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
import tech.trickwater.cubulusdos.core.events.EventLoopRender;
import tech.trickwater.cubulusdos.core.events.EventLoopUpdate;
import tech.trickwater.cubulusdos.core.mod.GameModHandler;
import tech.trickwater.cubulusdos.core.window.GLFWWindow;

public class CoreGame {
	
	public static final SemVer VERSION = new SemVer(0, 0, 1);
	private static final Logger log = LogManager.getLogger("CORE");
	private static final CoreGame INSTANCE;
	
	static {
		INSTANCE = new CoreGame();
	}
	
	private GLFWWindow window;
	private EventHandler eventHandler;
	private GameModHandler modHandler;
	private GameLoop gameLoop;
	
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
			throw new IllegalStateException("Unable to initialize GLFW.");
		}
		window = new GLFWWindow().init().setTitle("Cubulus v" + VERSION.toString()).halfScreen().centerOnScreen();
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	private void loop() {
		gameLoop = new GameLoop();
		gameLoop.setOnUpdate((d) -> update(d));
		gameLoop.setOnRender(() -> render());
		gameLoop.setOnLoopExit(() -> {
			info("Game closing.");
			eventHandler.callEvent(new EventGameClose());
			cleanup();
		});
		gameLoop.start();
	}
	
	private void update(double delta) {
		eventHandler.callEvent(new EventLoopUpdate(delta));
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		eventHandler.callEvent(new EventLoopRender());
		window.update();
		glfwPollEvents();
		if (window.shouldWindowClose()) {
			gameLoop.stop();
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
	
	public int getFps() {
		return gameLoop.getFps();
	}
	
	public static void info(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		log.info(out);
	}
	
	public static void error(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		log.error(out);
	}
	
	public static CoreGame getInstance() {
		return INSTANCE;
	}
	
}