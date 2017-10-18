package tech.trickwater.cubulusdos;

public class Log {
	
	public static void info(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		CubulusCore.getInstance().getLogger().info(out);
	}
	
	public static void err(Object msg) {
		String out = (msg == null) ? "null" : msg.toString();
		CubulusCore.getInstance().getLogger().error(out);
	}
	
}