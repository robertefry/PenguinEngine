
package robertefry.penguin.engine;

import robertefry.penguin.engine.core.Startable;
import robertefry.penguin.engine.core.Suspendable;
import robertefry.penguin.engine.core.Time;
import robertefry.penguin.engine.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public final class Engine implements Startable, Suspendable {

	private final Time time = new Time();
	private final Running running = new Running();
	private final Thread thread = new Thread( running );
	private volatile boolean active = false, suspended = false;
	private volatile float refresh = -1;

	private final TargetManager manager = new TargetManager();

	@Override
	public synchronized void start() {
		if (isActive()) return;
		active = true;
		thread.start();
	}

	@Override
	public synchronized void stop() {
		active = false;
	}

	@Override
	public synchronized void suspend() {
		suspended = true;
	}

	@Override
	public synchronized void resume() {
		suspended = false;
	}

	private final class Running implements Runnable {

		@Override
		public void run() {

			time.init();
			init();
			
			double omega = 0;
			while (isActive()) {
				time.tick();
				if (!isSuspended()) omega += (refresh < 0) ? 1 : time.getDelta() * refresh / 1e9;
				for ( ; omega >= 1; omega-- ) tick();
			}
			
			dispose();

		}
		
		private void init() {
			manager.init( Engine.this );
		}
		
		private void dispose() {
			manager.dispose( Engine.this );
		}
		
		private void tick() {
			manager.update( Engine.this );
		}

	}
	
	public Time getTime() {
		return time;
	}

	public TargetManager getTargetManager() {
		return manager;
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
