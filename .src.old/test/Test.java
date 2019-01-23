
package test;

import core.link.LinkMaster;
import core.logic.LogicEngine;
import core.render.GLFWWindow;
import core.render.RenderEngine;
import core.target.Target;
import core.target.TargetManager;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
	}
	
	/*
	 * WARNING::: Screen may flicker since a Targetable can update() and
	 * render() at the same time TODO May need to fix Targetable simultaneous
	 * update and render
	 */
	
	private Test() {
		
		LogicEngine logicEngine = new LogicEngine();
		RenderEngine renderEngine = new RenderEngine(new GLFWWindow());
		
		logicEngine.setRefreshRate(60);
		
		TargetManager targetManager = new TargetManager();
		targetManager.setRenderEngine(renderEngine);
		targetManager.add(new TestTarget());
		
		logicEngine.setTargetManager(targetManager);
		renderEngine.setTargetManager(targetManager);
		
		LinkMaster master = new LinkMaster();
		master.addSlave(logicEngine);
		master.addSlave(renderEngine);
		
		master.linkedstart();
		
	}
	
	private class TestTarget extends TargetManager {
		
		// private final Target triangle = new GLPipelineTriangle(0.5f);
		private final Target triangle = new MeshTriangle(0.7f);
		
		public TestTarget() {
			add(triangle);
		}
		
	}
	
}
