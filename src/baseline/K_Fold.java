package baseline;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class K_Fold {

	public void evalNaiveKFold(NaiveBayes naivebayes, Instances dataSet) throws Exception {
		Results resultados = new Results();
		// kfold cross validation.
		Evaluation evaluadorKFold = new Evaluation(dataSet);
		evaluadorKFold.crossValidateModel(naivebayes, dataSet, 10, new Random(1));
		System.out.println("metodo  kfold");
		resultados.imprimirResultados(evaluadorKFold);
		System.out.println("");
	}

}
