package tech.trickwater.cubulusdos.core.events;

import tech.trickwater.cubulusdos.core.event.IEvent;

public class EventLoopRender implements IEvent {
	
	private double delta;
	
	public EventLoopRender(double delta) {
		this.delta = delta;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public String getName() {
		return "EventLoopRender";
	}
	
	public void cancel() {
	}
	
	public boolean isCancelled() {
		return false;
	}
	
}