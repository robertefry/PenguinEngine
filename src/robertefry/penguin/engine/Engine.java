
package robertefry.penguin.engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import robertefry.penguin.engine.core.Startable;
import robertefry.penguin.engine.core.Suspendable;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.engine.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Engine implements Startable, Suspendable {

	private final Time time = new Time();
	private final Running running = new Running();
	private final Thread thread = new Thread( running );

	private volatile boolean active = false, suspended = false;
	private volatile float refresh = -1;

	private final TargetManager manager = new TargetManager();
	private final Set<EngineStateListener> stateListeners = new HashSet<>();
	private final Stack<Runnable> pretickTasks = new Stack<>();

	@Override
	public synchronized void start() {
		if (!isActive()) {
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
		pretickTasks.push( () -> stateListeners.forEach( EngineStateListener::engineSuspending ) );
		suspended = true;
		pretickTasks.push( () -> stateListeners.forEach( EngineStateListener::engineSuspended ) );
	}

	@Override
	public synchronized void resume() {
		pretickTasks.push( () -> stateListeners.forEach( EngineStateListener::engineResuming ) );
		suspended = false;
		pretickTasks.push( () -> stateListeners.forEach( EngineStateListener::engineResumed ) );
	}

	public synchronized void forceTick() {
		running.omega++;
	}

	public synchronized void forceRender() {
		running.renderable = true;
	}

	private final class Time {

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

			stateListeners.forEach( EngineStateListener::engineStarting );
			init();
			stateListeners.forEach( EngineStateListener::engineStarted );

			while (isActive()) {

				time.tick();
				pretickTasks.forEach( Runnable::run );
				if (!isSuspended()) omega += (refresh < 0) ? 1 : time.getDelta() * refresh / 1e9;

				while (omega >= 1) {
					omega--;
					renderable = true;
					tick();
				}

				if (renderable) {
					renderable = false;
					render();
				}

			}

			stateListeners.forEach( EngineStateListener::engineStopping );
			dispose();
			stateListeners.forEach( EngineStateListener::engineStopped );

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

	public Time getTime() {
		return time;
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

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}

	public float getRefreshRate() {
		return refresh;
	}

	public void setRefreshRate( float refresh ) {
		this.refresh = refresh;
	}

}
