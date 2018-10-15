
package test;

import core.Engine;
import core.render.RenderEngine;
import core.render.target.Mesh;
import core.render.target.Vertex;
import core.target.Target;
import math.Vector3f;

public class MeshTriangle extends Target {

	private Mesh mesh;
	
	public MeshTriangle(float size) {
		Vertex[] meshdata = new Vertex[] {
				new Vertex(new Vector3f(-size, -size, 0)),
				new Vertex(new Vector3f(-size, size, 0)),
				new Vertex(new Vector3f(0, size, 0))
		};
		mesh = new Mesh(meshdata);
		addSubTargets(mesh);
	}
	
	@Override
	public void load(Engine source) {
		super.load(source);
		if (source instanceof RenderEngine) {
			mesh.create();
		}
	}
	
}
