package baseline;

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

		// Encuentra la clase 'Class'
		int pos = 0;
		for (int t = 0; t < data.numAttributes(); t++) {
			if (data.attribute(t).name().equalsIgnoreCase("Class")) {
				pos = t;
			}
		}
		data.setClassIndex(pos);
		return data;
	}

}
