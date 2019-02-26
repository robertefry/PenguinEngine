
package robertefry.penguin.engine;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import robertefry.penguin.engine.listener.EngineLogicListener;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.engine.listener.EngineThreadListener;
import robertefry.penguin.engine.render.Renderer;
import robertefry.penguin.input.EngineInputReciever;
import robertefry.penguin.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */

public class Engine implements Startable, Suspendable, Resetable {

	private final Engine.Timing timing = new Timing();
	private final Engine.Running running = new Running();

	private final Queue< Runnable > preCycleTasks = new ConcurrentLinkedQueue<>();
	private final Set< EngineInputReciever > engineInputRecievers = new HashSet<>();
	private final Set< EngineStateListener > engineStateListeners = new HashSet<>();
	private final Set< EngineThreadListener > engineThreadListeners = new HashSet<>();
	private final Set< EngineLogicListener > engineLogicListeners = new HashSet<>();

	private final TargetManager targetManager = new TargetManager();
	private final Renderer renderer = new Renderer( targetManager );

	private volatile boolean active = false, suspended = false;
	private volatile float refreshrate = -1;

	@Override
	public synchronized void start() {
		if ( !isActive() ) {
			Thread thread = new Thread( running );
			active = true;
			thread.start();
		}
	}

	@Override
	public synchronized void stop() {
		active = false;
	}

	@Override
	public void suspend() {
		preCycleTasks.offer( () -> {
			engineStateListeners.stream().parallel().forEach( EngineStateListener::onSuspend );
			suspended = true;
		} );
	}

	@Override
	public void resume() {
		preCycleTasks.offer( () -> {
			engineStateListeners.stream().parallel().forEach( EngineStateListener::onResume );
			suspended = false;
		} );
	}

	@Override
	public void reset() {
		preCycleTasks.offer( () -> {
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::preReset );
			targetManager.reset();
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::postReset );
		} );
	}

	public void forceTick() {
		preCycleTasks.offer( () -> {
			running.omega++;
		} );
	}

	public void forceRender() {
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

			while ( active ) {

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
			engineThreadListeners.stream().parallel().forEach( EngineThreadListener::preInitialisationTask );
			targetManager.init();
			engineThreadListeners.stream().parallel().forEach( EngineThreadListener::postInitialisationTask );
		}

		private void dispose() {
			engineThreadListeners.stream().parallel().forEach( EngineThreadListener::preDisposalTask );
			targetManager.dispose();
			engineThreadListeners.stream().parallel().forEach( EngineThreadListener::postDisposalTask );
		}

		private void pollInput() {
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::prePollInput );
			targetManager.pollInput();
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::postPollInput );
		}

		private void tick() {
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::preTick );
			targetManager.update();
			engineInputRecievers.stream().parallel().forEach( EngineInputReciever::update );
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::postTick );
		}

		private void render() {
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::preRender );
			renderer.render();
			engineLogicListeners.stream().parallel().forEach( EngineLogicListener::postRender );
		}

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

	public TargetManager getTargetManager() {
		return targetManager;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public Set< EngineInputReciever > getEngineInputRecievers() {
		return engineInputRecievers;
	}

	public Set< EngineStateListener > getEngineStateListeners() {
		return engineStateListeners;
	}

	public Set< EngineThreadListener > getEngineThreadListeners() {
		return engineThreadListeners;
	}

	public Set< EngineLogicListener > getEngineLogicListeners() {
		return engineLogicListeners;
	}

}
