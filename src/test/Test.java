
package test;

import core.link.LinkMaster;
import core.link.LinkedEngine;
import core.link.control.LinkControl;
import core.logic.LogicEngine;
import core.render.RenderEngine;
import core.render.Window;
import core.target.Target;
import core.target.TargetManager;
import test.object.PTriangle;

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
		LinkedEngine engineRender = new RenderEngine(new Window());
		
		linkMaster.addSlave(engineLogic);
		linkMaster.addSlave(engineRender);
		
		TargetManager targetManager = new TargetManager();
		targetManager.add(new TestTarget());
		
		engineLogic.setTargetManager(targetManager);
		engineRender.setTargetManager(targetManager);
		
		engineLogic.setRefreshRate(60);
		engineLogic.setCanLoad(true);
		
		linkMaster.linkedstart();
		
	}
	
	private class TestTarget extends Target {
		
		private final PTriangle triangle = new PTriangle(0.5f);
		
		@Override
		public void update() {
			super.update();
			triangle.update();
		}
		
		@Override
		public void render() {
			super.render();
			triangle.render();
		}
		
	}
	
}
