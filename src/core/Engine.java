
package core;

import core.target.TargetManager;

public class Engine extends CoreThreaded {
	
	private TargetManager targetManager = new TargetManager();
	private boolean canLoad = true;
	
	@Override
	public void init() {
		if (canLoad) targetManager.load(this);
		super.init();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (canLoad) targetManager.unload(this);
	}
	
	public TargetManager getTargetManager() {
		return targetManager;
	}
	
	public void setTargetManager(TargetManager targetManager) {
		this.targetManager = targetManager;
	}
	
	public boolean isCanLoad() {
		return canLoad;
	}
	
	public void setCanLoad(boolean canLoad) {
		this.canLoad = canLoad;
	}
	
}
