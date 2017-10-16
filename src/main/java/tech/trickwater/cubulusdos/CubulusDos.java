package tech.trickwater.cubulusdos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CubulusDos {
	
	private final Logger logger;
	
	public CubulusDos() {
		logger = LogManager.getLogger("Cubulus");
	}
	
	public void onGameLaunch(String[] args) {
		logger.info("Cubulus launched.");
	}
	
}