package GetModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import weka.classifiers.Evaluation;

public class Results {

	public void imprimirResultados(Evaluation holdOut, Evaluation noHon, Evaluation kFold, String path)
			throws Exception {

		File fout = new File(path);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("Hold-Out: ");
		bw.newLine();
		bw.write(holdOut.toSummaryString());
		bw.write(holdOut.toClassDetailsString());
		bw.write(holdOut.toMatrixString());
		bw.newLine();

		bw.write("No Honesta: ");
		bw.newLine();
		bw.write(noHon.toSummaryString());
		bw.write(noHon.toClassDetailsString());
		bw.write(noHon.toMatrixString());
		bw.newLine();

		bw.write("kFold: ");
		bw.newLine();
		bw.write(kFold.toSummaryString());
		bw.write(kFold.toClassDetailsString());
		bw.write(kFold.toMatrixString());
		bw.newLine();

		bw.close();
	}
}
