package GetModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Lectura {

	public Instances leerFichero(String fichero) {
		// Se comprueba que el fichero existe y que no hay ningún problema para
		// abrirlo
		FileReader fi = null;
		try {
			fi = new FileReader(fichero);
		} catch (FileNotFoundException e) {
			// En este caso ha habido un problema y se muestra un aviso por
			// pantalla
			System.out.println("ERR0R: Revisa el path del fichero de datos");
		}

		// Una vez se tenga el archivo ya disponible, se cargan las instancias
		Instances data = null;
		try {
			data = new Instances(fi);
		} catch (IOException e) {
			// Se ha producido un error al intentar cargar las instancias
			System.out.println("ERR0R: Revisa el contenido del fichero de datos");
		}

		// Si la carga de las instancias ha sido correcta se cierra el archivo
		try {
			fi.close();
		} catch (IOException e) {
			// Se ha producido un error al cerrar el archivo con el que se
			// estaba trabajando
			System.out.println("ERR0R: Se ha producido algo no esperado al cerrar el archivo con el que trabajabas");
		}

		// Encuentra la clase 'Class'
		int pos = 0;
		for (int t = 0; t < data.numAttributes(); t++) {
			if (data.attribute(t).name().equals("Class")) {
				pos = t;
			}
		}
		data.setClassIndex(pos);

		// Se devuelven todas las instancias para poder trabajar con ellas
		return data;
	}
}