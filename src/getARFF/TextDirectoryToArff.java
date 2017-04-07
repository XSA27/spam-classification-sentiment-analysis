package getARFF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;

public class TextDirectoryToArff {
	public void createDataset(String[] pathDirectorio) throws Exception {

		// ArrayList se utiliza para preparar los atributos
		ArrayList<Attribute> atributos = new ArrayList<Attribute>(2);
		atributos.add(new Attribute("filename", (List) null));
		atributos.add(new Attribute("contents", (List) null));

		// Se crea el objeto Instances
		Instances data = new Instances("texto " + pathDirectorio[0], atributos, 0);

		File dir = new File(pathDirectorio[0]);

		String directoryPathExt;
		String clase;
		String[] filesExt;
		boolean missing = false;

		// Se listan las carpetas
		String[] files = dir.list();

		for (int q = 0; q < files.length; q++) {
			System.out.println();
			clase = files[q];
			if (!files[q].endsWith(".txt")) {
				File dir1 = new File(pathDirectorio[0] + "\\" + files[q]);
				directoryPathExt = pathDirectorio[0] + "\\" + files[q];
				filesExt = dir1.list();
			} else {

				directoryPathExt = pathDirectorio[0];
				filesExt = dir.list();
				missing = true;
			}
			for (int i = 0; i < filesExt.length; i++) {
				try {
					double[] newInst = new double[2];
					if (missing) {
						newInst[0] = Utils.missingValue();
					} else {
						newInst[0] = (double) data.attribute(0).addStringValue(clase);
					}
					File txt = new File(directoryPathExt + File.separator + filesExt[i]);
					InputStreamReader is = new InputStreamReader(new FileInputStream(txt));
					StringBuffer txtStr = new StringBuffer();
					int c;
					while ((c = is.read()) != -1) {
						txtStr.append((char) c);
					}
					newInst[1] = (double) data.attribute(1).addStringValue(txtStr.toString());
					data.add(new DenseInstance(1.0, newInst));
				} catch (Exception e) {

				}
			}
			if (missing) {
				break;
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
			pw.println("@attribute 'Class' {'pos','neg'}");
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