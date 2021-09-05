package algoritmos;

import tools.DataBase;
import tools.Vector;

import java.text.Collator;

public class InsertionSort {

    public static void ordenarPorObitos(Vector<DataBase> vector) {
        for(int unsortedElementIndex = 1; unsortedElementIndex < vector.size(); unsortedElementIndex++) {
            DataBase unsortedElement = vector.findWithIndex(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vector.findWithIndex(currentSortedElementIndex).getAvailableDeaths() > unsortedElement.getAvailableDeaths()) {
                vector.insert(vector.findWithIndex(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vector.insert(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorCasos(Vector<DataBase> vector) {
        for(int unsortedElementIndex=1; unsortedElementIndex < vector.size(); unsortedElementIndex++){
            DataBase unsortedElement = vector.findWithIndex(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vector.findWithIndex(currentSortedElementIndex).getAvailableConfirmed() > unsortedElement.getAvailableConfirmed()) {
                vector.insert(vector.findWithIndex(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vector.insert(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorNomeDasCidades(Vector<DataBase> vector) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for(int unsortedElementIndex = 1; unsortedElementIndex < vector.size(); unsortedElementIndex++) {
            DataBase unsortedElement = vector.findWithIndex(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 &&
                    (collator.compare(vector.findWithIndex(currentSortedElementIndex).getCity(), unsortedElement.getCity()) > 0)) {
                vector.insert(vector.findWithIndex(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vector.insert(unsortedElement, currentSortedElementIndex + 1);
        }
    }
}
