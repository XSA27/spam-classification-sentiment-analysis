package GetModel;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Hold_Out {

	public Evaluation evalHoldOut(RandomForest rf, Instances dataSet) throws Exception {
		RandomForest forest = rf;
		// Creamos la particion para el holdout
		int trainSize = (int) Math.round(dataSet.numInstances() * 0.7);
		int testSize = dataSet.numInstances() - trainSize;

		Instances train = new Instances(dataSet, 0, trainSize);
		Instances test = new Instances(dataSet, trainSize, testSize);

		// Cogemos los archivos para crear el holdout
		forest.buildClassifier(train);

		// Creamos un evaluador
		Evaluation evaluador = new Evaluation(train);
		evaluador.evaluateModel(forest, test);

		return evaluador;
	}
}