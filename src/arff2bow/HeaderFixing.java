package arff2bow;

import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class HeaderFixing {

	public static void main(String[] args) {
		Instances dataPrincipal = null, dataCompatible = null;
		String sobran = "";
		int cont = 0;
		boolean encontrado = false;

		ArffLoader cargador = new ArffLoader();
		try {
			cargador.setFile(new File(args[0]));
			dataPrincipal = cargador.getDataSet();
			cargador.setFile(new File(args[0]));
			dataCompatible = cargador.getDataSet();
			System.out.println("El dataPrincipal tiene " + dataPrincipal.numAttributes() + " atributos.");
			System.out.println("El dataCompatible tiene " + dataCompatible.numAttributes() + " atributos.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Buscamos los atributos de B que no estan en A
		for (int i = 0; i < dataCompatible.numAttributes(); i++) {
			int j = 0;
			encontrado = false;
			while (j < dataPrincipal.numAttributes() - 1 && !encontrado) {
				String[] nombre = dataPrincipal.attribute(j).name().split("\\s+");
				if (nombre[0].equals(dataCompatible.attribute(i).name())) {
					encontrado = true;
				}
				j++;
			}
			if (!encontrado) {
				sobran += i + ",";
				cont++;
			}
		}
		System.out.println("Al dataCompatible le sobran " + cont + " atributos.");

		// los borramos
		String[] sobranArray = (sobran.split(","));
		int[] sobranArrayInt = new int[sobranArray.length];
		for (int i = 0; i < sobranArray.length; i++) {
			sobranArrayInt[i] = Integer.parseInt(sobranArray[i]);
		}
		Remove filter = new Remove();
		filter.setAttributeIndicesArray(sobranArrayInt);
		try {
			filter.setInputFormat(dataCompatible);
			dataCompatible = Filter.useFilter(dataCompatible, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("El dataCompatible ahora tiene " + dataCompatible.numAttributes() + " atributos.");

		// añadimos los atributos de A que no existan
		for (int i = 0; i < dataPrincipal.numAttributes(); i++) {
			String[] nombre = dataPrincipal.attribute(i).name().split("\\s+");
			if (dataCompatible.attribute(nombre[0]) == null) {
				dataCompatible.insertAttributeAt(dataPrincipal.attribute(i), dataCompatible.numAttributes());
			}
		}
		System.out.println("El dataCompatible despues de añadir los que faltan tiene " + dataCompatible.numAttributes()
				+ " atributos.");

		ArffSaver grabador = new ArffSaver();
		try {
			grabador.setFile(new File(args[2]));
			grabador.setInstances(dataCompatible);
			grabador.setStructure(dataPrincipal);
			grabador.writeBatch();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
