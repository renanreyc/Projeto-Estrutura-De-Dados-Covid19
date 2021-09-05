package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class InsertionSort {

    public static void ordenarPorObitos(Vetor<DataBase> vetor) {
        for(int unsortedElementIndex = 1; unsortedElementIndex < vetor.getTamanho(); unsortedElementIndex++) {
            DataBase unsortedElement = vetor.encontrarElemento(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vetor.encontrarElemento(currentSortedElementIndex).getAvailableDeaths() > unsortedElement.getAvailableDeaths()) {
                vetor.inserirElemento(vetor.encontrarElemento(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vetor.inserirElemento(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorCasos(Vetor<DataBase> vetor) {
        for(int unsortedElementIndex = 1; unsortedElementIndex < vetor.getTamanho(); unsortedElementIndex++){
            DataBase unsortedElement = vetor.encontrarElemento(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vetor.encontrarElemento(currentSortedElementIndex).getAvailableConfirmed() > unsortedElement.getAvailableConfirmed()) {
                vetor.inserirElemento(vetor.encontrarElemento(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vetor.inserirElemento(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorNomeDasCidades(Vetor<DataBase> vetor) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for(int unsortedElementIndex = 1; unsortedElementIndex < vetor.getTamanho(); unsortedElementIndex++) {
            DataBase unsortedElement = vetor.encontrarElemento(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 &&
                    (collator.compare(vetor.encontrarElemento(currentSortedElementIndex).getCity(), unsortedElement.getCity()) > 0)) {
                vetor.inserirElemento(vetor.encontrarElemento(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vetor.inserirElemento(unsortedElement, currentSortedElementIndex + 1);
        }
    }
}
