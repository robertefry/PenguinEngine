
package render;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

public class Shader {
	
	private int program;
	
//	program = glCreateProgram();
//	if (program == 0) {
//		System.err.println("shader program creation failed: valid memory location not found");
//		System.exit(-1);
//	}
	
	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void compile() {
		if (program == 0) return; // not yet created
		
		glLinkProgram(program);
		if (glGetShaderi(program, GL_LINK_STATUS) == 0) {
			System.err.println("shader program linking failed");
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(-1);
		}
		
		glValidateProgram(program);
		if (glGetShaderi(program, GL_VALIDATE_STATUS) == 0) {
			System.err.println("shader program validation failed");
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(-1);
		}
		
	}
	
	public void bind() {
		if (program == 0) return; // not yet created
		glUseProgram(program);
	}
	
	private void addProgram(String text, int type) {
		if (program == 0) return; // not yet created
		
		int shader = glCreateShader(type);
		if (shader == 0) {
			System.err.println("shader creation failed: valid memory location not found");
			System.exit(-1);
		}
		
		glShaderSource(shader, text);
		
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			System.err.println("shader compilation failed: valid memory location not found");
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(-1);
		}
		
		glAttachShader(shader, program);
		
	}
	
}
