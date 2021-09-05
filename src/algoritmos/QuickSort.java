package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class QuickSort {

    public static final int CASOS = 0;
    public static final int OBITOS = 1;
    public static final int CIDADES = 2;

    public static void quickSort(Vetor<DataBase> vetor, int inicio, int fim, int indicador) {
        if (inicio < fim) {
            int pivotIndex = 0;
            switch (indicador) {
                case CASOS:
                    pivotIndex = particionaCasos(vetor, inicio, fim);
                    break;
                case OBITOS:
                    pivotIndex = particionaObitos(vetor, inicio, fim);
                    break;
                case CIDADES:
                    pivotIndex = particionaCidades(vetor, inicio, fim);
                    break;
            }
            quickSort(vetor, inicio, pivotIndex - 1, indicador);
            quickSort(vetor, pivotIndex + 1, fim, indicador);
        }
    }

    public static int particionaCasos(Vetor<DataBase> vetor, int inicio, int fim) {
        int pivot = vetor.encontrarElemento(fim).getAvailableConfirmed();
        int indexMenor = inicio - 1;
        for (int indexMaior = inicio; indexMaior < fim; indexMaior++) {
            if (vetor.encontrarElemento(indexMaior).getAvailableConfirmed() < pivot) {
                indexMenor++;
                trocaNumeros(vetor, indexMenor, indexMaior);
            }
        }
        trocaNumeros(vetor, indexMenor + 1, fim);
        return indexMenor + 1;
    }

    public static int particionaObitos(Vetor<DataBase> vetor, int inicio, int fim) {
        int pivot = vetor.encontrarElemento(fim).getAvailableDeaths();
        int indexMenor = inicio - 1;
        for (int indexMaior = inicio; indexMaior < fim; indexMaior++) {
            if (vetor.encontrarElemento(indexMaior).getAvailableDeaths() < pivot) {
                indexMenor++;
                trocaNumeros(vetor, indexMenor, indexMaior);
            }
        }
        trocaNumeros(vetor, indexMenor + 1, fim);
        return indexMenor + 1;
    }

    public static int particionaCidades(Vetor<DataBase> vetor, int inicio, int fim) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        String pivot = vetor.encontrarElemento(fim).getCity();
        int indexMenor = inicio - 1;
        for (int indexMaior = inicio; indexMaior < fim; indexMaior++) {
            if (collator.compare(vetor.encontrarElemento(indexMaior).getCity(), pivot) <= 0) {
                indexMenor++;
                trocaNumeros(vetor, indexMenor, indexMaior);
            }
        }
        trocaNumeros(vetor, indexMenor + 1, fim);
        return indexMenor + 1;
    }

    public static void trocaNumeros(Vetor<DataBase> vetor, int origem, int destino) {
        DataBase auxDaOrigem = vetor.encontrarElemento(origem);
        vetor.inserirElemento(vetor.encontrarElemento(destino), origem);
        vetor.inserirElemento(auxDaOrigem, destino);
    }

}
