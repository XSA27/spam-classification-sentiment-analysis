package GetModel;

import java.util.Random;

import arff2bow.Escritura;
import arff2bow.Lectura;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.RandomForest;
import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;

public class Barrido {
	private static Instances train;
	private static Instances dev;
	private static String exportPath;

	public static void main(String[] args) throws Exception {
		// args[0] = /path/train.arff
		// args[1] = /path/dev.arff
		// args[2] = /path/params.txt
		Lectura l = new Lectura();
		train = l.cargarDatos(args[0]);
		l = new Lectura();
		dev = l.cargarDatos(args[1]);
		exportPath = args[2];
		barrer();
	}

	// TODO de la clase minoritaria ?????
	private static void barrer() throws Exception {
		Instances trainDev = train;
		for (Instance i : dev) {
			trainDev.add(i);
		}
		
		Random rdn = new Random();
		StopWatch sw, swb;
		Evaluation evaluator;
		RandomForest forest;
		
		// Variables para el calculo
		int plus = train.numInstances() / 100; // Para avanzar en el while de arboles
		long buildTime = 0;
		long tiempoTardado = 0; // Tiempo tardado en evaluar
		double fMeasure = 0; // F-Measure va de 0 a 1
		int trees = 1; // Numero de arboles actual
		int depth = 1; // Profundidad de los arboles actual
		int features = 1; // Numero de features actual
		
		// Mejores parametros encontrados hasta el momento
		int bestTrees = 1;
		int bestDepth = 1;
		int bestFeatures = 1;
		double bestfMeasure = 0;
		
		System.out.println("Finding best parameters for this problem...");
		while (tiempoTardado < 5000) { // La evaluacion tarda menos de 5 segundos
			while (depth < Integer.MAX_VALUE) {	// Aumentamos profundidad hasta que tarde mas de 5s
				trees = 0;
				while (trees < train.numInstances() / 2) {
					features = 0;
					while (features <= 400) {
						System.out.println("Depth: " + depth);
						System.out.println("Trees: " + trees);
						System.out.println("Features: " + features);
						// Barajamos los datos
						train.randomize(rdn);
						dev.randomize(rdn);
						
						// Preparamos el clasificador
						evaluator = new Evaluation(trainDev);
						forest = new RandomForest();
						forest.setNumTrees(trees);
						forest.setMaxDepth(depth);
						forest.setNumFeatures(features);
						
						System.out.println("Empezamos a buildear el clasificador...");
						swb = new StopWatch();
						forest.buildClassifier(train);
						buildTime = swb.elapsedTime();
						System.out.println("Tiempo tardado en buildear: " + buildTime);
						
						System.out.println("Empezamos a evaluar...");
						sw = new StopWatch();
						evaluator.evaluateModel(forest, dev);
						tiempoTardado = sw.elapsedTime();
						System.out.println("Tiempo tardado en evaluar: " + tiempoTardado);
						
						fMeasure = evaluator.fMeasure(0);
						System.out.println("F-Measure: " + fMeasure);
						System.out.println("");
						System.out.println("");
						if (fMeasure > bestfMeasure) {
							bestfMeasure = fMeasure;
							bestTrees = trees;
							bestDepth = depth;
							bestFeatures = features;
						}
						features = features + 100;
					}
					trees = trees + plus;
				}	
				depth = depth + plus;
			}
		}
		String params = bestTrees + ";" + bestDepth + ";" + bestFeatures;
		Escritura e = new Escritura();
		e.escribir(params, exportPath);

		System.out.println("Done.");
		System.out.println("Best numTrees: " + bestTrees);
		System.out.println("Best numTrees: " + bestDepth);
		System.out.println("Best numTrees: " + bestFeatures);
	}
}
