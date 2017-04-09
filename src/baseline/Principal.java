package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Principal {

	public static void main(String[] args) throws Exception {
		String path = args[1];
		Results resultados = new Results();

		Lectura lect = new Lectura();

		Hold_Out holdOut = new Hold_Out();
		No_Honesto noHon = new No_Honesto();
		K_Fold kFold = new K_Fold();

		Instances dataSet;
		dataSet = lect.leerFichero(args[0]);

		System.out.println("Utilizando el naiveBayes para el baseline...");
		NaiveBayes naivebayes = new NaiveBayes();

		Evaluation holdOutEv = holdOut.evalNaiveHoldOut(naivebayes, dataSet);
		Evaluation noHonEv = noHon.evalNaiveNoHon(naivebayes, dataSet);
		Evaluation kFoldEv = kFold.evalNaiveKFold(naivebayes, dataSet);

		resultados.imprimirResultados(holdOutEv, noHonEv, kFoldEv, path);
	}
}
