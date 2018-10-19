
package utilities;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import render.Vertex;

public class Buffers {
	
	public static FloatBuffer createFlippedVertexBuffer(Vertex... vertices) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);
		for (Vertex vertex : vertices) {
			buffer.put(vertex.getPosition().getX());
			buffer.put(vertex.getPosition().getY());
			buffer.put(vertex.getPosition().getZ());
		}
		buffer.flip();
		return buffer;
	}
	
}
