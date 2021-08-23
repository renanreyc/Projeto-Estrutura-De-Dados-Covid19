package CSV;

import dataBase.CovidBase;

import java.io.*;
import java.util.Vector;

public class CSVReader {
    public static Vector<CovidBase> readerData(String caminho) {
        String linha = "";
        boolean primeiraLinha = true;
        Vector<CovidBase> data = new Vector<CovidBase>(1);
        try {
            FileReader fileReader = new FileReader(caminho);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            while ((linha = bufferReader.readLine()) != null) {
                if (primeiraLinha == true){
                    primeiraLinha = false;
                    continue;
                }
//                System.out.println(linha);
                data.add(new CovidBase(linha));
            }
            bufferReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: \n"+e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Erro: \n" + e.getMessage());
        }
        return data;
    }
}
