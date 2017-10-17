package tech.trickwater.cubulusdos.core;

public class GameLoop {
	
	// 1s = 1000000000ns
	
	private boolean running = false;
	private Runnable exit;
	private Runnable update;
	private ILoopCall render;
	
	private int fps = 60;
	private int trueFps;
	private long start = System.nanoTime();
	private long frameDuration = 1000000000L / fps;
	private int lag;
	
	public void start() {
		running = true;
		initLoop();
	}
	
	public void stop() {
		running = false;
	}
	
	private void initLoop() {
		while (running) {
			long current = System.nanoTime();
			long elapsed = current - start;
			start = current;
			lag += elapsed;
			while (lag >= frameDuration) {
				update.run();
				lag -= frameDuration;
			}
			double lagOffset = (double) lag / (double) frameDuration;
			render.run(lagOffset);
			trueFps = (int) Math.floor(1000000000D / (double) elapsed);
		}
		exit.run();
	}
	
	public void setOnLoopExit(Runnable exit) {
		this.exit = exit;
	}
	
	public void setOnUpdate(Runnable update) {
		this.update = update;
	}
	
	public void setOnRender(ILoopCall render) {
		this.render = render;
	}
	
	public int getFps() {
		return trueFps;
	}
	
}