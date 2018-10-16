
package core;

import core.engines.EngineNames;
import core.engines.Engines;
import core.target.TargetManager;
import logging.Logger;

public class Engine extends CoreThreaded {
	
	private TargetManager targetManager = new TargetManager();
	private boolean canLoad = true;
	
	public Engine() {
		getThread().setName(EngineNames.generateName());
		Engines.putEngine(this);
	}
	
	@Override
	public void start() {
		Logger.log("Starting Engine " + getName());
		super.start();
	}
	
	@Override
	public void stop() {
		Logger.log("Stopping Engine " + getName());
		super.stop();
	}
	
	@Override
	public void init() {
		Logger.log("Initialising Engine " + getName());
		if (canLoad) targetManager.load(this);
		super.init();
	}
	
	@Override
	public void dispose() {
		Logger.log("Disposing of Engine " + getName());
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
