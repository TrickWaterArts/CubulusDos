package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventGameConstruct implements IEvent {
	
	public String getName() {
		return "EventGameConstruct";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}