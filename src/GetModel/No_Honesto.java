package GetModel;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class No_Honesto {

	public Evaluation evalNoHon(RandomForest rf, Instances dataSet) throws Exception {
		RandomForest forest = rf;
		// Sistema no honesto
		Evaluation evaluadorNoHon = new Evaluation(dataSet);
		forest.buildClassifier(dataSet);
		evaluadorNoHon.evaluateModel(forest, dataSet);

		return evaluadorNoHon;
	}
}