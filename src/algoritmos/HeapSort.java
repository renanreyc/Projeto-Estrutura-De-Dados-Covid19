package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class HeapSort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;
    private static int heapSize;

    public static void sort(Vetor<DataBase> vetor, int type) {
        buildMaxHeap(vetor, type);
        for (int i = vetor.getTamanho() - 1; i >= 0; i--) {
            HeapSort.heapSize--;
            exchangeElements(vetor, 0, i);
            maxHeapify(vetor, 0, type);
        }
    }

    private static void buildMaxHeap(Vetor<DataBase> vetor, int type) {
        switch (type) {
            case OBITOS:
                buildMaxHeapDeaths(vetor);
                break;
            case CASOS:
                buildMaxHeapConfirmeds(vetor);
                break;
            case CIDADES:
                buildMaxHeapCitys(vetor);
                break;
        }
    }

    private static void buildMaxHeapDeaths(Vetor<DataBase> vetor) {
        HeapSort.heapSize = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyDeaths(vetor, i);
        }
    }

    private static void buildMaxHeapConfirmeds(Vetor<DataBase> vetor) {
        HeapSort.heapSize = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyConfirmeds(vetor, i);
        }
    }

    private static void buildMaxHeapCitys(Vetor<DataBase> vetor) {
        HeapSort.heapSize = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyCitys(vetor, i);
        }
    }

    private static void maxHeapify(Vetor<DataBase> vetor, int index, int type) {
        switch (type) {
            case OBITOS:
                maxHeapifyDeaths(vetor, index);
                break;
            case CASOS:
                maxHeapifyConfirmeds(vetor, index);
                break;
            case CIDADES:
                maxHeapifyCitys(vetor, index);
                break;
        }
    }

    private static void maxHeapifyDeaths(Vetor<DataBase> vetor, int index) {
        int bigger;
        int left = getLeftIndex(index);
        int right = getRightIndex(index);

        if(left <= HeapSort.heapSize && vetor.encontrarElemento(left).getAvailableDeaths() > vetor.encontrarElemento(index).getAvailableDeaths()) bigger = left;
        else bigger = index;

        if(right <= HeapSort.heapSize && vetor.encontrarElemento(right).getAvailableDeaths() > vetor.encontrarElemento(bigger).getAvailableDeaths()) bigger = right;

        if(bigger != index) {
            exchangeElements(vetor, index, bigger);
            maxHeapifyDeaths(vetor, bigger);
        }
    }

    private static void maxHeapifyConfirmeds(Vetor<DataBase> vetor, int index) {
        int bigger;
        int left = getLeftIndex(index);
        int right = getRightIndex(index);

        if(left <= HeapSort.heapSize && vetor.encontrarElemento(left).getAvailableConfirmed() > vetor.encontrarElemento(index).getAvailableConfirmed()) bigger = left;
        else bigger = index;

        if(right <= HeapSort.heapSize && vetor.encontrarElemento(right).getAvailableConfirmed() > vetor.encontrarElemento(bigger).getAvailableConfirmed()) bigger = right;

        if(bigger != index) {
            exchangeElements(vetor, index, bigger);
            maxHeapifyConfirmeds(vetor, bigger);
        }
    }

    private static void maxHeapifyCitys(Vetor<DataBase> vetor, int index) {
        int bigger;
        int left = getLeftIndex(index);
        int right = getRightIndex(index);

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        if(left <= HeapSort.heapSize && collator.compare(vetor.encontrarElemento(left).getCity(), vetor.encontrarElemento(index).getCity()) > 0) bigger = left;
        else bigger = index;

        if(right <= HeapSort.heapSize && collator.compare(vetor.encontrarElemento(right).getCity(), vetor.encontrarElemento(bigger).getCity()) > 0) bigger = right;

        if(bigger != index) {
            exchangeElements(vetor, index, bigger);
            maxHeapifyCitys(vetor, bigger);
        }
    }

    public static void exchangeElements(Vetor<DataBase> vetor, int source, int destination) {
        DataBase itemHoldedFromSource = vetor.encontrarElemento(source);
        vetor.inserirElemento(vetor.encontrarElemento(destination), source);
        vetor.inserirElemento(itemHoldedFromSource, destination);
    }

    private static int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    private static int getRightIndex(int index) {
        return 2 * index + 2;
    }

}
