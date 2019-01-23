
package core;

import core.control.CoreControl;

public class CoreRunning extends CoreControl implements Runnable {
	
	private volatile Time time = new Time();
	private float refreshRate = -1;
	
	@Override
	public void run() {
		
		double omega = 0;
		
		init();
		
		while (isRunning()) {
			
			final float refresh = getRefreshRate();
			
			time.tick();
			if (!isSuspended()) omega += (refresh < 0) ? 1 : time.getDelta() * refresh / 1e9;
			for (; omega >= 1; omega--) tick();
			
		}
		
		dispose();
		
	}

	public void tick() {}

	public float getRefreshRate() {
		return refreshRate;
	}
	
	public void setRefreshRate(float refreshRate) {
		this.refreshRate = refreshRate;
	}
	
	private final class Time {
		
		private long initialtime, currenttime;
		private long delta = 0;
		
		public Time() {
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