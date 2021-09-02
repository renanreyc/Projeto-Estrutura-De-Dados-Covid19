import CSV.CSVReader;
import CSV.CSVWriter;
import algoritmos.SelectionSort;
import dataBase.CovidBase;
import tools.Paths;

import java.util.Vector;

public class ProjetoCovid {
    public static void main(String[] args) {
        String pathDataBase = "C:\\DOWNLOAD\\dataBase";
        String pathNewFiles = "C:\\DOWNLOAD\\newFiles";

        criarMelhorEPiorCaso(pathDataBase,pathNewFiles);

//        analiseSelectionSort(dados,pathDataBase,pathNewFiles);
    }

    public static void criarMelhorEPiorCaso(String oldPath, String newPath) {
        Vector<CovidBase> dados;
        dados = CSVReader.readerData(Paths.path_data.replace("{0}",oldPath));

        //Médio - Obito
        CSVWriter.csvWriter(dados,newPath,
                "epidemiological_week,date,order_for_place,state,city,city_ibge_code," +
                "place_type,last_available_confirmed,last_available_confirmed_per_100k_inhabitants," +
                "new_confirmed,last_available_deaths,new_deaths,last_available_death_rate,estimated_population," +
                "is_last,is_repeated\n");
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
