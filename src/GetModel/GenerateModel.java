package GetModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class GenerateModel {

    public static void main(String[] args) throws Exception {
        File f = new File(args[0]);
        FileReader fr = new FileReader(f);
        Instances data = new Instances(fr);
        ArrayList<Integer> listaParametros = getParameters(args[1]);

        //create model
        RandomForest rf = new RandomForest();
        rf.setNumTrees(listaParametros.get(0));
        rf.setMaxDepth(listaParametros.get(1));
        rf.setNumFeatures(listaParametros.get(2));
       
        // data = train + dev
        rf.buildClassifier(data);
       
        crearModelo(args[2], rf, data);
    }
    
    public static ArrayList<Integer> getParameters(String path) throws IOException {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        File f = new File(path);
        try {
            String linea;
            BufferedReader reader = new BufferedReader(new FileReader (f));
            String texto = null;
            StringBuilder stringBuilder = new StringBuilder();
            texto = reader.readLine();
            stringBuilder.append(texto);
            linea = stringBuilder.toString();
            reader.close();
            // Separacion por comas
            String lineaArray[] = linea.split(";");
            // getParameters
            for(String param : lineaArray){
                lista.add(Integer.parseInt(param));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista;
    }

    public static void crearModelo(String path, Classifier clasificador, Instances data) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(clasificador);           
        Instances trainHeader = new Instances(data, 0);
        trainHeader.setClassIndex(data.classIndex());
        oos.writeObject(trainHeader);
        oos.flush();
        oos.close();
    }
}