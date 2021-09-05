package algoritmos;

import tools.DataBase;
import tools.Vector;

import java.text.Collator;

public class QuickMedianaDe3Sort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;

    public static void sort(Vector<DataBase> vector, int start, int end, int type) {
        if(hasMoreElements(start, end)) {
            int pivotIndex = 0;
            switch (type) {
                case OBITOS:
                    int pivot3Index = findPivotObitosIndex(vector, start, end);
                    exchangeElements(vector, pivot3Index, end);
                    pivotIndex = particionaObitos(vector, start, end);
                    break;
                case CASOS:
                    pivot3Index = findPivotCasosIndex(vector, start, end);
                    exchangeElements(vector, pivot3Index, end);
                    pivotIndex = particionaCasos(vector, start, end);
                    break;
                case CIDADES:
                    pivot3Index = findPivotCidadesIndex(vector, start, end);
                    exchangeElements(vector, pivot3Index, end);
                    pivotIndex = particionaCidades(vector, start, end);
                    break;
            }
            sort(vector, start, pivotIndex - 1, type);
            sort(vector, pivotIndex + 1, end, type);
        }
    }

    public static int particionaObitos(Vector<DataBase> vector, int start, int end) {
        DataBase pivot = vector.findWithIndex(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vector.findWithIndex(largerItemsThanPivotIndexController).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vector, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vector, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static int particionaCasos(Vector<DataBase> vector, int start, int end) {
        DataBase pivot = vector.findWithIndex(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vector.findWithIndex(largerItemsThanPivotIndexController).getAvailableConfirmed() <= pivot.getAvailableConfirmed()) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vector, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vector, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static int particionaCidades(Vector<DataBase> vector, int start, int end) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        DataBase pivot = vector.findWithIndex(end);
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(collator.compare(vector.findWithIndex(largerItemsThanPivotIndexController).getCity(), pivot.getCity()) <= 0) {
                smallerItemsThanPivotIndexController++;
                exchangeElements(vector, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeElements(vector, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }


    public static void exchangeElements(Vector<DataBase> vector, int source, int destination) {
        DataBase itemHoldedFromSource = vector.findWithIndex(source);
        vector.insert(vector.findWithIndex(destination), source);
        vector.insert(itemHoldedFromSource, destination);
    }

    public static int findPivotObitosIndex(Vector<DataBase> vector, int startIndex, int endIndex) {

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        int start = vector.findWithIndex(startIndex).getAvailableConfirmed();
        int end = vector.findWithIndex(endIndex).getAvailableConfirmed();
        int middle = vector.findWithIndex(middleIndex).getAvailableConfirmed();

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

    public static int findPivotCasosIndex(Vector<DataBase> vector, int startIndex, int endIndex) {

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        int start = vector.findWithIndex(startIndex).getAvailableConfirmed();
        int end = vector.findWithIndex(endIndex).getAvailableConfirmed();
        int middle = vector.findWithIndex(middleIndex).getAvailableConfirmed();
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

    public static int findPivotCidadesIndex(Vector<DataBase> vector, int startIndex, int endIndex) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int middleIndex = 0;
        if (endIndex % 2 == 0) middleIndex = (endIndex / 2) - 1;
        else middleIndex  = endIndex / 2;

        String start = vector.findWithIndex(startIndex).getCity();
        String end = vector.findWithIndex(endIndex).getCity();
        String middle = vector.findWithIndex(middleIndex).getCity();
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
