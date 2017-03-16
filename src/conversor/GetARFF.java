package conversor;

public class GetARFF {
	public static void main(String[] args) throws Exception {

		System.out.println(args[0]);

		if (args[0].endsWith(".txt")) {
			System.out.println("Preparando conversion de txt a arff");
			TextoAArff txtArff = new TextoAArff();
			txtArff.createDataset(args);
		} else if (args[0].endsWith(".csv")) {
			System.out.println("Preparando conversion de csv a arff");
			CsvAArff csvarff = new CsvAArff();
			csvarff.createDataset(args);
		} else {
			System.out.println("Preparando conversion de carpetas a arff");
			TextDirectoryToArff fileArff = new TextDirectoryToArff();
			fileArff.createDataset(args);
		}
	}
}
