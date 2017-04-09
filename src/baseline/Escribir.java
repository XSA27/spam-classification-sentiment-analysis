package baseline;

import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Escribir {

	public void escribir(String args, Instances inst) throws IOException {

		ArffSaver saver = new ArffSaver();
		saver.setInstances(inst);
		saver.setFile(new File(args));
		saver.writeBatch();
	}
}