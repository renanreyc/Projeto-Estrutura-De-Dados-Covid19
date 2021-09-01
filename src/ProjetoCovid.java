import CSV.CSVReader;
import algoritmos.SelectionSort;
import dataBase.CovidBase;
import tools.Paths;

import java.util.Vector;

public class ProjetoCovid {
    public static void main(String[] args) {
        String pathDataBase = "C:\\DOWNLOAD\\dataBase";
        String pathNewFiles = "C:\\DOWNLOAD\\newFiles";

        Vector<CovidBase> dados;
        dados = CSVReader.readerData(Paths.path_data.replace("{0}",pathDataBase));

        analiseSelectionSort(dados,pathDataBase,pathNewFiles);
    }

    public static void analiseSelectionSort(Vector dados,String pathDataBase, String pathNewFiles){
        System.out.println("SelectionSort");

        //Ordenação crescente por quantidade acumulada de óbitos;
        long tempoInicial = System.currentTimeMillis();
        SelectionSort.orderAvailableDeaths(dados, SelectionSort.INCREASE);

        System.out.println("o método executou em: " + (System.currentTimeMillis() - tempoInicial));
        //Ordenação crescente por quantidade acumulada de casos;



        //Ordenação crescente por ordem alfabética pelo nome das cidades.
    }
}
