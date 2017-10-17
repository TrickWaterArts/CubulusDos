package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventGameInitialization implements IEvent {
	
	public String getName() {
		return "EventGameInitialization";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}