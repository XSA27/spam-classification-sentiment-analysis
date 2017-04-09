package GetModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class GenerateModel {

	public static void main(String[] args) throws Exception {
		Lectura l = new Lectura();
		Instances data = l.leerFichero(args[0]);
		ArrayList<Integer> listaParametros = getParameters(args[1]);
		String exportPath = args[2] + ".model";
		String estimation = args[3];

		System.out.println("Generando el modelo y estimacion de calidad...");
		// create model
		RandomForest rf = new RandomForest();
		rf.setNumTrees(listaParametros.get(0));
		rf.setMaxDepth(listaParametros.get(1));

		// data = train + dev
		rf.buildClassifier(data);

		SerializationHelper.write(exportPath, rf);

		Results resultados = new Results();
		Hold_Out holdOut = new Hold_Out();
		No_Honesto noHon = new No_Honesto();
		K_Fold kFold = new K_Fold();
		Evaluation holdOutEv = holdOut.evalHoldOut(rf, data);
		Evaluation noHonEv = noHon.evalNoHon(rf, data);
		Evaluation kFoldEv = kFold.evalKFold(rf, data);

		resultados.imprimirResultados(holdOutEv, noHonEv, kFoldEv, estimation);
		
		System.out.println("Archivo " + exportPath + " creado");
		System.out.println("Archivo " + estimation + " creado");
	}

	public static ArrayList<Integer> getParameters(String path) throws IOException {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		File f = new File(path);
		try {
			String linea;
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String texto = null;
			StringBuilder stringBuilder = new StringBuilder();
			texto = reader.readLine();
			stringBuilder.append(texto);
			linea = stringBuilder.toString();
			reader.close();
			// Separacion por comas
			String lineaArray[] = linea.split(";");
			// getParameters
			for (String param : lineaArray) {
				lista.add(Integer.parseInt(param));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}