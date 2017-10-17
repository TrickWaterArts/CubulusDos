package tech.trickwater.cubulusdos.core.event;

public class EventHandle {
	
	private Class<? extends IEvent> event;
	private IListener listener;
	
	public EventHandle(Class<? extends IEvent> event, IListener listener) {
		this.event = event;
		this.listener = listener;
	}
	
	public Class<? extends IEvent> getEvent() {
		return event;
	}
	
	public IListener getListener() {
		return listener;
	}
	
}