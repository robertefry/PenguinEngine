
package test;

import core.engines.Engines;
import core.link.LinkedEngine;
import core.render.GLFWWindow;
import core.target.Target;
import core.target.TargetManager;
import core.target.Targetable;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
	/*
	 * WARNING::: Screen may flicker since a Targetable can update() and
	 * render() at the same time
	 * TODO May need to fix Targetable simultaneous update and render
	 */
	
	private Test() {
		
		LinkedEngine renderEngine = Engines.newRenderEngine(new GLFWWindow());
		LinkedEngine logicEngine = Engines.newLogicEngine();
		
		TargetManager targetManager = new TargetManager();
		targetManager.add(new TestTarget());
		
		logicEngine.setRefreshRate(60);
		logicEngine.setTargetManager(targetManager);
		renderEngine.setTargetManager(targetManager);
		
		Engines.startAll();
		
	}
	
	private class TestTarget extends Target {
		
		private final Targetable pTriangle = new PTriangle(0.5f);
		// private final Targetable mTriangle = new MeshTriangle(0.5f);
		
		public TestTarget() {
			// addSubTargets(pTriangle);
			addSubTargets(pTriangle);
		}
		
	}
	
}
