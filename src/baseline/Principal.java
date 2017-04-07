package baseline;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Principal {

	public static void main(String[] args) throws Exception {
		Lectura lect = new Lectura();

		Hold_Out holdOut = new Hold_Out();
		No_Honesto noHon = new No_Honesto();
		K_Fold kFold = new K_Fold();
		///////////// Lectura de datos y aplica el filtro RANDOMIZE/////////////
		Instances dataSet;
		dataSet = lect.cargarDatos(args[0]);

		System.out.println("Creamos el naiveBayes para el baseline");
		// creamos el clasificador para baseline, hemos cogido el naivebayes
		NaiveBayes naivebayes = new NaiveBayes();

		holdOut.evalNaiveHoldOut(naivebayes, dataSet);
		noHon.evalNaiveNoHon(naivebayes, dataSet);
		kFold.evalNaiveKFold(naivebayes, dataSet);
	}
}
