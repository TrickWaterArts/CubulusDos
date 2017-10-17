package tech.trickwater.cubulusdos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.trickwater.cubulusdos.core.event.EventHandler;
import tech.trickwater.cubulusdos.core.events.EventGameClose;
import tech.trickwater.cubulusdos.core.events.EventGameConstruct;
import tech.trickwater.cubulusdos.core.events.EventGameInitialization;
import tech.trickwater.cubulusdos.core.events.EventLoopRender;
import tech.trickwater.cubulusdos.core.events.EventLoopUpdate;
import tech.trickwater.cubulusdos.core.mod.IGameMod;

public class CubulusCore implements IGameMod {
	
	private Logger logger;

	public String getName() {
		return "CubulusCore";
	}

	public void registerEvents(EventHandler eventHandler) {
		getLogger().info("Registering events...");
		
		eventHandler.addListener(EventGameConstruct.class, (e) -> {
			construct();
			getLogger().info("EventGameConstruct");
		});
		eventHandler.addListener(EventGameInitialization.class, (e) -> {
			init();
			getLogger().info("EventGameInitialization");
		});
		eventHandler.addListener(EventGameClose.class, (e) -> {
			close();
			getLogger().info("EventGameClose");
		});
		eventHandler.addListener(EventLoopUpdate.class, (e) -> {
			update();
			getLogger().info("EventLoopUpdate");
		});
		eventHandler.addListener(EventLoopRender.class, (e) -> {
			render(((EventLoopRender) e).getDelta());
			getLogger().info("EventLoopRender");
		});
		
		getLogger().info("Registered events.");
	}
	
	public Logger getLogger() {
		if (logger == null) {
			logger = LogManager.getLogger("CubulusCore");
		}
		return logger;
	}
	
	private void construct() {
		
	}
	
	private void init() {
		
	}
	
	private void close() {
		
	}
	
	private void update() {
		
	}
	
	private void render(double delta) {
		
	}
	
}