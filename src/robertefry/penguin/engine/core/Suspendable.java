package robertefry.penguin.engine.core;

public interface Suspendable {
	
	public void suspend();
	
	public void resume();
	
	public boolean isSuspended();

}
