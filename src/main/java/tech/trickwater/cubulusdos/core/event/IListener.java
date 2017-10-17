package tech.trickwater.cubulusdos.core.event;

@FunctionalInterface
public interface IListener {
	
	void eventCall(IEvent event);
	
}