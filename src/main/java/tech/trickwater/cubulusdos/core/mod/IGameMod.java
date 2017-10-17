package tech.trickwater.cubulusdos.core.mod;

import org.apache.logging.log4j.Logger;
import tech.trickwater.cubulusdos.core.event.EventHandler;

public interface IGameMod {
	
	String getName();
	void registerEvents(EventHandler e);
	Logger getLogger();
	
}