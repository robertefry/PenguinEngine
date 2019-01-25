
package robertefry.penguin.engine;

import robertefry.penguin.engine.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Engine {

	private final Running running = new Running();
	private final Thread thread = new Thread( running );
	private volatile boolean active = false, suspended = false;
	private volatile float refresh = -1;

	private final Clock clock = new Clock();
	private final TargetManager manager = new TargetManager();

	public synchronized void start() {
		if (isActive()) return;
		active = true;
		thread.start();
	}

	public synchronized void stop() {
		active = false;
	}

	public synchronized void suspend() {
		suspended = true;
	}

	public synchronized void resume() {
		suspended = false;
	}
	
	public final class Clock {

		private long tickcount = 0;
		private long starttime = 0;
		private double delta = 0;

		private void init( Engine engine ) {
			starttime = engine.running.time.initialtime;
		}

		private void update( Engine engine ) {
			delta = engine.running.time.delta;
			tickcount++;
		}
		
		private void dispose( Engine engine ) {
		}
		
		public long getStartTime() {
			return starttime;
		}
		
		public long getTickCount() {
			return tickcount;
		}
		
		public double getDelta() {
			return delta;
		}

	}

	private final class Running implements Runnable {

		private final class Time {

			private volatile long initialtime, currenttime;
			private volatile long delta = 0;

			public void init() {
				initialtime = currenttime = System.nanoTime();
			}

			public void tick() {
				currenttime = System.nanoTime();
				delta = currenttime - initialtime;
				initialtime = currenttime;
			}

			public long getDelta() {
				return delta;
			}

		}
		
		private final Time time = new Time();

		@Override
		public void run() {

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
			time.init();
			clock.init( Engine.this );
			manager.init( Engine.this );
		}
		
		private void dispose() {
			manager.dispose( Engine.this );
			clock.dispose( Engine.this );
		}
		
		private void tick() {
			clock.update( Engine.this );
			manager.update( Engine.this );
		}

	}
	
	public Clock getClock() {
		return clock;
	}

	public TargetManager getTargetManager() {
		return manager;
	}

	public boolean isActive() {
		return active;
	}

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
