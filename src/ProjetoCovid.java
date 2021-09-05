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

        criarArquivosDeDadosBrutos(pathDataBase);
        ordenarDadosECalcularTempos(pathDataBase, pathNewFiles);

        System.out.println("---------Finalizando programa---------");
    }

    public static void criarArquivosDeDadosBrutos(String pathDataBase) {

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

    public static void ordenarDadosECalcularTempos(String pathDataBase, String pathNewFiles) {
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

    public static void calcularCountingSort(String pathDadosBrutos, String pathDataBase, Timer timer) {

        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do CountingSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do CountingSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do CountingSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/countingsort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        System.out.println("CountingSort calculado com sucesso!");

    }

    public static void calcularInsertionSort(String pathDadosBrutos, String pathDataBase, Timer timer) {

        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do InsertionSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do InsertionSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do InsertionSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/insertionsort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("InsertionSort calculado com sucesso!");

    }

    public static void calcularMergeSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do MergeSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do MergeSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do MergeSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.getTamanho() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/mergesort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("MergeSort calculado com sucesso!");

    }

    public static void calcularSelectionSort(String pathDadosBrutos, String pathDataBase, Timer timer) {
        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do SelectionSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do SelectionSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do SelectionSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasosConfirmados(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCidades(dados, SelectionSort.CRESCENTE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDataBase + "/selectionsort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("SelectionSort com sucesso!");

    }

    public static void calcularQuickSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do QuickSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do QuickSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do QuickSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickSort.sort(dados, 0, dados.getTamanho() - 1, QuickSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQuickTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quicksort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("QuickSort com sucesso!");

    }

    public static void calcularQuickMedianaDe3Sort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do QuickSort Mediana de Três...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do QuickSort Mediana de Três...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do QuickSort Mediana de Três...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        QuickMedianaDe3Sort.sort(dados, 0, dados.getTamanho() - 1, QuickMedianaDe3Sort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setQUickMedTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/quick3sort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("QuickSort Mediana de Três calculado com sucesso!");

    }

    public static void calcularHeapSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {

        Vetor<DataBase> dados;

        System.out.println("\nCalculando os melhores casos do HeapSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-melhor-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-melhor-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_MELHORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-melhor-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os piores casos do HeapSort...");

        // Piores Casos

        dados = CSVReader.lerDados(PATH_OBITOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-pior-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CONFIRMADOS_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-pior-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_CIDADES_PIORCASO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-pior-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("Calculando os médios casos do HeapSort...");

        // Médios Casos

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-medio-obitos.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-medio-casosconfirmados.csv", PRIMEIRA_LINHA);

        dados = CSVReader.lerDados(PATH_DATA.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        HeapSort.sort(dados, HeapSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setHeapTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.writerFile(dados, pathDadosFinais + "/heapsort-medio-cidades.csv", PRIMEIRA_LINHA);

        System.out.println("HeapSort calculado com sucesso!");

    }

}