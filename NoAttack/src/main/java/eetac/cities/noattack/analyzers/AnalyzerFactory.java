package eetac.cities.noattack.analyzers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import eetac.cities.noattack.args.conf.AnalyzersConfig;
import eetac.cities.noattack.exception.ArgsException;
import eetac.cities.noattack.model.Flow;

public class AnalyzerFactory {

	@SuppressWarnings("unchecked")
	public static void analyze(Flow flow) {
		Class<Analyzer<?>> analyzerKlass;
		try {
			analyzerKlass = (Class<Analyzer<?>>) Class.forName(AnalyzersConfig.getAnalyzer(flow.getClass()));
		} catch (ClassNotFoundException e) {
			throw new ArgsException("Could not load the class for the flow " + flow.getClass().getSimpleName(), e);
		}
		
		Method method;
		try {
			method = analyzerKlass.getMethod("getInstance");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return;
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		}
		
		Analyzer<Flow> analyzer;
		try {
			analyzer = (Analyzer<Flow>) method.invoke(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return;
		}
		
		analyzer.analize(flow);
	}
	
}
