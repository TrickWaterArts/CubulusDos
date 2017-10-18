package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventLoopUpdate implements IEvent {
	
	private double delta;
	
	public EventLoopUpdate(double delta) {
		this.delta = delta;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public String getName() {
		return "EventLoopUpdate";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}