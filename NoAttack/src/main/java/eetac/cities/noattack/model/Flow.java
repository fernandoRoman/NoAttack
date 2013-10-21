package eetac.cities.noattack.model;

public interface Flow {
	public static final String pkg = "eetac.cities.noattack.model";
	
	public String getDstAddress();
	public float getTime();
	public int getPacketNumber();
	public float getPacketRate();
}
