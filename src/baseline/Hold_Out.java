package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Hold_Out {
	
	public Evaluation evalNaiveHoldOut(NaiveBayes naivebayes,Instances dataSet,String path) throws Exception {
		
		//creamos la particion para el holdout
		int trainSize = (int) Math.round(dataSet.numInstances() * 0.7);
		int testSize = dataSet.numInstances() - trainSize;
		
		Instances train = new Instances(dataSet, 0, trainSize);
		Instances test = new Instances(dataSet, trainSize, testSize);
		
		//cogemos los archivos para crear el holdout
		naivebayes.buildClassifier(train);
		
		//creamos un evaluador
		Evaluation evaluador = new Evaluation(train);
		evaluador.evaluateModel(naivebayes, test);

		return evaluador;
	}
	

}
