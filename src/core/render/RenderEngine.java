
package core.render;

import core.link.LinkedEngine;

public class RenderEngine extends LinkedEngine {
	
	private final Window window;
	
	public RenderEngine(Window window) {
		this.window = window;
		getThread().setName("rendering");
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
		if (window.isCloseRequested()) getSuperMaster().linkedstop();
		getTargetManager().render();
		window.update();
		window.pollEvents();
	}

	public Window getWindow() {
		return window;
	}
	
}
