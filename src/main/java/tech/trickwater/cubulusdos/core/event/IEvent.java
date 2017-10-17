package tech.trickwater.cubulusdos.core.event;

public interface IEvent {
	
	String getName();
	void cancel();
	boolean isCancelled();
	
}