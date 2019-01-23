
package core.render;

public abstract class Window {
	
	public int width = 800, height = 600;
	public String title = "Penguin Engine 1.0";
	
	public abstract void swapBuffers();
	
	public abstract boolean isCloseRequested();
	
	public abstract void create();
	
	public abstract void destroy();
	
}
