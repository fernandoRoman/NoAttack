package eetac.cities.noattack.args.conf;

import java.io.FileInputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import eetac.cities.noattack.args.ArgInput;
import eetac.cities.noattack.args.Args;
import eetac.cities.noattack.exception.ArgsException;
import eetac.cities.noattack.model.Flow;

public class AnalyzersConfig {
	private static final AnalyzersConfig instance = new AnalyzersConfig();
	private static final String ANALYZER = "analyzers";
	private static final String FILE = "analyzers.conf";
	ResourceBundle properties;
	
	private AnalyzersConfig() {
		try {
			properties = new PropertyResourceBundle(new FileInputStream(Args.get(ArgInput.CONF_FOLDER) + "\\" + FILE));
		} catch (Exception e) {
			throw new ArgsException("Could not load the configuration from analyzers.", e);
		}
	}
	
	public static String getAnalyzer(Class<? extends Flow> flow) {
		return instance.properties.getString(ANALYZER + "." + flow.getSimpleName());
	}
	
	public static AnalyzersConfig get() {
		return instance;
	}
}
