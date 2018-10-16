
package core.logic;

import core.link.LinkedEngine;

public class LogicEngine extends LinkedEngine {
	
	@Override
	public void tick() {
		super.tick();
		getTargetManager().update(this);
	}
	
}
