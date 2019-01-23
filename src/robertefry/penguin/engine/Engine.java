
package robertefry.penguin.engine;

import robertefry.penguin.engine.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 * @time 23:41:58
 */

public class Engine {
	
	private final Thread thread = new Thread(new Running());
	private volatile boolean running = false, suspended = false;
	private volatile float refresh = -1;
	
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
	
	private static final class Time {
		
		private static volatile long initialtime, currenttime;
		private static volatile long delta = 0;
		
		static {
			initialtime = currenttime = System.nanoTime();
		}
		
		public static synchronized void tick() {
			currenttime = System.nanoTime();
			delta = currenttime - initialtime;
			initialtime = currenttime;
		}
		
		public static synchronized long getDelta() {
			return delta;
		}
		
	}
	
	private final class Running implements Runnable {

		@Override
		public void run() {

			manager.init();
			
			double omega = 0;
			while (isRunning()) {
				Time.tick();
				if (!isSuspended()) omega += (refresh < 0) ? 1 : Time.getDelta() * refresh / 1e9;
				for (; omega >= 1; omega--) manager.update();
			}
			
			manager.dispose();
			
		}
		
	}
	
	public Thread getThread() {
		return thread;
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
	
	public void setRefreshRate(float refresh) {
		this.refresh = refresh;
	}
	
}
