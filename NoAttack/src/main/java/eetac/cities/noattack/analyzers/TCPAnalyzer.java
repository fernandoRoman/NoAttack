package eetac.cities.noattack.analyzers;

import java.io.IOException;

import weka.classifiers.Classifier;
import eetac.cities.noattack.args.ArgInput;
import eetac.cities.noattack.args.Args;
import eetac.cities.noattack.exception.AnalyzerInitializationException;
import eetac.cities.noattack.model.FlowTCP;

public class TCPAnalyzer extends Analyzer<FlowTCP> {
	private static TCPAnalyzer instance;
	private static final String TRAIN_FILE = "tcp-flows.arff";
	
	public TCPAnalyzer(String trainFile) throws IOException {
		super(trainFile);
	}

	@Override
	public void analize(FlowTCP flow) {
		System.out.println(flow);
		System.out.println("No attack detected");
	}
	
	@Override
	protected Classifier getClassifier() {
		return null;
	}
	
	public static TCPAnalyzer getInstance() {
		if(instance == null) {
			try {
				instance = new TCPAnalyzer(Args.get(ArgInput.DATA_FOLDER) + "\\" + TRAIN_FILE);
			} catch (IOException e) {
				throw new AnalyzerInitializationException("The analyzer could not be initialized", e);
			}
		}
		return instance;
	}
	
}