package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Hold_Out {

	public Evaluation evalNaiveHoldOut(NaiveBayes naivebayes, Instances dataSet) throws Exception {
		// Creamos la particion para el holdout
		int trainSize = (int) Math.round(dataSet.numInstances() * 0.7);
		int testSize = dataSet.numInstances() - trainSize;

		Instances train = new Instances(dataSet, 0, trainSize);
		Instances test = new Instances(dataSet, trainSize, testSize);

		// Cogemos los archivos para crear el holdout
		naivebayes.buildClassifier(train);

		// Creamos un evaluador
		Evaluation evaluador = new Evaluation(train);
		evaluador.evaluateModel(naivebayes, test);

		return evaluador;
	}
}