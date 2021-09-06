package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterResultados {

    private static final String OBITOS = "Obitos";
    private static final String CASOS = "Casos Confirmados";
    private static final String CIDADES = "Cidades";

    public static void writeresultado(Temporizador temporizador, String caminhoParaSalvar) {
        try {
            File file = new File(caminhoParaSalvar);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(joinText(temporizador.insertionTimeCasos, "InsertionSort", CASOS));
            bufferedWriter.append(joinText(temporizador.insertionTimeObitos, "InsertionSort", OBITOS));
            bufferedWriter.append(joinText(temporizador.insertionTimeCidades, "InsertionSort", CIDADES));

            bufferedWriter.append(joinText(temporizador.selectionTimeCasos, "SelectionSort", CASOS));
            bufferedWriter.append(joinText(temporizador.selectionTimeObitos, "SelectionSort", OBITOS));
            bufferedWriter.append(joinText(temporizador.selectionTimeCidades, "SelectionSort", CIDADES));

            bufferedWriter.append(joinText(temporizador.mergeTimeCasos, "MergeSort", CASOS));
            bufferedWriter.append(joinText(temporizador.mergeTimeObitos, "MergeSort", OBITOS));
            bufferedWriter.append(joinText(temporizador.mergeTimeCidades, "MergeSort", CIDADES));

            bufferedWriter.append(joinText(temporizador.quickTimeCasos, "QuickSort", CASOS));
            bufferedWriter.append(joinText(temporizador.quickTimeObitos, "QuickSort", OBITOS));
            bufferedWriter.append(joinText(temporizador.quickTimeCidades, "QuickSort", CIDADES));

            bufferedWriter.append(joinText(temporizador.quickMedTimeCasos, "QuickSort Mediana de Três", CASOS));
            bufferedWriter.append(joinText(temporizador.quickMedTimeObitos, "QuickSort Mediana de Três", OBITOS));
            bufferedWriter.append(joinText(temporizador.quickMedTimeCidades, "QuickSort Mediana de Três", CIDADES));

            bufferedWriter.append(joinText(temporizador.countingTimeCasos, "CountingSort", CASOS));
            bufferedWriter.append(joinText(temporizador.countingTimeObitos, "CountingSort", OBITOS));

            bufferedWriter.append(joinText(temporizador.heapTimeCasos, "HeapSort", CASOS));
            bufferedWriter.append(joinText(temporizador.heapTimeObitos, "HeapSort", OBITOS));
            bufferedWriter.append(joinText(temporizador.heapTimeCidades, "HeapSort", CIDADES));

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException error) {
            System.err.println(error);
        }
    }

    private static String joinText(long[] times, String algorithmName, String orderType) {
        StringBuilder builder = new StringBuilder();
        builder.append("----------------------\n");
        builder.append(algorithmName + "\n");
        builder.append("----------------------\n");
        builder.append("Médio Caso - " + orderType + ": " + times[Temporizador.MEDIO] + "\n");
        builder.append("\nMelhor Caso - " + orderType + ": " + times[Temporizador.MELHOR] + "\n");
          builder.append("Pior Caso - " + orderType + ": " + times[Temporizador.PIOR] + "\n");
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "WriterResultados{}";
    }
}
