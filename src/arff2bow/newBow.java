package arff2bow;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class newBow {
	private static Instances train;
	private static Instances dev;
	private static String pathTrain;
	private static String pathDev;
	private static String pathTrainDev;
	private static Instances trainBowed;
	private static Instances devBowed;
	private static Instances filteredTrain;
	private static Instances filteredDev;

	public static void main(String[] args) throws Exception {
		System.out.println("Rutas de origen seleccionadas:");
		System.out.println("  Train: " + args[0]);
		System.out.println("  Dev: " + args[1]);
		System.out.println("Rutas de destino seleccionadas:");
		System.out.println("  TrainFiltered: " + args[2]);
		System.out.println("  DevFiltered: " + args[3]);
		System.out.println("  Train+DevFiltered: " + args[4]);

		System.out.println("\nCargando archivos...");

		// data
		Lectura l = new Lectura();
		train = l.cargarDatos(args[0]);
		dev = l.cargarDatos(args[1]);

		// paths para exportar
		pathTrain = args[2];
		pathDev = args[3];
		pathTrainDev = args[4];

		bow();
		fssInfoGain();
		guardar();
	}

	private static void bow() throws Exception {
		System.out.println("\nAplicando filtros...");
		StringToWordVector stwv = new StringToWordVector();

		// stwv.setIDFTransform(true); // Por defecto: false.
		// stwv.setTFTransform(true); // Por defecto: false.
		stwv.setWordsToKeep(2000);
		stwv.setOutputWordCounts(true);
		stwv.setInputFormat(train);
		stwv.isNewBatch(); // Forzar compatibilidad de las instancias de salida

		trainBowed = Filter.useFilter(train, stwv);
		devBowed = Filter.useFilter(dev, stwv);
	}

	private static void fssInfoGain() throws Exception {
		AttributeSelection filter = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();

		Ranker search = new Ranker();
		search.setThreshold(0);

		filter.setEvaluator(eval);
		filter.setSearch(search);
		filter.setInputFormat(trainBowed);

		filteredTrain = Filter.useFilter(trainBowed, filter);
		filteredDev = Filter.useFilter(devBowed, filter);
	}

	private static void guardar() throws Exception {
		Escribir e = new Escribir();
		e.escribir(filteredTrain, pathTrain);
		e.escribir(filteredDev, pathDev);
		
		Merge m = new Merge();
		m.merge(filteredTrain, filteredDev, pathTrainDev);

		System.out.println("\nArchivo " + pathTrain + " creado.");
		System.out.println("Archivo " + pathDev + " creado.");
		System.out.println("Archivo " + pathTrainDev + " creado");
	}
}
