package getARFF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;

public class CsvAArff {

	public void createDataset(String[] pathDirectorio) throws Exception {

		// ArrayList se utiliza para preparar los atributos
		ArrayList<Attribute> atributos = new ArrayList<Attribute>(2);
		atributos.add(new Attribute("filename", (List) null));
		atributos.add(new Attribute("contents", (List) null));

		// Se crea el objeto Instances
		Instances data = new Instances("texto " + pathDirectorio[0], atributos, 0);

		File dir = new File(pathDirectorio[0]);

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String clase = "?";
		try {

			br = new BufferedReader(new FileReader(dir));
			line = br.readLine();// necesito saltar la primera linea
			while ((line = br.readLine()) != null) {
				String texto = "";
				if (line.contains("Oct")) {
					double[] newInst = new double[2];
					String[] frase = line.split(cvsSplitBy);
					clase = frase[1];
					for (int z = 0; z < frase.length; z++) {
						if (z == 1) {
							z++;
						} else {
							texto = texto.concat(frase[z]);
						}
					}
					clase = clase.replaceAll("\"", "");
					if (clase.equals("UNKNOWN")) {
						newInst[0] = Utils.missingValue();
					} else {
						newInst[0] = (double) data.attribute(0).addStringValue(clase);
					}
					newInst[1] = (double) data.attribute(1).addStringValue(texto);
					data.add(new DenseInstance(1.0, newInst));

				} else {

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
			pw.println("@attribute 'Class' {'positive','negative','neutral','irrelevant'}");
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