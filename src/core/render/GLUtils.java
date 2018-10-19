
package core.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.awt.Color;

public class GLUtils {
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}
	
	public static void clearScreen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void glinit(Color clearcolor) {
		
		// Setup clockwise face culling
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		// Setup z-depth
		glEnable(GL_DEPTH_TEST);
		// TODO z-depth clamping
	}
	
	public static void setClearColor(Color clearcolor) {
		float red = clearcolor.getRed() / 255f;
		float green = clearcolor.getGreen() / 255f;
		float blue = clearcolor.getBlue() / 255f;
		float alpha = clearcolor.getAlpha() / 255f;
		glClearColor(red, green, blue, alpha);
	}
	
	public static void enableGammaCorrection() {
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	public static void disableGammaCorrection() {
		glDisable(GL_FRAMEBUFFER_SRGB);
	}
	
}
