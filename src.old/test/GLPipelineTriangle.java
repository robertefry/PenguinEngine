
package test;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import core.target.Target;

public class GLPipelineTriangle extends Target {
	
	private volatile float size;
	private volatile float x = 0.3f, y = 0, dx = 0.007f, dy = 0.005f;
	
	public GLPipelineTriangle(float size) {
		this.size = size;
	}
	
	@Override
	public void makeRenderable() {}
	
	@Override
	public void breakRenderable() {}
	
	@Override
	public void update() {
		
		if (x + size >= 1 || x - size <= -1) dx *= -1;
		if (y + size >= 1 || y - size <= -1) dy *= -1;
		
		x += dx;
		y += dy;
		
	}
	
	@Override
	public void render() {
		glBegin(GL_TRIANGLES);
		
	    glColor3f(1, 0, 0);
	    glVertex3f(x - size, y - size, 0);

	    glColor3f(0, 1, 0);
	    glVertex3f(x, y + size, 0);

	    glColor3f(0, 0, 1);
	    glVertex3f(x + size, y - size, 0);
	    
	    glEnd();
	}
	
}
