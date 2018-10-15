
package core.render;

import core.link.LinkedEngine;

public class RenderEngine extends LinkedEngine {
	
	public static final String THREAD_NAME = "render";
	
	private final GLFWWindow window;
	private final RenderPipeline renderPipeline = new RenderPipeline();
	
	public RenderEngine(GLFWWindow window) {
		this.window = window;
		getThread().setName(THREAD_NAME);
	}
	
	@Override
	public void init() {
		window.create();
		super.init();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		window.destroy();
	}
	
	@Override
	public void tick() {
		super.tick();
		GLFWUtils.pollEvents();
		if (window.isCloseRequested()) getSuperMaster().linkedstop();
		GLUtils.clearScreen();
		try {
			renderPipeline.executeNext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getTargetManager().render(this);
		window.swapBuffers();
	}

	public GLFWWindow getWindow() {
		return window;
	}
	
	public RenderPipeline getRenderPipeline() {
		return renderPipeline;
	}
	
}
