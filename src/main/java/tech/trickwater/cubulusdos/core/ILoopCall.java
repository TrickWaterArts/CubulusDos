package tech.trickwater.cubulusdos.core;

@FunctionalInterface
public interface ILoopCall {
	
	void run(double delta);
	
}