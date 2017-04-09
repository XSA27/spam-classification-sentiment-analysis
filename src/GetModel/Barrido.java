package GetModel;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;

public class Barrido {
	private static Instances train;
	private static Instances dev;
	private static String exportPath;

	public static void main(String[] args) throws Exception {
		System.out.println("Ruta del Train: " + args[0]);
		System.out.println("Ruta del Dev: " + args[1]);
		System.out.println("Ruta de salida de parametros: " + args[2]);
		
		Lectura l = new Lectura();
		train = l.leerFichero(args[0]);
		l = new Lectura();
		dev = l.leerFichero(args[1]);
		exportPath = args[2];
		
		barrer();
	}

	private static void barrer() throws Exception {
		// Calcular la clase minoritaria
		int contOne = 0;
		for (Instance i : train) {
			if (i.classValue() == 1.0)
				contOne++;
		}
		int minClass = (contOne > train.numInstances() / 2) ? 0 : 1;

		StopWatch sw, swb, st;
		Evaluation evaluator;
		RandomForest forest;

		// Variables para el calculo
		int plusTree = train.numInstances() / 1000; // Para avanzar en el while
													// de arboles
		int plusDepth = train.numAttributes() / 100;
		long buildTime = 0; // Tiempo tardado en buildear
		long tiempoTardado = 0; // Tiempo tardado en evaluar
		double fMeasure = 0; // F-Measure va de 0 a 1
		int trees = train.numInstances() / 1000; // Numero de arboles actual
		int depth = train.numAttributes() / 100; // Profundidad de los arboles
													// actual

		// Mejores parametros encontrados hasta el momento
		int bestTrees = train.numInstances() / 1000;
		int bestDepth = train.numAttributes() / 100;
		double bestfMeasure = 0;

		System.out.println("\nBuscando los mejores parametros para el problema...\n");
		st = new StopWatch();
		while (depth < train.numAttributes() / 10) {
			trees = train.numInstances() / 1000;
			while (trees < train.numInstances() / 100) {
				System.out.println("Parametros actuales: numTrees " + trees + " & maxDepth " + depth + "");

				evaluator = new Evaluation(train);
				forest = new RandomForest();
				forest.setNumTrees(trees);
				forest.setMaxDepth(depth);

				System.out.println("Empezamos a buildear el clasificador...");
				swb = new StopWatch();
				forest.buildClassifier(train);
				buildTime = swb.elapsedTime();
				System.out.println("Tiempo tardado en buildear: " + buildTime + "ms");

				System.out.println("Empezamos a evaluar...");
				sw = new StopWatch();
				evaluator.evaluateModel(forest, dev);
				tiempoTardado = sw.elapsedTime();
				System.out.println("Tiempo tardado en evaluar: " + tiempoTardado + "ms");

				fMeasure = evaluator.fMeasure(minClass);
				System.out.println("F-Measure: " + fMeasure + "\n\n");

				if (fMeasure > bestfMeasure) {
					bestfMeasure = fMeasure;
					bestTrees = trees;
					bestDepth = depth;
				}
				trees = trees + plusTree;
			}
			depth = depth + plusDepth;
		}
		String params = bestTrees + ";" + bestDepth;
		Escritura e = new Escritura();
		e.escribir(params, exportPath);

		System.out.println("Done.");
		System.out.println("Best numTrees: " + bestTrees);
		System.out.println("Best numDepth: " + bestDepth);
		System.out.println("Best fMeasure: " + bestfMeasure);
		System.out.println("Tiempo total tardado: " + st.elapsedTime() + "ms");
	}
}