package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class No_Honesto {

	public Evaluation evalNaiveNoHon(NaiveBayes naivebayes, Instances dataSet,String path) throws Exception {
		// sistema no honesto
		Evaluation evaluadorNoHon = new Evaluation(dataSet);
		naivebayes.buildClassifier(dataSet);
		evaluadorNoHon.evaluateModel(naivebayes, dataSet);

		return evaluadorNoHon;
	}
}
