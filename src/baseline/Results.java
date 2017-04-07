package baseline;

import weka.classifiers.Evaluation;

public class Results {

	public void imprimirResultados(Evaluation evaluator) throws Exception {
		double confMatrix[][] = evaluator.confusionMatrix();

		System.out.println(evaluator.toSummaryString());
		System.out.println(evaluator.toClassDetailsString());

		for (int row_i = 0; row_i < confMatrix.length; row_i++) {
			for (int col_i = 0; col_i < confMatrix.length; col_i++) {
				System.out.print(confMatrix[row_i][col_i]);
				System.out.print("|");
			}
			System.out.println();
		}

	}
}
