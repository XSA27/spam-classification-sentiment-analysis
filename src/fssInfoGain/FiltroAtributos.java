package fssInfoGain;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class FiltroAtributos {

	public Instances filtrar(Instances data) throws Exception {

		// Set filter parameters
		AttributeSelection filter = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		
		// Número de atributos a mantener
		// Default para todos
			// search.setNumToSelect(Math.round(data.numAttributes()/70));
		// Minimo valor metrico para descartar
		// 0 = InfoGain positivo
		search.setThreshold(0);
		
		filter.setEvaluator(eval);
		filter.setSearch(search);
		filter.setInputFormat(data);
		
		// Get new data set with the attribute sub-set
		Instances newData = Filter.useFilter(data, filter);
		
		return newData;
	}
}
