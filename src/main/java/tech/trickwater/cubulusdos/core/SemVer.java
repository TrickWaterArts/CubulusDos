package tech.trickwater.cubulusdos.core;

public class SemVer {
	
	private int major;
	private int minor;
	private int patch;
	
	public SemVer(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	public int getMajor() {
		return major;
	}
	
	public int getMinor() {
		return minor;
	}
	
	public int getPatch() {
		return patch;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(major);
		b.append('.');
		b.append(minor);
		b.append('.');
		b.append(patch);
		return b.toString();
	}
	
}