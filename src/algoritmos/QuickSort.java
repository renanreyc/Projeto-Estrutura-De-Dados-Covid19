package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class QuickSort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;

    public static void sort(Vetor<DataBase> vetor, int start, int end, int type) {
        if(hasMoreElements(start, end)) {
            int pivotIndex = 0;
            switch (type) {
                case OBITOS:
                    pivotIndex = particionaObitos(vetor, start, end);
                    break;
                case CASOS:
                    pivotIndex = particionaCasos(vetor, start, end);
                    break;
                case CIDADES:
                    pivotIndex = particionaCidades(vetor, start, end);
                    break;
            }
            sort(vetor, start, pivotIndex - 1, type);
            sort(vetor, pivotIndex + 1, end, type);
        }
    }

    public static int particionaObitos(Vetor<DataBase> vetor, int start, int end) {
        int pivot = vetor.encontrarElemento(end).getAvailableDeaths();
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vetor.encontrarElemento(largerItemsThanPivotIndexController).getAvailableDeaths() <= pivot) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vetor, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vetor, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static int particionaCasos(Vetor<DataBase> vetor, int start, int end) {
        int pivot = vetor.encontrarElemento(end).getAvailableConfirmed();
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vetor.encontrarElemento(largerItemsThanPivotIndexController).getAvailableConfirmed() <= pivot) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vetor, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vetor, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static int particionaCidades(Vetor<DataBase> vetor, int start, int end) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        String pivot = vetor.encontrarElemento(end).getCity();
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(collator.compare(vetor.encontrarElemento(largerItemsThanPivotIndexController).getCity(), pivot) <= 0) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vetor, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vetor, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static void exchangeElements(Vetor<DataBase> vetor, int source, int destination) {
        DataBase itemHoldedFromSource = vetor.encontrarElemento(source);
        vetor.inserirElemento(vetor.encontrarElemento(destination), source);
        vetor.inserirElemento(itemHoldedFromSource, destination);
    }

    public static boolean hasMoreElements(int start, int end) {
        return start < end;
    }
}
