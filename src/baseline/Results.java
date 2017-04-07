package baseline;

import weka.classifiers.Evaluation;

public class Results {

	public void imprimirResultados(Evaluation evaluator) throws Exception {

		System.out.println(evaluator.toSummaryString());
		System.out.println(evaluator.toClassDetailsString());
		System.out.println(evaluator.toMatrixString());

	}
}
