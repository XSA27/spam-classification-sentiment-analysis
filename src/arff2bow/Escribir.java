package arff2bow;

import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Escribir {

	public void escribir(Instances inst, String args) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setInstances(inst);
		saver.setFile(new File(args));
		saver.writeBatch();
	}
}
