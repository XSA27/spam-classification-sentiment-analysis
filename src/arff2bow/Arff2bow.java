package arff2bow;

import weka.core.Instances;

public class Arff2bow {

	public static void main(String[] args) throws Exception {

		Lectura lec = new Lectura();
		Filtro filtro = new Filtro();
		Instances dataTrain, dataDev, dataSalida;
		dataTrain = lec.cargarDatos(args[0]);
		dataDev = lec.cargarDatos(args[1]);
		dataSalida = new Instances(dataTrain);
		for (int i = 0; i < dataDev.numInstances(); i++) {
			dataSalida.add(dataDev.instance((i)));
		}

		dataSalida = filtro.filtrar(dataSalida);
		Escribir esc = new Escribir();
		esc.escribir(dataSalida, args[2]);

		System.out.println("creado archivo BOW");

	}
}
