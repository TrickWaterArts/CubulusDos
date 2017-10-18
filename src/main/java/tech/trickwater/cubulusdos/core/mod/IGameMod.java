package tech.trickwater.cubulusdos.core.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.trickwater.cubulusdos.core.event.EventHandler;

public abstract class IGameMod {
	
	private Logger logger;
	
	public abstract String getName();
	public abstract void registerEvents(EventHandler e);
	
	public final Logger getLogger() {
		if (logger == null) {
			logger = LogManager.getLogger(getName());
		}
		return logger;
	}
	
}