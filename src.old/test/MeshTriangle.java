
package test;

import java.io.File;

import core.target.Target;
import math.Vector3f;
import render.Mesh;
import render.Shader;
import render.Vertex;
import resource.ResourceLoader;

public class MeshTriangle extends Target {
	
	private final Mesh mesh = new Mesh();
	private final Shader shader = new Shader();
	
	public MeshTriangle(float size) {
		
		Vertex[] meshdata = new Vertex[] {
				new Vertex(new Vector3f(-size, -size, 0)),
				new Vertex(new Vector3f(0, size, 0)),
				new Vertex(new Vector3f(size, -size, 0))
		};
		mesh.setVertices(meshdata);
		
		 shader.addFragmentShader(ResourceLoader.loadFileAsString(new File("res/shaders/basic.fs")));
		 shader.addVertexShader(ResourceLoader.loadFileAsString(new File("res/shaders/basic.vs")));
		
	}
	
	@Override
	public void makeRenderable() {
		shader.compile();
		mesh.makeRenderable();
	}
	
	@Override
	public void render() {
		shader.bind();
		mesh.render();
	}
	
}
