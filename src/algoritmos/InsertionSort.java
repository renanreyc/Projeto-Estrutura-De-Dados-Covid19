package algoritmos;

import tools.CovidData;
import tools.Vector;

import java.text.Collator;

public class InsertionSort {

    public static void ordenarPorObitos(Vector<CovidData> vector) {
        for(int unsortedElementIndex = 1; unsortedElementIndex < vector.size(); unsortedElementIndex++) {
            CovidData unsortedElement = vector.findWithIndex(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vector.findWithIndex(currentSortedElementIndex).getAvailableDeaths() > unsortedElement.getAvailableDeaths()) {
                vector.insert(vector.findWithIndex(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vector.insert(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorCasos(Vector<CovidData> vector) {
        for(int unsortedElementIndex=1; unsortedElementIndex < vector.size(); unsortedElementIndex++){
            CovidData unsortedElement = vector.findWithIndex(unsortedElementIndex);
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vector.findWithIndex(currentSortedElementIndex).getAvailableConfirmed() > unsortedElement.getAvailableConfirmed()) {
                vector.insert(vector.findWithIndex(currentSortedElementIndex), currentSortedElementIndex + 1);
                currentSortedElementIndex--;
            }
            vector.insert(unsortedElement, currentSortedElementIndex + 1);
        }
    }

    public static void ordenarPorNomeDasCidades(Vector<CovidData> vector) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for(int unsortedElementIndex = 1; unsortedElementIndex < vector.size(); unsortedElementIndex++) {
            CovidData unsortedElement = vector.findWithIndex(unsortedElementIndex);
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
