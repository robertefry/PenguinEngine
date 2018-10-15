
package core.render.target;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import core.Engine;
import core.target.Target;
import utilities.Buffers;

public class Mesh extends Target {
	
	private int vbo;
	private int size = 0;
	
	private Vertex[] vertices;
	
	public Mesh(Vertex... vertices) {
		this.vertices = vertices;
	}
	
	@Override
	public void create() {
		super.create();
		vbo = glGenBuffers();
		size = vertices.length * Vertex.SIZE;
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Buffers.createFlippedVertexBuffer(vertices), GL_STATIC_DRAW);
		vertices = null;
	}
	
	@Override
	public void render(Engine source) {
		super.render(source);
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glDisableVertexAttribArray(0);
	}
	
}
