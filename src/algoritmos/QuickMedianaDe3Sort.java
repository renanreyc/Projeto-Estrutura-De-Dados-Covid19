package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class QuickMedianaDe3Sort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;

    public static void sort(Vetor<DataBase> vetor, int start, int end, int type) {
        if(hasMoreElements(start, end)) {
            int pivotIndex = 0;
            switch (type) {
                case OBITOS:
                    int pivot3Index = findPivotObitosIndex(vetor, start, end);
                    exchangeElements(vetor, pivot3Index, end);
                    pivotIndex = particionaObitos(vetor, start, end);
                    break;
                case CASOS:
                    pivot3Index = findPivotCasosIndex(vetor, start, end);
                    exchangeElements(vetor, pivot3Index, end);
                    pivotIndex = particionaCasos(vetor, start, end);
                    break;
                case CIDADES:
                    pivot3Index = findPivotCidadesIndex(vetor, start, end);
                    exchangeElements(vetor, pivot3Index, end);
                    pivotIndex = particionaCidades(vetor, start, end);
                    break;
            }
            sort(vetor, start, pivotIndex - 1, type);
            sort(vetor, pivotIndex + 1, end, type);
        }
    }

    public static int particionaObitos(Vetor<DataBase> vetor, int start, int end) {
        DataBase pivot = vetor.encontrarElemento(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vetor.encontrarElemento(largerItemsThanPivotIndexController).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vetor, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vetor, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static int particionaCasos(Vetor<DataBase> vetor, int start, int end) {
        DataBase pivot = vetor.encontrarElemento(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vetor.encontrarElemento(largerItemsThanPivotIndexController).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
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

        DataBase pivot = vetor.encontrarElemento(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(collator.compare(vetor.encontrarElemento(largerItemsThanPivotIndexController).getCity(), pivot.getCity()) <= 0) {
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

    public static int findPivotObitosIndex(Vetor<DataBase> vetor, int startIndex, int endIndex) {

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        int start = vetor.encontrarElemento(startIndex).getAvailableConfirmed();
        int end = vetor.encontrarElemento(endIndex).getAvailableConfirmed();
        int middle = vetor.encontrarElemento(middleIndex).getAvailableConfirmed();

        int pivotIndex = 0;

        if (start > end) {
            if (start > middle) {
                if (end > middle) {
                    pivotIndex = endIndex;
                } else {
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = startIndex;
            }
        } else {
            if(end > middle) {
                if(start > middle) {
                    pivotIndex = startIndex;
                } else{
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = endIndex;
            }
        }
        return pivotIndex;
    }

    public static int findPivotCasosIndex(Vetor<DataBase> vetor, int startIndex, int endIndex) {

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        int start = vetor.encontrarElemento(startIndex).getAvailableConfirmed();
        int end = vetor.encontrarElemento(endIndex).getAvailableConfirmed();
        int middle = vetor.encontrarElemento(middleIndex).getAvailableConfirmed();
        int pivotIndex = 0;

        if (start > end) {
            if (start > middle) {
                if (end > middle) {
                    pivotIndex = endIndex;
                } else {
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = startIndex;
            }
        } else {
            if(end > middle) {
                if(start > middle) {
                    pivotIndex = startIndex;
                } else{
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = endIndex;
            }
        }
        return pivotIndex;
    }

    public static int findPivotCidadesIndex(Vetor<DataBase> vetor, int startIndex, int endIndex) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        String start = vetor.encontrarElemento(startIndex).getCity();
        String end = vetor.encontrarElemento(endIndex).getCity();
        String middle = vetor.encontrarElemento(middleIndex).getCity();
        int pivotIndex = 0;

        if (collator.compare(start, end) > 0) {
            if (collator.compare(start, middle) > 0) {
                if (collator.compare(end, middle) > 0) {
                    pivotIndex = endIndex;
                } else {
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = startIndex;
            }
        } else {
            if(collator.compare(end, middle) > 0) {
                if(collator.compare(start, middle) > 0) {
                    pivotIndex = startIndex;
                } else{
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = endIndex;
            }
        }
        return pivotIndex;
    }

    public static boolean hasMoreElements(int start, int end) {
        return start < end;
    }

}
