package arff2bow;

import weka.core.Instance;
import weka.core.Instances;

public class Merge {

	public void merge(Instances pTrain, Instances pDev, String path) throws Exception {
		Instances train = pTrain;
		Instances dev = pDev;

		for (Instance i : dev) {
			train.add(i);
		}

		Escribir e = new Escribir();
		e.escribir(train, path);
	}
}
