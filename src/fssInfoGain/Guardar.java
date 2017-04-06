package fssInfoGain;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Guardar {

	public void guardar(String args, Instances inst) throws IOException {
		ArffSaver saver = new ArffSaver();
		saver.setInstances(inst);
		saver.setFile(new File(args));
		saver.writeBatch();
	}
}
