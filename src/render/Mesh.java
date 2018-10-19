
package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import core.target.Target;

public class Mesh extends Target {

	private int vbo;
	private Vertex[] vertices;
	private final int size;
	
	public Mesh(Vertex... vertices) {
		this.vertices = vertices;
		size = vertices.length * Vertex.SIZE;
	}
	
//	vbo = glGenBuffers();
//	glBindBuffer(GL_ARRAY_BUFFER, vbo);
//	glBufferData(GL_ARRAY_BUFFER, Buffers.createFlippedVertexBuffer(vertices), GL_STATIC_DRAW);

	@Override
	public void update() {}
	
	@Override
	public void render() {
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glDisableVertexAttribArray(0);
	}
	
}
