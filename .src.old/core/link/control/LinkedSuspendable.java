package core.link.control;

import core.control.Suspendable;

public interface LinkedSuspendable extends Suspendable {
	
	public void linkedsuspend();
	
	public void linkedresume();
	
}
