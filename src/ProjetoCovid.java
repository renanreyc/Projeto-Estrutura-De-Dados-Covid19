import algoritmos.*;
import csv.*;
import tools.*;

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

    public static void main(String[] args) {
        System.out.println("---------Iniciando programa---------");

        String pathDataBase = "C:\\DOWNLOAD\\dataBase";
        String pathNewFiles = "C:\\DOWNLOAD\\newFiles";

        criarMelhoresEPioresCasos(pathDataBase);
        ordenarDataEMedirTempos(pathDataBase, pathNewFiles);

        System.out.println("---------Finalizando programa---------");
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
        Timer timer = new Timer();
        calcularSelectionSort(pathDataBase, pathNewFiles, timer);
        calcularInsertionSort(pathDataBase, pathNewFiles, timer);
        calcularMergeSort(pathDataBase, pathNewFiles, timer);
        calcularQuickSort(pathDataBase, pathNewFiles, timer);
        calcularQuickMedianaDe3Sort(pathDataBase, pathNewFiles, timer);
        calcularCountingSort(pathDataBase, pathNewFiles, timer);
        calcularHeapSort(pathDataBase, pathNewFiles, timer);

        System.out.println("\nEscrevendo tempos...");
        TimerWriter.writeTime(timer, pathNewFiles + "/temposDeOrdenacao.txt");
        System.out.println("Tempos escritos com sucesso!...");
    }

    public static void calcularSelectionSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por SelectionSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/selectionsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do SelectionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(data, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());

    }

    public static void calcularInsertionSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por InsertionSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/insertionsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do InsertionSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(data);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());

    }

    public static void calcularMergeSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por MergeSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do MergeSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/mergesort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do MergeSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do MergeSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.mergeSort(data, 0, data.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());

    }


    public static void calcularQuickSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por QuickSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do QuickSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quicksort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do QuickSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do QuickSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.quickSort(data, 0, data.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());

    }

    public static void calcularQuickMedianaDe3Sort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por QuickMedianaDe3Sort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/quick3sort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no pior caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do QuickMedianaDe3Sort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.quickMedianaDe3Sort(data, 0, data.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());

    }

    public static void calcularCountingSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por CountingSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do CountingSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        data = CountingSort.ordenarPorCasosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/countingsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        data = CountingSort.ordenarPorObitosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDataBase + "/countingsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do CountingSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        CountingSort.ordenarPorCasosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        CountingSort.ordenarPorObitosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        // Pior Caso
        System.out.println("Calculando tempo no pior caso do CountingSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        CountingSort.ordenarPorCasosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        CountingSort.ordenarPorObitosConfirmados(data);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
    }

    public static void calcularHeapSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vetor<DataBase> data;

        System.out.println("\nOrdenação por HeapSort:");

        // Médio Caso
        System.out.println("Calculando tempo e criando arquivos no médio caso do HeapSort...");

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_casos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_obitos.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort_ordena_cidades.csv", PRIMEIRA_LINHA);

        // Melhor Caso
        System.out.println("Calculando tempo no melhor caso do HeapSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        data = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(data, pathDadosFinais + "/heapsort-melhor-cidades.csv", PRIMEIRA_LINHA);

        // Piores Casos
        System.out.println("Calculando tempo no pior caso do HeapSort...");

        data = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());

        data = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.heapSort(data, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
    }
}