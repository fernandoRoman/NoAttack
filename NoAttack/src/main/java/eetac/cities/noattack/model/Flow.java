package eetac.cities.noattack.model;


public interface Flow {
	
	public String getDstAddress();
	public float getTime();
	public int getPacketNumber();
	public float getPacketRate();
}
