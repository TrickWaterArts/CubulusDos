package tech.trickwater.cubulusdos.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;
import tech.trickwater.cubulusdos.core.event.EventHandler;
import tech.trickwater.cubulusdos.core.events.EventGameClose;
import tech.trickwater.cubulusdos.core.events.EventGameConstruct;
import tech.trickwater.cubulusdos.core.events.EventGameInitialization;
import tech.trickwater.cubulusdos.core.mod.GameModHandler;
import tech.trickwater.cubulusdos.core.window.GLFWWindow;

public class CubulusDos {
	
	public static final SemVer VERSION = new SemVer(0, 0, 1);
	private static final Logger log = LogManager.getLogger("Cubulus");
	
	private GLFWWindow window;
	private EventHandler eventHandler;
	private GameModHandler modHandler;
	
	public void onGameLaunch(String[] args) {
		info("Game launching.");
		eventHandler = new EventHandler();
		initMods();
		eventHandler.callEvent(new EventGameConstruct());
		init();
		window.show();
		info("Game loading.");
		eventHandler.callEvent(new EventGameInitialization());
		loop();
		info("Game closing.");
		eventHandler.callEvent(new EventGameClose());
		cleanup();
	}
	
	private void initMods() {
		modHandler = new GameModHandler();
		info("Loading modules...");
		modHandler.loadMods();
		try {
			modHandler.constructMods(eventHandler);
		} catch (InstantiationException | IllegalAccessException e) {
			error("Failed to construct modules.");
			e.printStackTrace();
		}
	}
	
	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) {
			error("GLFW error");
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		window = new GLFWWindow().init().setTitle("Cubulus v" + VERSION.toString()).halfScreen().centerOnScreen();
	}
	
	private void loop() {
		glClearColor(1.0f, 0.75f, 0.50f, 0.0f);
		while (!window.shouldWindowClose()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			window.update();
			glfwPollEvents();
		}
	}
	
	private void cleanup() {
		eventHandler.destroy();
		window.cleanup();
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public EventHandler getEventHandler() {
		return eventHandler;
	}
	
	public static void info(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		log.info(out);
	}
	
	public static void error(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		log.error(out);
	}
	
}