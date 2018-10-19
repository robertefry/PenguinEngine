
package core.control;

public class CoreControl implements Startable, Initializable, Suspendable {
	
	private volatile boolean running = false, initialized = false, suspended = false;

	@Override
	public void start() {
		running = true;
	}

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public void init() {
		initialized = true;
	}

	@Override
	public void dispose() {
		initialized = false;
	}

	@Override
	public void suspend() {
		suspended = true;
	}

	@Override
	public void resume() {
		suspended = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}
	
}
