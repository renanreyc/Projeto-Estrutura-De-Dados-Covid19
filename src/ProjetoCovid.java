import algoritmos.*;
import csv.CSVReader;
import csv.CSVWriter;
import tools.*;


public class ProjetoCovid {
    public static void main(String[] args) {

        System.out.println("Iniciando...");

        String pathDadosBrutos = "C:\\DOWNLOAD\\dataBase";
        String pathDadosFinais = "C:\\DOWNLOAD\\newFiles";

        criarArquivosDeDadosBrutos(pathDadosBrutos);
        ordenarDadosECalcularTempos(pathDadosBrutos, pathDadosFinais);

        System.out.println("Finalizando...");
    }

    public static void criarArquivosDeDadosBrutos(String pathDadosBrutos) {

        Vector<CovidData> dados;

        System.out.println("Lendo arquivo - casos, filtrando e escrevendo...");

        // Lendo arquivo bruto, filtrando e escrevendo o aquivo filtrado.
        dados = CSVReader.lerDados(Strings.PATH_DADOS.replace("{0}", pathDadosBrutos));
        //dados = CSVCleaner.filtrarDados(dados);
        CSVWriter.escreverDados(dados, Strings.PATH_DADOS_LIMPOS.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        System.out.println("Calculando e escrevendo melhores, piores, e médios casos de obitos.");

        // Médio - Obito
        dados = CSVReader.lerDados(Strings.PATH_DADOS_LIMPOS.replace("{0}", pathDadosBrutos));
        CSVWriter.escreverDados(dados, Strings.PATH_OBITOS_MEDIO.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Melhor - Obito
        SelectionSort.ordenarPorObitos(dados, SelectionSort.INCREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_OBITOS_MELHOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Pior - Obito
        SelectionSort.ordenarPorObitos(dados, SelectionSort.DECREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_OBITOS_PIOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        System.out.println("Calculando e escrevendo melhores, piores, e médios casos de casos confirmados.");

        // Médio - Casos
        dados = CSVReader.lerDados(Strings.PATH_DADOS_LIMPOS.replace("{0}", pathDadosBrutos));
        CSVWriter.escreverDados(dados, Strings.PATH_CONFIRMADOS_MEDIO.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Melhor - Casos
        SelectionSort.ordenarPorCasos(dados, SelectionSort.INCREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_CONFIRMADOS_MELHOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Pior - Casos
        SelectionSort.ordenarPorCasos(dados, SelectionSort.DECREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_CONFIRMADOS_PIOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        System.out.println("Calculando e escrevendo melhores, piores, e médios casos de cidades.");

        // Médio - Cidades
        dados = CSVReader.lerDados(Strings.PATH_DADOS_LIMPOS.replace("{0}", pathDadosBrutos));
        CSVWriter.escreverDados(dados, Strings.PATH_CIDADES_MEDIO.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Melhor - Cidades
        SelectionSort.ordenarPorNomeDasCidades(dados, SelectionSort.INCREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_CIDADES_MELHOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        // Pior - Cidades
        SelectionSort.ordenarPorNomeDasCidades(dados, SelectionSort.DECREASE);
        CSVWriter.escreverDados(dados, Strings.PATH_CIDADES_PIOR.replace("{0}", pathDadosBrutos), Strings.CABECALHO);

        System.out.println("Finalizando a manipulação de arquivos brutos.");

    }

    public static void ordenarDadosECalcularTempos(String pathDadosLimpos, String pathDadosFinais) {
        Timer timer = new Timer();
        calcularCountingSort(pathDadosLimpos, pathDadosFinais, timer);
         calcularInsertionSort(pathDadosLimpos, pathDadosFinais, timer);
        calcularMergeSort(pathDadosLimpos, pathDadosFinais, timer);
        calcularSelectionSort(pathDadosLimpos, pathDadosFinais, timer);


        System.out.println("\nEscrevendo tempos...");
        TimerWriter.writeTime(timer, pathDadosFinais + "/tempos.txt");
        System.out.println("Tempos escritos com sucesso!...");
    }

    public static void calcularCountingSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {

        Vector<CovidData> dados;

        System.out.println("\nCalculando os melhores casos do CountingSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-melhor-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-melhor-casosconfirmados.csv", Strings.CABECALHO);

        System.out.println("Calculando os piores casos do CountingSort...");

        // Piores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-pior-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-pior-casosconfirmados.csv", Strings.CABECALHO);

        System.out.println("Calculando os médios casos do CountingSort...");

        // Médios Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-medio-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        dados = CountingSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setCountingTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/countingsort-medio-casosconfirmados.csv", Strings.CABECALHO);

        System.out.println("CountingSort calculado com sucesso!");

    }

    public static void calcularInsertionSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {

        Vector<CovidData> dados;

        System.out.println("\nCalculando os melhores casos do InsertionSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-melhor-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-melhor-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-melhor-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os piores casos do InsertionSort...");

        // Piores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-pior-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-pior-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-pior-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os médios casos do InsertionSort...");

        // Médios Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorObitos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-medio-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorCasos(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-medio-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        InsertionSort.ordenarPorNomeDasCidades(dados);
        timer.setTemporaryFinalTime();
        timer.setInsertionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/insertionsort-medio-cidades.csv", Strings.CABECALHO);

        System.out.println("InsertionSort calculado com sucesso!");

    }

    public static void calcularMergeSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vector<CovidData> dados;

        System.out.println("\nCalculando os melhores casos do MergeSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-melhor-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-melhor-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-melhor-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os piores casos do MergeSort...");

        // Piores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-pior-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-pior-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-pior-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os médios casos do MergeSort...");

        // Médios Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.OBITOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-medio-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CASOS);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-medio-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        MergeSort.sort(dados, 0, dados.size() - 1, MergeSort.CIDADES);
        timer.setTemporaryFinalTime();
        timer.setMergeTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/mergesort-medio-cidades.csv", Strings.CABECALHO);

        System.out.println("MergeSort calculado com sucesso!");

    }

    public static void calcularSelectionSort(String pathDadosBrutos, String pathDadosFinais, Timer timer) {
        Vector<CovidData> dados;

        System.out.println("\nCalculando os melhores casos do SelectionSort...");

        // Melhores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-melhor-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-melhor-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MELHOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorNomeDasCidades(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MELHOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-melhor-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os piores casos do SelectionSort...");

        // Piores Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-pior-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-pior-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_PIOR.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorNomeDasCidades(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.PIOR, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-pior-cidades.csv", Strings.CABECALHO);

        System.out.println("Calculando os médios casos do SelectionSort...");

        // Médios Casos

        dados = CSVReader.lerDados(Strings.PATH_OBITOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorObitos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeObitos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-medio-obitos.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CONFIRMADOS_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorCasos(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCasos(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-medio-casosconfirmados.csv", Strings.CABECALHO);

        dados = CSVReader.lerDados(Strings.PATH_CIDADES_MEDIO.replace("{0}", pathDadosBrutos));
        timer.setTemporaryInitialTime();
        SelectionSort.ordenarPorNomeDasCidades(dados, SelectionSort.INCREASE);
        timer.setTemporaryFinalTime();
        timer.setselectionTimeCidades(Timer.MEDIO, timer.getTemporaryFinalTime());
        CSVWriter.escreverDados(dados, pathDadosFinais + "/selectionsort-medio-cidades.csv", Strings.CABECALHO);

        System.out.println("SelectionSort com sucesso!");

    }

}