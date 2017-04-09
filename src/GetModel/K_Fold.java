package GetModel;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class K_Fold {

	public Evaluation evalKFold(RandomForest rf, Instances dataSet) throws Exception {
		// kFold cross validation.
		Evaluation evaluadorKFold = new Evaluation(dataSet);
		evaluadorKFold.crossValidateModel(rf, dataSet, 10, new Random(1));

		return evaluadorKFold;
	}
}