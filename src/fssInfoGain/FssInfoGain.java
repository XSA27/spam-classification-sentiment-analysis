package fssInfoGain;

import weka.core.Instances;

public class FssInfoGain {

	public static void main(String[] args) throws Exception {
		FiltroAtributos info = new FiltroAtributos();
		Lectura lec = new Lectura();
		Guardar guardar = new Guardar();
		Instances data, newData;
		
		// Leer datos de entrada
		data = lec.leerFichero(args[0]);
		
		// Aplicar ganancia
		newData = info.filtrar(data);

		// Guardar
		guardar.guardar(args[1], newData);

	}

}
