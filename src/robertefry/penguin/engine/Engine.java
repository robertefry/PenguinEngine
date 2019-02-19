
package robertefry.penguin.engine;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import robertefry.penguin.engine.listener.EngineLogicListener;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.engine.listener.EngineThreadListener;
import robertefry.penguin.input.EngineInputReciever;
import robertefry.penguin.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */

// TODO Renderer class & instance synchronisation
// rendering done by a different thread on request

public class Engine implements Startable, Suspendable {

	private final Engine.Timing timing = new Timing();
	private final Engine.Running running = new Running();
	private final Thread thread = new Thread( running );

	private volatile boolean active = false, suspended = false;
	private volatile float refreshrate = -1;

	private final TargetManager targetManager = new TargetManager();

	private final Queue< Runnable > preCycleTasks = new ConcurrentLinkedQueue<>();
	private final Set< EngineInputReciever > engineInputRecievers = new HashSet<>();
	private final Set< EngineStateListener > engineStateListeners = new HashSet<>();
	private final Set< EngineThreadListener > engineThreadListeners = new HashSet<>();
	private final Set< EngineLogicListener > engineLogicListeners = new HashSet<>();

	@Override
	public synchronized void start() {
		if ( !isActive() && !thread.isAlive() ) {
			active = true;
			thread.start();
		}
	}

	@Override
	public synchronized void stop() {
		active = false;
	}

	@Override
	public synchronized void suspend() {
		preCycleTasks.offer( () -> {
			engineStateListeners.forEach( EngineStateListener::onSuspend );
			suspended = true;
		} );
	}

	@Override
	public synchronized void resume() {
		preCycleTasks.offer( () -> {
			engineStateListeners.forEach( EngineStateListener::onResume );
			suspended = false;
		} );
	}

	public synchronized void forceTick() {
		preCycleTasks.offer( () -> {
			running.omega++;
		} );
	}

	public synchronized void forceRender() {
		preCycleTasks.offer( () -> {
			running.renderable = true;
		} );
	}

	private final class Timing {

		private volatile long lasttime = 0; // time when engine last ticked over
		private volatile long delta = 0; // time taken for the last tick sequence

		private synchronized void tick() {
			long currenttime = System.nanoTime();
			delta = currenttime - lasttime;
			lasttime = currenttime;
		}

		public synchronized long getDelta() {
			return delta;
		}

	}

	private final class Running implements Runnable {

		private double omega = 0;
		private boolean renderable = false;

		@Override
		public void run() {

			init();

			while ( isActive() ) {

				timing.tick();
				while ( !preCycleTasks.isEmpty() ) preCycleTasks.poll().run();
				if ( !isSuspended() ) omega += ( refreshrate < 0 ) ? 1 : timing.getDelta() * refreshrate / 1e9;

				while ( omega >= 1 ) {
					renderable = true;
					omega--;
					pollInput();
					tick();
				}

				if ( renderable ) {
					renderable = false;
					render();
				}

			}

			dispose();

		}

		private void init() {
			engineThreadListeners.parallelStream().forEach( EngineThreadListener::preInitialisationTask );
			targetManager.init();
			engineThreadListeners.parallelStream().forEach( EngineThreadListener::postInitialisationTask );
		}

		private void dispose() {
			engineThreadListeners.parallelStream().forEach( EngineThreadListener::preDisposalTask );
			targetManager.dispose();
			engineThreadListeners.parallelStream().forEach( EngineThreadListener::postDisposalTask );
		}

		private void pollInput() {
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::prePollInput );
			targetManager.pollInput();
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::postPollInput );
		}

		private void tick() {
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::preTick );
			targetManager.update();
			engineInputRecievers.parallelStream().forEach( EngineInputReciever::update );
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::postTick );
		}

		// TODO rendering by new constant thread

		private void render() {
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::preRender );
			targetManager.render();
			engineLogicListeners.parallelStream().forEach( EngineLogicListener::postRender );
		}

	}

	public synchronized boolean isAlive() {
		return thread.isAlive();
	}

	@Override
	public synchronized boolean isActive() {
		return active;
	}

	@Override
	public synchronized boolean isSuspended() {
		return suspended;
	}

	public synchronized Timing getTiming() {
		return timing;
	}

	public synchronized float getRefreshRate() {
		return refreshrate;
	}

	public synchronized void setRefreshRate( float refreshrate ) {
		this.refreshrate = refreshrate;
	}

	public synchronized TargetManager getTargetManager() {
		return targetManager;
	}

	public synchronized Set< EngineInputReciever > getEngineInputRecievers() {
		return engineInputRecievers;
	}

	public synchronized Set< EngineStateListener > getEngineStateListeners() {
		return engineStateListeners;
	}

	public synchronized Set< EngineThreadListener > getEngineThreadListeners() {
		return engineThreadListeners;
	}

	public synchronized Set< EngineLogicListener > getEngineLogicListeners() {
		return engineLogicListeners;
	}

}
