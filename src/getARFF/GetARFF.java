package getARFF;

public class GetARFF {
	public static void main(String[] args) throws Exception {
		System.out.println("Ruta seleccionada: " + args[0] + "\n");

		if (args[0].endsWith(".txt")) {
			System.out.println("Preparando conversion de .txt a .arff ...\n");
			TextoAArff txtArff = new TextoAArff();
			txtArff.createDataset(args);
		} else if (args[0].endsWith(".csv")) {
			System.out.println("Preparando conversion de .csv a .arff ...\n");
			CsvAArff csvarff = new CsvAArff();
			csvarff.createDataset(args);
		} else {
			System.out.println("Preparando conversion de carpetas con .txt a .arff ...\n");
			TextDirectoryToArff fileArff = new TextDirectoryToArff();
			fileArff.createDataset(args);
		}
	}
}
