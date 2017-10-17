package tech.trickwater.cubulusdos.core.mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import tech.trickwater.cubulusdos.core.CubulusDos;
import tech.trickwater.cubulusdos.core.event.EventHandler;

public class GameModHandler {
	
	private List<Class<? extends IGameMod>> loadedMods;
	
	public GameModHandler() {
		loadedMods = new ArrayList<>();
	}
	
	public void loadMods() {
		Reflections refs = new Reflections("");
		Set<Class<? extends IGameMod>> mods = refs.getSubTypesOf(IGameMod.class);
		loadedMods.addAll(mods);
	}
	
	public void constructMods(EventHandler e) throws InstantiationException, IllegalAccessException {
		for (Class<? extends IGameMod> mod : loadedMods) {
			IGameMod cMod = mod.newInstance();
			if (cMod != null) {
				CubulusDos.info("Loading: " + cMod.getName());
				cMod.registerEvents(e);
			}
		}
	}
	
}