package tech.trickwater.cubulusdos.core.event;

import java.util.ArrayList;
import java.util.List;
import tech.trickwater.cubulusdos.core.CoreGame;

public class EventHandler {
	
	private List<EventHandle> handles;
	
	public EventHandler() {
		handles = new ArrayList<>();
	}
	
	public void addListener(Class<? extends IEvent> event, IListener listener) {
		handles.add(new EventHandle(event, listener));
	}
	
	public void callEvent(IEvent event) {
		for (EventHandle handle : handles) {
			if (handle.getEvent().equals(event.getClass())) {
				handle.getListener().eventCall(event);
			}
			if (event.isCancelled()) {
				break;
			}
		}
	}
	
	public void destroy() {
		handles.clear();
		CoreGame.info("Destroying event handler.");
	}
	
}