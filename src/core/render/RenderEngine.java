
package core.render;

import core.link.LinkedEngine;

public class RenderEngine extends LinkedEngine {
	
	private final Window window;
	private final RenderPipeline renderPipeline = new RenderPipeline();
	
	public RenderEngine(Window window) {
		this.window = window;
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
		getTargetManager().render();
		window.swapBuffers();
	}

	public Window getWindow() {
		return window;
	}
	
	public RenderPipeline getRenderPipeline() {
		return renderPipeline;
	}
	
}
