package tech.trickwater.cubulusdos.core;

public class Bootstrap {
	
	public static void main(String[] args) {
		CoreGame.getInstance().onGameLaunch(args);
	}
	
}