package baseline;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Hold_Out {
	
	public void evalNaiveHoldOut(NaiveBayes naivebayes,Instances dataSet) throws Exception{
		Results resultados = new Results();
	
		//creamos la particion para el holdout
		int trainSize = (int) Math.round(dataSet.numInstances() * 0.7);
		int testSize = dataSet.numInstances() - trainSize;
		
		Instances train = new Instances(dataSet, 0, trainSize);
		Instances test = new Instances(dataSet, trainSize, testSize);
		
		
		//cogemos los archivos para crear el holdout
		naivebayes.buildClassifier(train);
		
		//creamos un evaluador
		Evaluation evaluador = new Evaluation(train);
		evaluador.evaluateModel(naivebayes, test);
		
		System.out.println("metodo hold out");
		resultados.imprimirResultados(evaluador);
		System.out.println("");
	}
	

}
