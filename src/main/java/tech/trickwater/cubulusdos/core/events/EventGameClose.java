package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventGameClose implements IEvent {
	
	public String getName() {
		return "EventGameClose";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}