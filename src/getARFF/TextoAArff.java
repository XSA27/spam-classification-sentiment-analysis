package getARFF;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;

public class TextoAArff {

	public void createDataset(String[] pathDirectorio) {

		// ArrayList se utiliza para preparar los atributos
		ArrayList<Attribute> atributos = new ArrayList<Attribute>(2);
		atributos.add(new Attribute("filename", (List) null));
		atributos.add(new Attribute("contents", (List) null));

		// Se crea el objeto Instances
		Instances data = new Instances("texto " + pathDirectorio[0], atributos, 0);

		File dir = new File(pathDirectorio[0]);

		try {
			Scanner entrada = new Scanner(dir);
			String linea = "";
			// Se recorren las lineas que tenga el archivo. Se cogerá las
			// primeras cuatro posiciones en las que se podrá observar si es
			// "ham", "spam" o en el caso de no ser ninguna de las dos estaremos
			// trabajando con el archivo test_blind el cual no tiene asignado
			// una clase y se considerara como si fuera un missing value
			// mostrando una ?
			while (entrada.hasNext()) {
				double[] info = new double[2];
				linea = entrada.nextLine();
				String inicio = linea.substring(0, 4);
				inicio = inicio.replace("\t", "");
				String resto = linea.substring(4, linea.length());
				resto = resto.replace("\t", ",");

				if (!inicio.equals("ham") && !inicio.equals("spam")) {
					info[0] = Utils.missingValue();
				} else {
					info[0] = data.attribute(0).addStringValue(inicio);
				}
				info[1] = data.attribute(1).addStringValue(resto);
				data.add(new DenseInstance(1.0, info));
			}
		} catch (Exception e) {
			System.out.println("");
		}
		escribirEnARRF(data, pathDirectorio[1]);
	}

	private void escribirEnARRF(Instances data, String nombre) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			int z = 0;
			fichero = new FileWriter(nombre);
			pw = new PrintWriter(fichero);
			pw.println("@relation test");
			pw.println("@attribute 'Class' {'ham','spam'}");
			pw.println("@attribute Mensaje string");
			pw.println("@data");
			while (z < data.numInstances()) {
				pw.print(data.instance(z));
				pw.println();
				z++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					System.out.println("Archivo " + nombre + " creado");
				fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
