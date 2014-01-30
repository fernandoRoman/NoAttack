package eetac.cities.noattack.model.weka;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import eetac.cities.noattack.model.FlowTCP;

public class FlowTcpInstance extends Instance {
	private static final long serialVersionUID = -1018166285338979883L;
	public static final Attribute DST_PORT = new Attribute("Dst_Port", 0);
	public static final Attribute PACKET_COUNT = new Attribute("PACKET_Count", 1);
	public static final Attribute FLOW_TIME = new Attribute("FLOW_Time", 2);
	public static final Attribute PPS_COUNT = new Attribute("PPS_Count", 3);
	public static final Attribute SYN_COUNT = new Attribute("SYN_Count", 4);
	public static final Attribute RST_COUNT = new Attribute("RST_Count", 5);
	public static final Attribute PORT_COUNT = new Attribute("PORT_Count", 6);
	public static final Attribute CLASS = new Attribute("class", getClasses(), 7);
	
	public static final String SAFE_CLASS = "SAFE";
	public static final String SYN_ATTACK_CLASS = "SYN_ATTACK";
	public static final String RST_ATTACK_CLASS = "RST_ATTACK";
	public static final String DOS_ATTACK_CLASS = "DoS_ATTACK";
	public static final String[] CLASSES = new String[] {
		SAFE_CLASS, 
		SYN_ATTACK_CLASS,
		RST_ATTACK_CLASS,
		DOS_ATTACK_CLASS
	};
	
	public static final FastVector attributes = getAttributes();
	
	public FlowTcpInstance(FlowTCP instance) {
		super(getAttributes().size());
		setValue(DST_PORT, instance.getPort());
		setValue(PACKET_COUNT, instance.getPacketNumber());
		setValue(FLOW_TIME, instance.getTime());
		setValue(PPS_COUNT, instance.getPacketRate());
		setValue(SYN_COUNT, instance.getSynNumber());
		setValue(RST_COUNT, instance.getRstNumber());
		setValue(PORT_COUNT, instance.getPortCount());
		setValue(CLASS, SAFE_CLASS);
	}

	private static FastVector getClasses() {
		FastVector classes = new FastVector(4);
		classes.addElement(SAFE_CLASS);
		classes.addElement(SYN_ATTACK_CLASS);
		classes.addElement(RST_ATTACK_CLASS);
		classes.addElement(DOS_ATTACK_CLASS);
		return classes;
	}

	private static FastVector getAttributes() {
		FastVector attributes = new FastVector(8);
		
		attributes.addElement(DST_PORT);
		attributes.addElement(PACKET_COUNT);
		attributes.addElement(FLOW_TIME);
		attributes.addElement(PPS_COUNT);
		attributes.addElement(SYN_COUNT);
		attributes.addElement(RST_COUNT);
		attributes.addElement(PORT_COUNT);
		attributes.addElement(CLASS);
		
		return attributes;
	}

	public void makeEmptyDataset() {
		Instances dataset = new Instances("FlowTcpInstance", attributes, 1);
		dataset.setClassIndex(dataset.numAttributes() - 1);
		setDataset(dataset);
	}
	
	public static String getFlowClass(int index) {
		return CLASSES[index];
	}
	
}
