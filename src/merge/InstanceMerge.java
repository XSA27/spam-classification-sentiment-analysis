package merge;

import arff2bow.Escribir;
import arff2bow.Lectura;
import weka.core.Instance;
import weka.core.Instances;

public class InstanceMerge {

	public static void main(String[] args) throws Exception {
		String pathTrain = args[0];
		String pathDev = args[1];
		String pathResult = args[2];
		
		Instances dataTrain;
		Instances dataDev;
		Instances trainDev;
		
		Lectura l = new Lectura();
		dataTrain = l.cargarDatos(pathTrain);
		dataDev = l.cargarDatos(pathDev);
		
		trainDev = dataTrain;
		for (Instance i : dataDev) {
			trainDev.add(i);
		}
		
		Escribir e = new Escribir();
		e.escribir(trainDev, pathResult);
	}
}
