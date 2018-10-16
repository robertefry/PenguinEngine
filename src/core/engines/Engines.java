
package core.engines;

import java.util.HashMap;
import java.util.Map;

import core.Engine;
import core.link.LinkMaster;
import core.link.LinkedEngine;
import core.link.control.LinkControl;
import core.logic.LogicEngine;
import core.render.GLFWWindow;
import core.render.RenderEngine;

public class Engines {
	
	private static final LinkControl linkControl = new LinkMaster();
	private static final Map<String, Engine> map = new HashMap<>();
	
	public static Engine putEngine(Engine engine) {
		map.put(engine.getThread().getName(), engine);
		return engine;
	}
	
	public static Engine putEngine(LinkedEngine engine) {
		linkControl.addSlave(engine);
		map.put(engine.getThread().getName(), engine);
		return engine;
	}
	
	public static RenderEngine newRenderEngine(GLFWWindow window) {
		return (RenderEngine) putEngine(new RenderEngine(window));
	}
	
	public static LogicEngine newLogicEngine() {
		return (LogicEngine) putEngine(new LogicEngine());
	}
	
	public static void startAll() {
		linkControl.linkedstart();
	}
	
	public static void stopAll() {
		linkControl.linkedstop();
	}
	
	public static void suspendAll() {
		linkControl.linkedsuspend();
	}
	
	public static void resumeAll() {
		linkControl.linkedresume();
	}
	
	public static Map<String, Engine> getMap() {
		return map;
	}
	
}
