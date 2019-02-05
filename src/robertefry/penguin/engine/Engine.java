
package robertefry.penguin.engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import robertefry.penguin.engine.core.Resetable;
import robertefry.penguin.engine.core.Startable;
import robertefry.penguin.engine.core.Suspendable;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.engine.listener.EngineThreadListener;
import robertefry.penguin.engine.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Engine implements Resetable, Startable, Suspendable {

	private final Engine.Timing timing = new Timing();
	private final Engine.Running running = new Running();
	private final Thread thread = new Thread( running );

	private volatile boolean active = false, suspended = false;
	private volatile float refreshrate = -1;

	private final TargetManager manager = new TargetManager();
	private final Stack<Runnable> preCycleTasks = new Stack<>();
	private final Set<EngineStateListener> stateListeners = new HashSet<>();
	private final Set<EngineThreadListener> threadListeners = new HashSet<>();

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
		preCycleTasks.push( () -> {
			stateListeners.forEach( EngineStateListener::onEngineSuspend );
			suspended = true;
		} );
	}

	@Override
	public synchronized void resume() {
		preCycleTasks.push( () -> {
			stateListeners.forEach( EngineStateListener::onEngineResume );
			suspended = false;
		} );
	}

	@Override
	public void reset() {
		preCycleTasks.push( () -> {
			manager.reset();
		} );
	}

	public synchronized void forceTick() {
		preCycleTasks.push( () -> {
			running.omega++;
		} );
	}

	public synchronized void forceRender() {
		preCycleTasks.push( () -> {
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

			threadListeners.forEach( EngineThreadListener::enginePreInitialisationTask );
			init();
			threadListeners.forEach( EngineThreadListener::enginePostInitialisationTask );

			while ( isActive() ) {

				timing.tick();
				while ( !preCycleTasks.isEmpty() ) preCycleTasks.pop().run();
				if ( !isSuspended() ) omega += ( refreshrate < 0 ) ? 1 : timing.getDelta() * refreshrate / 1e9;

				while ( omega >= 1 ) {
					omega--;
					renderable = true;
					tick();
				}

				if ( renderable ) {
					renderable = false;
					render();
				}

			}

			threadListeners.forEach( EngineThreadListener::enginePreDisposalTask );
			dispose();
			threadListeners.forEach( EngineThreadListener::enginePostDisposalTask );

		}

		private void init() {
			manager.init( Engine.this );
		}

		private void dispose() {
			manager.dispose( Engine.this );
		}

		private void tick() {
			manager.tick( Engine.this );
		}

		private void render() {
			manager.render( Engine.this );
		}

	}

	public Timing getTiming() {
		return timing;
	}

	public TargetManager getTargetManager() {
		return manager;
	}

	public void addStateListener( EngineStateListener... listeners ) {
		stateListeners.addAll( Arrays.asList( listeners ) );
	}

	public void removeStateListener( EngineStateListener... listeners ) {
		stateListeners.removeAll( Arrays.asList( listeners ) );
	}

	public void addThreadListener( EngineThreadListener... listeners ) {
		threadListeners.addAll( Arrays.asList( listeners ) );
	}

	public void removeThreadListener( EngineThreadListener... listeners ) {
		threadListeners.addAll( Arrays.asList( listeners ) );
	}

	public boolean isAlive() {
		return thread.isAlive();
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}

	public float getRefreshRate() {
		return refreshrate;
	}

	public void setRefreshRate( float refreshrate ) {
		this.refreshrate = refreshrate;
	}

}
