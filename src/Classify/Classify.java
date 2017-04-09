package Classify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Classify {
	
	public static void main(String[] args) throws Exception {
		String testPath = args[0];
		String modelPath = args[1];
		String exportPath = args[2];
		
		System.out.println("Cargando datos...\n");
		Lectura lec = new Lectura();
		Instances dataTest = lec.leerFichero(testPath);
		
		System.out.println("Cargando el modelo...\n");
		Classifier classifier = (Classifier) SerializationHelper.read(modelPath);
		
		System.out.println("Clasificacion el test...\n");
		File fout = new File(exportPath);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));	
		
		bw.write("Predictions for the test: ");
		bw.newLine();
		int i = 0;
		for (Instance c : dataTest){
			bw.write("  Instance " + i + ":  " + ((classifier.classifyInstance(c) == 0.0) ? "ham" : "spam"));
			i++;
			bw.newLine();
		}
		bw.close();
		System.out.println("Archivo " + exportPath + " creado");
	}
}
