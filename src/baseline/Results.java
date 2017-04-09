package baseline;

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
		
		System.out.println("Metodo Hold-Out: ");
		System.out.println(holdOut.toSummaryString());
		System.out.println(holdOut.toClassDetailsString());
		System.out.println(holdOut.toMatrixString());
		bw.write("Hold-Out: ");
		bw.newLine();
		bw.write(holdOut.toSummaryString());
		bw.write(holdOut.toClassDetailsString());
		bw.write(holdOut.toMatrixString());
		bw.newLine();
		
		System.out.println("Metodo no honesto: ");
		System.out.println(noHon.toSummaryString());
		System.out.println(noHon.toClassDetailsString());
		System.out.println(noHon.toMatrixString());
		bw.write("No Honesta: ");
		bw.newLine();
		bw.write(noHon.toSummaryString());
		bw.write(noHon.toClassDetailsString());
		bw.write(noHon.toMatrixString());
		bw.newLine();
		
		System.out.println("Metodo kFold: ");
		System.out.println(kFold.toSummaryString());
		System.out.println(kFold.toClassDetailsString());
		System.out.println(kFold.toMatrixString());
		bw.write("kFold: ");
		bw.newLine();
		bw.write(kFold.toSummaryString());
		bw.write(kFold.toClassDetailsString());
		bw.write(kFold.toMatrixString());
		bw.newLine();

		bw.close();
	}
}