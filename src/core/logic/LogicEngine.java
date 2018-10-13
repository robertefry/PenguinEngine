
package core.logic;

import core.link.LinkedEngine;

public class LogicEngine extends LinkedEngine {
	
	public LogicEngine() {
		getThread().setName("logic");
	}
	
	@Override
	public void tick() {
		super.tick();
		getTargetManager().update();
	}
	
}
