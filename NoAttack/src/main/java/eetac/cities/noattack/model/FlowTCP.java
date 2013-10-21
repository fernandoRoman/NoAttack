package eetac.cities.noattack.model;

import java.io.Serializable;
import java.util.ResourceBundle;

import eetac.cities.noattack.analyzers.Analyzer;
import eetac.cities.noattack.exception.NoAttackFileException;


public class FlowTCP implements Flow, Serializable {
	private static final long serialVersionUID = -382874556276816710L;
	
	public static final String DST_ADDRESS_FIELD = "dstAddress";
	public static final String PORT_FIELD = "port";
	public static final String SYN_NUMBER_FIELD = "synNumber";
	public static final String RST_NUMBER_FIELD = "rstNumber";
	public static final String TIME_FIELD = "time";
	public static final String PACKET_NUBMER_FIELD = "packetNumber";
	public static final String PACKET_RATE_FIELD = "packetRate";
	
	private String dstAddress;
	private int port;
	private int synNumber;
	private int rstNumber;
	private float time;
	private int packetNumber;
	private float packetRate;
	
	public FlowTCP(String dstAddress, int port, int synNumber, int rstNumber, float time, int packetNumber) {
		super();
		this.dstAddress = dstAddress;
		this.time = time;
		this.packetNumber = packetNumber;
		this.packetRate = packetNumber / time;
		this.port = port;
		this.synNumber = synNumber;
		this.rstNumber = rstNumber;
	}
	
	public String getDstAddress() {
		return dstAddress;
	}
	public void setDstAddress(String dstAddress) {
		this.dstAddress = dstAddress;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	
	public int getSynNumber() {
		return synNumber;
	}
	public void setSynNumber(int synNumber) {
		this.synNumber = synNumber;
	}
	
	public int getRstNumber() {
		return rstNumber;
	}
	public void setRstNumber(int rstNumber) {
		this.rstNumber = rstNumber;
	}

	public int getPacketNumber() {
		return packetNumber;
	}
	public void setPacketNumber(int packetNumber) {
		this.packetNumber = packetNumber;
	}
	
	public float getPacketRate() {
		return packetRate;
	}
	public void setPacketRate(float packetRate) {
		this.packetRate = packetRate;
	}
	
	public static FlowTCP fromBoundle(ResourceBundle boundle) throws NoAttackFileException {
		try {
			return new FlowTCP(
					boundle.getString(DST_ADDRESS_FIELD), 
					Integer.parseInt(boundle.getString(PORT_FIELD)), 
					Integer.parseInt(boundle.getString(SYN_NUMBER_FIELD)), 
					Integer.parseInt(boundle.getString(RST_NUMBER_FIELD)), 
					Float.parseFloat(boundle.getString(TIME_FIELD)), 
					Integer.parseInt(boundle.getString(PACKET_NUBMER_FIELD))
			);
		} catch (Exception e) {
			throw new NoAttackFileException("Could not load this file", e);
		}
	}

	@Override
	public String toString() {
		return "FlowTCP [dstAddress=" + dstAddress + ", port=" + port
				+ ", synNumber=" + synNumber + ", rstNumber=" + rstNumber
				+ ", time=" + time + ", packetNumber=" + packetNumber
				+ ", packetRate=" + packetRate + "]";
	}

	public Analyzer<FlowTCP> getAnalyzer() {
		return null;
	}
}
