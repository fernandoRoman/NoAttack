package eetac.cities.noattack.args;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

import eetac.cities.noattack.exception.ArgsException;
import eetac.cities.noattack.model.Flow;
import eetac.cities.noattack.model.FlowTCP;

public class Args implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String argSplitter = "=";
	private static final Args instance = new Args();
	private Map<String, String> argsMap;
	
	
	private Args() {
		argsMap = new HashMap<String, String>();
	}
	
	private void processData(String[] args) {
		try {
			for (String arg : args) {
				String[] splittedArg = arg.split(argSplitter);
				argsMap.put(splittedArg[0], splittedArg[1]);
			}
		} catch (Exception e) {
			throw new ArgsException("Could not load args", e);
		}
		
	}
	
	public static void setArgs(String[] args) {
		instance.processData(args);
	}
	
	public static String get(ArgInput argument) {
		return instance.argsMap.get(argument.getKey());
	}
	
	public static List<Flow> getInputFlows() {
		List<Flow> flows = new ArrayList<Flow>();
		if(get(ArgInput.INPUT_TYPE).equals("file")) {
			try {
				FileInputStream fis = new FileInputStream(get(ArgInput.INPUT));
				Flow flow = FlowTCP.fromBoundle(new PropertyResourceBundle(fis));
				flows.add(flow);
			} catch (Exception e) {
				throw new ArgsException("Could not get the flows", e);
			}		
		}
		
		return flows;
		
	}
}
