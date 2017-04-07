package fssInfoGain;

import java.io.FileReader;

import weka.core.Instances;

public class FssInfoGain {

	public static void main(String[] args) throws Exception {
		FiltroAtributos info = new FiltroAtributos();
		Guardar guardar = new Guardar();
		Instances data, newData;
		
		// Leer datos de entrada
		FileReader fi = new FileReader(args[0]);
		data = new Instances(fi);
		fi.close();
		
		// Aplicar ganancia
		newData = info.filtrar(data);

		// Guardar
		guardar.guardar(args[1], newData);

	}

}
