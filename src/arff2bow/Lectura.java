package arff2bow;
	
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Lectura {

	public Instances cargarDatos(String path) throws Exception{
	
		// 1.2. Open the file
		FileReader fi=null;
			
		try {
			fi= new FileReader(path); 
		} catch (FileNotFoundException e) {
				System.out.println("ERROR: Revisar path del fichero de datos:");
		}
		// 1.3. Load the instances
		Instances data=null;
		try {
			data = new Instances(fi);
		} catch (IOException e) {
			System.out.println("ERROR: Revisar contenido del fichero de datos: ");
		}
		
		// 1.4. Close the file
		try {
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
			
		// 1.6. EL for encuentra la clase, siempre y cuando se llame class
		int pos =0;
		for(int t=0;t<data.numAttributes();t++){
			if (data.attribute(t).name().equalsIgnoreCase("Class")){
				pos=t;
			}
		}
		data.setClassIndex(pos);	
		

	
		
		return data;
		
	}

}
