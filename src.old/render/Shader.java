
package render;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashSet;
import java.util.Set;

import core.target.TargetSingle;
import logging.Logger;

public class Shader extends TargetSingle {
	
	private int program;
	private final Set<ShaderSingle> singles = new HashSet<>();
	
	public void addFragmentShader(String text) {
		singles.add(new ShaderSingle(text, GL_FRAGMENT_SHADER));
	}
	
	public void addVertexShader(String text) {
		singles.add(new ShaderSingle(text, GL_VERTEX_SHADER));
	}
	
	public void addGeometryShader(String text) {
		singles.add(new ShaderSingle(text, GL_GEOMETRY_SHADER));
	}
	
	public void compile() {
		
		singles.forEach(ShaderSingle::attachRaw);
		
		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
			Logger.log(Logger.SEVERE, "shader program linking failed", glGetProgramInfoLog(program, 1024));
			System.exit(-1);
		}
		
		glValidateProgram(program);
		if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
			Logger.log(Logger.SEVERE, "shader program validation failed", glGetProgramInfoLog(program, 1024));
			System.exit(-1);
		}
		
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	@Override
	public void makeRenderable() {
		
		program = glCreateProgram();
		if (program == 0) {
			Logger.log(Logger.SEVERE, "shader program creation failed: valid memory location not found");
			System.exit(-1);
		}
		
	}
	
	private class ShaderSingle {
		
		private final String text;
		private final int type;
		
		public ShaderSingle(String text, int type) {
			this.text = text;
			this.type = type;
		}
		
		public void attachRaw() {
			
			int shader = glCreateShader(type);
			if (shader == 0) {
				Logger.log(Logger.SEVERE, "shader creation failed: valid memory location not found");
				System.exit(-1);
			}
			
			glShaderSource(shader, text);
			
			glCompileShader(shader);
			if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
				Logger.log(Logger.SEVERE, "shader compilation failed: valid memory location not found", glGetShaderInfoLog(shader, 1024));
				System.exit(-1);
			}

			glAttachShader(program, shader);
			
		}
		
	}
	
}
