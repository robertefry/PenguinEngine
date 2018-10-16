
package core;

public class CoreThreaded extends CoreRunning {
	
	private final Thread thread = new Thread(this);
	
	@Override
	public void start() {
		if (isRunning()) return;
		super.start();
		thread.start();
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public String getName() {
		return thread.getName();
	}
	
	public void setName(String name) {
		thread.setName(name);
	}
	
}
