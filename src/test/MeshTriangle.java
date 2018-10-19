
package test;

import core.target.TargetManager;
import math.Vector3f;
import render.Mesh;
import render.Vertex;

public class MeshTriangle extends TargetManager {

	private Mesh mesh;
	
	public MeshTriangle(float size) {
		Vertex[] meshdata = new Vertex[] {
				new Vertex(new Vector3f(-size, -size, 0)),
				new Vertex(new Vector3f(0, size, 0)),
				new Vertex(new Vector3f(size, -size, 0))
		};
		mesh = new Mesh(meshdata);
		add(mesh);
	}
	
}
