
package core.control;

public interface Suspendable {
	
	public void suspend();
	
	public void resume();
	
	public boolean isSuspended();
	
}
