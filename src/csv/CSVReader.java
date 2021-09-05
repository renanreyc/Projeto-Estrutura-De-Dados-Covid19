package csv;

import tools.DataBase;
import tools.Vetor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static Vetor<DataBase> lerDados(String caminho) {
        String linha = "";
        boolean isFirstLine = true;
        Vetor<DataBase> data = new Vetor<DataBase>(1);
        try {
            FileReader fileReader = new FileReader(caminho);
            BufferedReader reader = new BufferedReader(fileReader);
            while((linha = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                data.adicionarElemento(new DataBase(linha));
            }
            reader.close();
            fileReader.close();
        } catch (FileNotFoundException error) {
            System.err.println("Não foi possível encontrar o arquivo.");
        } catch (IOException error) {
            System.err.println("Erro ao ler linha.");
        }
        return data;
    }
}
