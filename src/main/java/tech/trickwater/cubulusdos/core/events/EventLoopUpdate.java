package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventLoopUpdate implements IEvent {
	
	public String getName() {
		return "EventLoopUpdate";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}