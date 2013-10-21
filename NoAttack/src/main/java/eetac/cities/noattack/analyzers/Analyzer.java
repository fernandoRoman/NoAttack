package eetac.cities.noattack.analyzers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.core.Instances;
import eetac.cities.noattack.model.Flow;

public abstract class Analyzer<T extends Flow> {	
	Instances trainInstances;

	protected Analyzer(String trainFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(trainFile));
		trainInstances = new Instances(br);
	}
	
	protected Instances getTrainInstances() {
		return trainInstances;
	}	
	
	public abstract void analize(T flow);
	protected abstract Classifier getClassifier();
}
