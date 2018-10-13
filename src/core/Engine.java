
package core;

import core.target.TargetManager;

public class Engine extends CoreThreaded {
	
	private TargetManager targetManager = new TargetManager();
	private boolean canLoad = false;
	
	@Override
	public void init() {
		if (canLoad) targetManager.load();
		super.init();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (canLoad) targetManager.unload();
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
