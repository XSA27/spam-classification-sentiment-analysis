package GetModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escritura {

	public void escribir(String datos, String path) throws IOException {
		File f = new File(path);
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(datos);
		bw.close();
	}
}
