package arff2bow;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Filtro {
	public Instances filtrar(Instances data) throws Exception {

		// Se utiliza el filtro StringToWordVector con IDT y TFT a false
		StringToWordVector filtroSTWV = new StringToWordVector();
		filtroSTWV.setIDFTransform(false);
		filtroSTWV.setTFTransform(false);
		filtroSTWV.setOutputWordCounts(true);
		filtroSTWV.setDoNotOperateOnPerClassBasis(false);
		filtroSTWV.setInvertSelection(false);
		filtroSTWV.setLowerCaseTokens(true);
		filtroSTWV.setMinTermFreq(1);
		filtroSTWV.setOutputWordCounts(true);
		filtroSTWV.setPeriodicPruning(-1.0);
		filtroSTWV.setWordsToKeep(2000);
		filtroSTWV.setInputFormat(data);
		data = Filter.useFilter(data, filtroSTWV);
		return data;
	}
}
