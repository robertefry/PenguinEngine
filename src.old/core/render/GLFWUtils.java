package core.render;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public class GLFWUtils {
	
	public static void pollEvents() {
		glfwPollEvents();
	}
	
}
