package tech.trickwater.cubulusdos.core;

public class GameLoop {
	
	private final long NANO_PER_SEC = 1000000000L;
	private final int TARGET_FPS = 60;
	private final long TARGET_UPDATE_TIME = NANO_PER_SEC / TARGET_FPS;
	private final int UPDATE_CAP = 320;
	private final long MIN_FRAME_TIME = NANO_PER_SEC / UPDATE_CAP;
	
	private boolean running = false;
	private Runnable exit;
	private ILoopCall update;
	private Runnable render;
	
	private int fps = 0;
	private int tmpFps = 0;
	private long lastFpsTime = now();
	private long lastLoopTime = now();
	private long nextLoopTime = now() + MIN_FRAME_TIME;
	
	public void start() {
		if (!running) {
			running = true;
			initLoop();
		}
	}
	
	public void stop() {
		running = false;
	}
	
	private void initLoop() {
		while (running) {
			nextLoopTime = now() + MIN_FRAME_TIME;
			long now = now();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = ((double) updateLength) / ((double) TARGET_UPDATE_TIME);
			
			update.run(delta);
			render.run();
			
			tmpFps ++;
			if (now - lastFpsTime >= NANO_PER_SEC) {
				fps = tmpFps;
				tmpFps = 0;
				lastFpsTime = now;
			}
			
			while(now() < nextLoopTime) {
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					CoreGame.error("Failed to sleep for loop.");
					e.printStackTrace();
				}
			}
		}
		exit.run();
	}
	
	public void setOnLoopExit(Runnable exit) {
		this.exit = exit;
	}
	
	public void setOnUpdate(ILoopCall update) {
		this.update = update;
	}
	
	public void setOnRender(Runnable render) {
		this.render = render;
	}
	
	public int getFps() {
		return fps;
	}
	
	public long now() {
		return System.nanoTime();
	}
	
}