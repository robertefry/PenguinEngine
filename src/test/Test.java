
package test;

import core.link.LinkMaster;
import core.link.LinkedEngine;
import core.link.control.LinkControl;
import core.logic.LogicEngine;
import core.render.GLFWWindow;
import core.render.RenderEngine;
import core.target.Target;
import core.target.TargetManager;
import core.target.Targetable;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
	/*
	 * WARNING::: Screen may flicker since a Targetable can update() and
	 * render() at the same time TODO May need to fix Targetable simultaneous
	 * update() and render()
	 */
	
	private Test() {
		
		LinkControl linkMaster = new LinkMaster();
		LinkedEngine engineLogic = new LogicEngine();
		LinkedEngine engineRender = new RenderEngine(new GLFWWindow());
		
		linkMaster.addSlave(engineLogic);
		linkMaster.addSlave(engineRender);
		
		TargetManager targetManager = new TargetManager();
		targetManager.add(new TestTarget());
		
		engineLogic.setTargetManager(targetManager);
		engineRender.setTargetManager(targetManager);
		
		engineLogic.setRefreshRate(60);
		
		linkMaster.linkedstart();
		
	}
	
	private class TestTarget extends Target {
		
		// private final Targetable pTriangle = new PTriangle(0.5f);
		private final Targetable mTriangle = new MeshTriangle(0.5f);
		
		public TestTarget() {
			// addSubTargets(pTriangle);
			addSubTargets(mTriangle);
		}
		
	}
	
}
