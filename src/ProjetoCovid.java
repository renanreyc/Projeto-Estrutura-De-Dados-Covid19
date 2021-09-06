import algoritmos.*;
import csv.*;
import tools.*;

import java.util.Scanner;

public class ProjetoCovid {

    private static final String PATH_DATA = "{0}/data.csv";
    private static final String PATH_CONFIRMADOS_MELHORCASO = "{0}/data_confirmados_melhorCaso.csv";
    private static final String PATH_CONFIRMADOS_PIORCASO = "{0}/data_confirmados_piorCaso.csv";

    private static final String PATH_OBITOS_MELHORCASO = "{0}/data_obitos_melhorCaso.csv";
    private static final String PATH_OBITOS_PIORCASO = "{0}/data_obitos_piorCaso.csv";

    private static final String PATH_CIDADES_MELHORCASO = "{0}/data_cidades_melhorCaso.csv";
    private static final String PATH_CIDADES_PIORCASO = "{0}/data_cidades_piorCaso.csv";

    private static final String PRIMEIRA_LINHA = "epidemiological_week,date,order_for_place,state,city," +
            "city_ibge_code,place_type,last_available_confirmed,last_available_confirmed_per_100k_inhabitants," +
            "new_confirmed,last_available_deaths,new_deaths,last_available_death_rate,estimated_population,is_last,is_repeated\n";

    private static final String PROGRAMA_INICIO = "Por favor, digite o caminho do arquivo data.csv\n" +
            "   OBS.: se seu Sistema Operacional for Windows coloque duas barras invertidas no lugar da barra\n" +
            "   Ex.: C:\\\\DOWNLOAD\\\\dataBase\n"+
            "   OBS.2: para Linux ou IOS coloque o caminho normal.\n" +
            "   Ex.: /DOWNLOAD/dataBase";

    public static void main(String[] args) {
        System.out.println("################ INICIANDO PROGRAMA ################");

        Scanner entrada = new Scanner(System.in);

        System.out.println(PROGRAMA_INICIO);
        //C:\\DOWNLOAD\\dataBase
        String pathDataBase = entrada.nextLine();
        System.out.println("Agora digite o caminho da pasta onde ficaram os novos arquivos:");
        //C:\\DOWNLOAD\\newFiles
        String pathNewFiles = entrada.nextLine();

        criarMelhoresEPioresCasos(pathDataBase);
        ordenarDataEMedirTempos(pathDataBase, pathNewFiles);

        System.out.println("################ FINALIZANDO PROGRAMA ################");
    }

    public static void criarMelhoresEPioresCasos(String pathDataBase) {

        Vetor<DataBase> data;

        System.out.println("Lendo arquivo data.csv");
        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDataBase));

        System.out.println("Escrevendo melhores e piores casos de \"availableConfirmed\".");
        // Melhor caso | availableConfirmed
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        CSVWriter.writerFile(data, PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);
        // Pior caso | availableConfirmed
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.DECRESCENTE);
        CSVWriter.writerFile(data, PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);

        System.out.println("Escrevendo melhores e piores casos de \"availableDeaths\".");
        // Melhor caso | availableDeaths
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        CSVWriter.writerFile(data, PATH_OBITOS_MELHORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);
        // Pior caso | availableDeaths
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.DECRESCENTE);
        CSVWriter.writerFile(data, PATH_OBITOS_PIORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);

        System.out.println("Escrevendo melhores e piores casos de \"city\".");
        // Melhor caso | city
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        CSVWriter.writerFile(data, PATH_CIDADES_MELHORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);
        // Pior caso | city
        SelectionSort.ordenarPorCidades(data, SelectionSort.DECRESCENTE);
        CSVWriter.writerFile(data, PATH_CIDADES_PIORCASO.replace("{0}", pathDataBase), PRIMEIRA_LINHA);

    }

    public static void ordenarDataEMedirTempos(String pathDataBase, String pathNewFiles) {
        Temporizador temporizador = new Temporizador();
        calcularSelectionSort(pathDataBase, pathNewFiles, temporizador);
        calcularInsertionSort(pathDataBase, pathNewFiles, temporizador);
        calcularMergeSort(pathDataBase, pathNewFiles, temporizador);
        calcularQuickSort(pathDataBase, pathNewFiles, temporizador);
        calcularQuickMedianaDe3Sort(pathDataBase, pathNewFiles, temporizador);
        calcularCountingSort(pathDataBase, pathNewFiles, temporizador);
        calcularHeapSort(pathDataBase, pathNewFiles, temporizador);

        System.out.println("\nEscrevendo resultados...");
        WriterResultados.writeresultado(temporizador, pathNewFiles + "/temposDeOrdenacao.txt");
        System.out.println("Resultados escritos com sucesso!");
    }

    public static void calcularSelectionSort(String pathDadosBrutos, String pathDataBase, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por SelectionSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setselectionTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

    }

    public static void calcularInsertionSort(String pathDadosBrutos, String pathDataBase, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por InsertionSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorCasos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorObitos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorNomeDasCidades(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorCasos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorObitos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorCasos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorCasos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorObitos(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        InsertionSort.ordenarPorNomeDasCidades(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setInsertionTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

    }

    public static void calcularMergeSort(String pathDadosBrutos, String pathDataBase, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por MergeSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do MergeSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do MergeSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do MergeSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setMergeTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

    }


    public static void calcularQuickSort(String pathDadosBrutos, String pathDadosFinais, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por QuickSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do QuickSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do QuickSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do QuickSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQuickTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

    }

    public static void calcularQuickMedianaDe3Sort(String pathDadosBrutos, String pathDadosFinais, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por QuickMedianaDe3Sort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no pior caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setQUickMedTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

    }

    public static void calcularCountingSort(String pathDadosBrutos, String pathDataBase, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por CountingSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do CountingSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        data = CountingSort.ordenarPorCasosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/countingsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        data = CountingSort.ordenarPorObitosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDataBase + "/countingsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do CountingSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        CountingSort.ordenarPorCasosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        CountingSort.ordenarPorObitosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        // Pior Caso
        System.out.println("Calculando tempos no pior caso do CountingSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        CountingSort.ordenarPorCasosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        CountingSort.ordenarPorObitosConfirmados(data);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setCountingTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());
    }

    public static void calcularHeapSort(String pathDadosBrutos, String pathDadosFinais, Temporizador temporizador) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por HeapSort:");

        // Médio Caso
        System.out.println("Calculando tempos e criando arquivos no médio caso do HeapSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCasos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeObitos(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCidades(Temporizador.MEDIO, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempos no melhor caso do HeapSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCasos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeObitos(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCidades(Temporizador.MELHOR, temporizador.getTemporizadorTempoFinal());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort-melhor-cidades.csv", PRIMEIRA_LINHA);

        // Piores Casos
        System.out.println("Calculando tempos no pior caso do HeapSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CASOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCasos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeObitos(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        temporizador.setTemporizadorTempoInicial();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        temporizador.setTemporizadorTempoFinal();
        temporizador.setHeapTimeCidades(Temporizador.PIOR, temporizador.getTemporizadorTempoFinal());
    }
}