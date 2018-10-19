
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
	
	public static class Global {
		
		public static final LogicEngine logic_engine = new LogicEngine();
		public static final RenderEngine render_engine = new RenderEngine(new GLFWWindow());
	}
	
	private Test() {

		Global.logic_engine.setRefreshRate(60);
		
		TargetManager targetManager = new TargetManager();
		targetManager.add(new TestTarget());
		
		Global.logic_engine.setTargetManager(targetManager);
		Global.render_engine.setTargetManager(targetManager);
		
		LinkMaster master = new LinkMaster();
		master.addSlave(Global.logic_engine);
		master.addSlave(Global.render_engine);
		
		master.linkedstart();
		
	}
	
	private class TestTarget extends TargetManager {
		
		private final Target triangle = new GLPipelineTriangle(0.5f);
		// private final Targetable triangle = new MeshTriangle(0.7f);
		// private final Shader shader = new Shader();
		
		public TestTarget() {
			add(triangle);
		}
		
	}
	
}
