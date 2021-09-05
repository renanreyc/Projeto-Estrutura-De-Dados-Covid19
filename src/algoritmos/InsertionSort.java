package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class InsertionSort {

    public static void ordenarPorCasos(Vetor<DataBase> vetor) {
        for (int i = 1; i < vetor.getTamanho(); i++) {
            DataBase LinhaParaOrdenar = vetor.encontrarElemento(i);
            int j = i - 1;
            while (j >= 0 && vetor.encontrarElemento(j).getAvailableConfirmed() > LinhaParaOrdenar.getAvailableConfirmed()) {
                vetor.inserirElemento(vetor.encontrarElemento(j), j + 1);
                j--;
            }
            vetor.inserirElemento(LinhaParaOrdenar, j + 1);
        }
    }

    public static void ordenarPorObitos(Vetor<DataBase> vetor) {
        for (int i = 1; i < vetor.getTamanho(); i++) {
            DataBase unsortedElement = vetor.encontrarElemento(i);
            int j = i - 1;
            while (j >= 0 && vetor.encontrarElemento(j).getAvailableDeaths() > unsortedElement.getAvailableDeaths()) {
                vetor.inserirElemento(vetor.encontrarElemento(j), j + 1);
                j--;
            }
            vetor.inserirElemento(unsortedElement, j + 1);
        }
    }

    public static void ordenarPorNomeDasCidades(Vetor<DataBase> vetor) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for (int i = 1; i < vetor.getTamanho(); i++) {
            DataBase unsortedElement = vetor.encontrarElemento(i);
            int j = i - 1;
            while (j >= 0 &&
                    (collator.compare(vetor.encontrarElemento(j).getCity(), unsortedElement.getCity()) > 0)) {
                vetor.inserirElemento(vetor.encontrarElemento(j), j + 1);
                j--;
            }
            vetor.inserirElemento(unsortedElement, j + 1);
        }
    }
}
