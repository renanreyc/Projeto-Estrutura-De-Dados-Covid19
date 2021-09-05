package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class HeapSort {

    public static final int CASOS = 0;
    public static final int OBITOS = 1;
    public static final int CIDADES = 2;
    private static int heapTamanho;

    public static void heapSort(Vetor<DataBase> vetor, int indicador) {
        buildMaxHeap(vetor, indicador);
        for (int i = vetor.getTamanho() - 1; i >= 0; i--) {
            HeapSort.heapTamanho--;
            trocaElementos(vetor, 0, i);
            maxHeapify(vetor, 0, indicador);
        }
    }

    private static void buildMaxHeap(Vetor<DataBase> vetor, int indicador) {
        switch (indicador) {
            case OBITOS:
                buildMaxHeapObitosConfirmados(vetor);
                break;
            case CASOS:
                buildMaxHeapCasosConfirmados(vetor);
                break;
            case CIDADES:
                buildMaxHeapCidades(vetor);
                break;
        }
    }

    private static void maxHeapify(Vetor<DataBase> vetor, int index, int indicador) {
        switch (indicador) {
            case CASOS:
                maxHeapifyCasosConfirmados(vetor, index);
                break;
            case OBITOS:
                maxHeapifyObitosConfirmados(vetor, index);
                break;
            case CIDADES:
                maxHeapifyCidades(vetor, index);
                break;
        }
    }

    private static void buildMaxHeapCasosConfirmados(Vetor<DataBase> vetor) {
        HeapSort.heapTamanho = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyCasosConfirmados(vetor, i);
        }
    }

    private static void buildMaxHeapObitosConfirmados(Vetor<DataBase> vetor) {
        HeapSort.heapTamanho = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyObitosConfirmados(vetor, i);
        }
    }

    private static void buildMaxHeapCidades(Vetor<DataBase> vetor) {
        HeapSort.heapTamanho = vetor.getTamanho() - 1;
        for (int i = (vetor.getTamanho() - 1) / 2; i >= 0; i--) {
            maxHeapifyCidades(vetor, i);
        }
    }

    private static void maxHeapifyCasosConfirmados(Vetor<DataBase> vetor, int index) {
        int maior;
        int esquerda = getElementoAEsquerdaIndex(index);
        int direita = getElementoADiretaIndex(index);

        if (esquerda <= HeapSort.heapTamanho && vetor.encontrarElemento(esquerda).getAvailableConfirmed() > vetor.encontrarElemento(index).getAvailableConfirmed())
            maior = esquerda;
        else maior = index;

        if (direita <= HeapSort.heapTamanho && vetor.encontrarElemento(direita).getAvailableConfirmed() > vetor.encontrarElemento(maior).getAvailableConfirmed())
            maior = direita;

        if (maior != index) {
            trocaElementos(vetor, index, maior);
            maxHeapifyCasosConfirmados(vetor, maior);
        }
    }

    private static void maxHeapifyObitosConfirmados(Vetor<DataBase> vetor, int index) {
        int maior;
        int esquerda = getElementoAEsquerdaIndex(index);
        int direita = getElementoADiretaIndex(index);

        if (esquerda <= HeapSort.heapTamanho && vetor.encontrarElemento(esquerda).getAvailableDeaths() > vetor.encontrarElemento(index).getAvailableDeaths())
            maior = esquerda;
        else maior = index;

        if (direita <= HeapSort.heapTamanho && vetor.encontrarElemento(direita).getAvailableDeaths() > vetor.encontrarElemento(maior).getAvailableDeaths())
            maior = direita;

        if (maior != index) {
            trocaElementos(vetor, index, maior);
            maxHeapifyObitosConfirmados(vetor, maior);
        }
    }

    private static void maxHeapifyCidades(Vetor<DataBase> vetor, int index) {
        int maior;
        int esquerda = getElementoAEsquerdaIndex(index);
        int direita = getElementoADiretaIndex(index);

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        if (esquerda <= HeapSort.heapTamanho && collator.compare(vetor.encontrarElemento(esquerda).getCity(), vetor.encontrarElemento(index).getCity()) > 0)
            maior = esquerda;
        else maior = index;

        if (direita <= HeapSort.heapTamanho && collator.compare(vetor.encontrarElemento(direita).getCity(), vetor.encontrarElemento(maior).getCity()) > 0)
            maior = direita;

        if (maior != index) {
            trocaElementos(vetor, index, maior);
            maxHeapifyCidades(vetor, maior);
        }
    }

    private static void trocaElementos(Vetor<DataBase> vetor, int origem, int destino) {
        DataBase aux = vetor.encontrarElemento(origem);
        vetor.inserirElemento(vetor.encontrarElemento(destino), origem);
        vetor.inserirElemento(aux, destino);
    }

    private static int getElementoAEsquerdaIndex(int index) {
        return 2 * index + 1;
    }

    private static int getElementoADiretaIndex(int index) {
        return 2 * index + 2;
    }

}
