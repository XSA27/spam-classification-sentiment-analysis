package arff2bow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Lectura {

	public Instances cargarDatos(String path) throws Exception {
		FileReader fi = null;
		try {
			fi = new FileReader(path);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Revisar path del fichero de datos:");
		}

		// Load the instances
		Instances data = null;
		try {
			data = new Instances(fi);
		} catch (IOException e) {
			System.out.println("ERROR: Revisar contenido del fichero de datos: ");
		}

		// Close the file
		try {
			fi.close();
		} catch (IOException e) {
			System.out.println("*PANIC*");
		}

		// Setea la clase
		data.setClassIndex(0);
		return data;
	}
}