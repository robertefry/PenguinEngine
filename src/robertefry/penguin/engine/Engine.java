
package robertefry.penguin.engine;

import robertefry.penguin.engine.target.TargetManager;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Engine {

	private final Thread thread = new Thread( new Running() );
	private volatile boolean running = false, suspended = false;
	private volatile float refresh = -1;

	private final Clock clock = new Clock();
	private final TargetManager manager = new TargetManager();

	public synchronized void start() {
		if (isRunning()) return;
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
	}

	public synchronized void suspend() {
		suspended = true;
	}

	public synchronized void resume() {
		suspended = false;
	}
	
	public final class Clock implements Targetable {

		private long tickcount = 0;
		private long starttime = 0;

		@Override
		public void init( Engine engine ) {
			Targetable.super.init( engine );
			starttime = System.currentTimeMillis();
		}

		@Override
		public void update( Engine engine ) {
			Targetable.super.update( engine );
			tickcount++;
		}
		
		public long getStartTime() {
			return starttime;
		}
		
		public long getTickCount() {
			return tickcount;
		}

	}

	private final class Running implements Runnable {
		
		private final Time time = new Time();

		@Override
		public void run() {

			init();
			
			double omega = 0;
			while (isRunning()) {
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

	}

	public Thread getThread() {
		return thread;
	}
	
	public Clock getClock() {
		return clock;
	}

	public TargetManager getTargetManager() {
		return manager;
	}

	public boolean isRunning() {
		return running;
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
