package arff2bow;

import weka.core.Instances;

public class Arff2bow {

	public static void main(String[] args) throws Exception {
		Lectura lec = new Lectura();
		Filtro filtro = new Filtro();
		Instances dataTrain, dataSalida;
		dataTrain = lec.cargarDatos(args[0]);
		dataSalida = new Instances(dataTrain);
		dataSalida = filtro.filtrar(dataSalida);
		Escribir esc = new Escribir();
		esc.escribir(dataSalida, args[1]);
		System.out.println("Creado archivo BOW");
	}
}
