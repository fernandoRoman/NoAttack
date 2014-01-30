package eetac.cities.noattack.analyzers;

import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import eetac.cities.noattack.args.ArgInput;
import eetac.cities.noattack.args.Args;
import eetac.cities.noattack.exception.AnalyzerInitializationException;
import eetac.cities.noattack.model.FlowTCP;
import eetac.cities.noattack.model.weka.FlowTcpInstance;

public class TCPAnalyzer extends Analyzer<FlowTCP> {
	private static TCPAnalyzer instance;
	private static final String TRAIN_FILE = "tcp-flows.arff";
	private Classifier classifier;
	
	public TCPAnalyzer(String trainFile) throws IOException {
		super(trainFile);
	}

	@Override
	public void analize(FlowTCP flow) {
		Classifier tcpClassifier = getClassifier();
		
		FlowTcpInstance flowInstance = new FlowTcpInstance(flow);
		flowInstance.makeEmptyDataset();
		
		double klass;
		try {
			klass = tcpClassifier.classifyInstance(flowInstance);
		} catch (Exception e) {
			throw new RuntimeException("Can't classify the instance.", e);
		}
		
		System.out.println(flow);
		System.out.println();
		System.out.println("Attack class: " + FlowTcpInstance.getFlowClass((int)klass));
	}
	
	@Override
	protected Classifier getClassifier() {
		/* We select this classifier due to:
		 *  TP Rate   FP Rate   Precision   Recall  F-Measure   ROC Area  Class
         *  0.75      0.042     0.857       0.75    0.8         0.813     SAFE
         *  1         0         1           1       1           1         SYN_ATTACK
         *  1         0.042     0.889       1       0.941       0.958     RST_ATTACK
         *  0.875     0.042     0.875       0.875   0.875       0.917     DoS_ATTACK
         *  0.906     0.031     0.905       0.906   0.904       0.922     Weighted Avg.
		 * 
		 */
		
		if(classifier == null) {
			classifier = new J48();
			try {
				getTrainInstances().setClassIndex(getTrainInstances().numAttributes() - 1);
				classifier.buildClassifier(getTrainInstances());
			} catch (Exception e) {
				throw new RuntimeException("Can't initialize the classifier.", e);
			}	
		}
				
		return classifier;
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