
package core;

import core.target.TargetManager;

public class Engine extends CoreThreaded {
	
	protected TargetManager targetManager = new TargetManager();
	
	public TargetManager getTargetManager() {
		return targetManager;
	}
	
	public void setTargetManager(TargetManager targetManager) {
		this.targetManager = targetManager;
	}
	
}
