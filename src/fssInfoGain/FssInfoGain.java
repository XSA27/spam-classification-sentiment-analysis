package fssInfoGain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class FssInfoGain {

	public static void main(String[] args) throws Exception {
		FiltroAtributos info = new FiltroAtributos();
		Guardar guardar = new Guardar();
		Instances data = null;
		Instances newData = null;
		
		// Leer datos de entrada
		FileReader fi = null;
		try {
			fi = new FileReader(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Revisar path del fichero de datos: ");
		}
		
		// Load the instances
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
		data.setClassIndex(0);
		
		// Aplicar ganancia
		newData = info.filtrar(data);

		// Guardar
		guardar.guardar(args[1], newData);

	}

}
