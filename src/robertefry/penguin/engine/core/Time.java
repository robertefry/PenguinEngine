package robertefry.penguin.engine.core;

public final class Time {

	private volatile long initialtime = 0;	// time when engine last ticked over
	private volatile long currenttime = 0;	// time when current tick called
	private volatile long delta = 0;		// time taken for engine to tick over

	public void init() {
		initialtime = currenttime = System.nanoTime();
	}

	public void tick() {
		currenttime = System.nanoTime();
		delta = currenttime - initialtime;
		initialtime = currenttime;
	}

	public long getInitialtime() {
		return initialtime;
	}

	public long getCurrenttime() {
		return currenttime;
	}

	public long getDelta() {
		return delta;
	}

}
