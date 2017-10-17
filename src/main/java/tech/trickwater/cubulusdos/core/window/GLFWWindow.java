package tech.trickwater.cubulusdos.core.window;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.joml.Vector2i;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;
import tech.trickwater.cubulusdos.core.CoreGame;

public class GLFWWindow {
	
	private long window;
	private Vector2i size;
	private String title;
	private boolean init = false;
	private GLCapabilities caps;
	private GLFWVidMode vidmode;
	
	public GLFWWindow() {
		size = new Vector2i();
		size.set(300, 300);
		title = "GLFW Window";
	}
	
	public GLFWWindow init() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(300, 300, title, NULL, NULL);
		if (window == NULL) {
			CoreGame.error("GLFW window error");
			throw new RuntimeException("GLFW window could not be created.");
		}
		
		centerOnScreen();
		glfwSetFramebufferSizeCallback(window, (window, w, h) -> {
			size.set(w, h);
			GL11.glViewport(0, 0, w, h);
		});
		
		glfwMakeContextCurrent(window);
		caps = GL.createCapabilities();

		CoreGame.info("DETAILS:");
		CoreGame.info("\tLWJGL\t" + Version.getVersion());
		CoreGame.info("\tOpenGL\t" + glGetString(GL_VERSION));
		CoreGame.info("\tCubulus\t" + CoreGame.VERSION.toString());
		CoreGame.info("GLFW window created.");
		
		init = true;
		return this;
	}
	
	public GLFWWindow update() {
		glfwSwapBuffers(window);
		return this;
	}
	
	public GLFWWindow show() {
		glfwSwapInterval(1);
		glfwShowWindow(window);
		CoreGame.info("GLFW window shown.");
		return this;
	}
	
	public GLFWWindow hide() {
		glfwHideWindow(window);
		return this;
	}
	
	public GLFWWindow cleanup() {
		hide();
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		return this;
	}
	
	public long getWindow() {
		return window;
	}
	
	public Vector2i getWindowSize() {
		return size;
	}
	
	public GLFWWindow setWindowSize(final int width, final int height) {
		glfwSetWindowSize(window, width, height);
		return this;
	}
	
	public boolean shouldWindowClose() {
		return glfwWindowShouldClose(window);
	}
	
	public String getTitle() {
		return title;
	}
	
	public GLFWWindow setTitle(final String title) {
		this.title = title;
		if (init) {
			glfwSetWindowTitle(window, title);
		}
		return this;
	}
	
	public GLCapabilities getCapabilities() {
		return caps;
	}
	
	public GLFWWindow centerOnScreen() {
		GLFWVidMode vidmode = getMonitor();
		glfwSetWindowPos(window, (vidmode.width() - size.x) / 2, (vidmode.height() - size.y) / 2);
		return this;
	}
	
	public GLFWWindow halfScreen() {
		GLFWVidMode vidmode = getMonitor();
		setWindowSize(vidmode.width() / 2, vidmode.height() / 2);
		return this;
	}
	
	public GLFWVidMode getMonitor() {
		if (vidmode == null) {
			vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		}
		return vidmode;
	}
	
}