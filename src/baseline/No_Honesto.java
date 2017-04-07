package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class No_Honesto {

	public void evalNaiveNoHon(NaiveBayes naivebayes, Instances dataSet) throws Exception {
		Results resultados = new Results();
		// sistema no honesto
		Evaluation evaluadorNoHon = new Evaluation(dataSet);
		naivebayes.buildClassifier(dataSet);
		evaluadorNoHon.evaluateModel(naivebayes, dataSet);

		System.out.println("metodo no honesto");
		resultados.imprimirResultados(evaluadorNoHon);
		System.out.println("");
	}
}
