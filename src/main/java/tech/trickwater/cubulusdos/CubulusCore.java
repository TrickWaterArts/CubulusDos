package tech.trickwater.cubulusdos;

import tech.trickwater.cubulusdos.core.event.EventHandler;
import tech.trickwater.cubulusdos.core.events.EventGameClose;
import tech.trickwater.cubulusdos.core.events.EventGameConstruct;
import tech.trickwater.cubulusdos.core.events.EventGameInitialization;
import tech.trickwater.cubulusdos.core.events.EventLoopRender;
import tech.trickwater.cubulusdos.core.events.EventLoopUpdate;
import tech.trickwater.cubulusdos.core.mod.IGameMod;

public class CubulusCore extends IGameMod {
	
	private static CubulusCore self;
	
	public CubulusCore() {
		self = this;
	}

	public String getName() {
		return "Cubulus";
	}

	public void registerEvents(EventHandler eventHandler) {
		getLogger().info("Registering listeners...");
		
		eventHandler.addListener(EventGameConstruct.class, (e) -> GameManager.construct());
		eventHandler.addListener(EventGameInitialization.class, (e) -> GameManager.init());
		eventHandler.addListener(EventGameClose.class, (e) -> GameManager.close());
		eventHandler.addListener(EventLoopUpdate.class, (e) -> GameManager.update(((EventLoopUpdate) e).getDelta()));
		eventHandler.addListener(EventLoopRender.class, (e) -> GameManager.render());
		
		getLogger().info("Registered core listeners.");
	}
	
	public static CubulusCore getInstance() {
		return self;
	}
	
}