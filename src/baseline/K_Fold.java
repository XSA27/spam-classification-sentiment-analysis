package baseline;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class K_Fold {

	public Evaluation evalNaiveKFold(NaiveBayes naivebayes, Instances dataSet, String path) throws Exception {
		Results resultados = new Results();
		// kfold cross validation.
		Evaluation evaluadorKFold = new Evaluation(dataSet);
		evaluadorKFold.crossValidateModel(naivebayes, dataSet, 10, new Random(1));

		return evaluadorKFold;
	}

}
