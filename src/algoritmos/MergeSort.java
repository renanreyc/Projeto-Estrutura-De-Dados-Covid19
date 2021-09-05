package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class MergeSort {

    public static final byte CASOS = 0;
    public static final byte OBITOS = 1;
    public static final byte CIDADES = 2;
    private static final String MAX_VALUE = "ZZZZZZZZ";

    public static void mergeSort(Vetor<DataBase> vetor, int inicio, int fim, int indicador) {
        if (inicio < fim) {
            int meio = (int) Math.floor((inicio + fim) / 2);
            mergeSort(vetor, inicio, meio, indicador);
            mergeSort(vetor, meio + 1, fim, indicador);

            switch (indicador) {
                case CASOS:
                    intercalaCasosConfirmados(vetor, inicio, meio, fim);
                    break;
                case OBITOS:
                    intercalaObitosConfirmados(vetor, inicio, meio, fim);
                    break;
                case CIDADES:
                    intercalaCidades(vetor, inicio, meio, fim);
                    break;
            }
        }
    }

    public static void intercalaCasosConfirmados(Vetor<DataBase> vetor, int inicio, int meio, int fim) {
        int tamInicioAoMeio = meio - inicio + 1;
        int tamMeioAoFim = fim - meio;

        Vetor<DataBase> vetorInicioAoMeio = new Vetor<>(tamInicioAoMeio + 1);
        Vetor<DataBase> vetorMeioAoFim = new Vetor<>(tamMeioAoFim + 1);

        for (int i = 0; i < tamInicioAoMeio; i++) {
            vetorInicioAoMeio.inserirElemento(vetor.encontrarElemento(inicio + i), i);
        }

        for (int j = 0; j < tamMeioAoFim; j++) {
            vetorMeioAoFim.inserirElemento(vetor.encontrarElemento(meio + j + 1), j);
        }

        vetorInicioAoMeio.inserirElemento(new DataBase(), tamInicioAoMeio);
        vetorInicioAoMeio.encontrarElemento(tamInicioAoMeio).setAvailableConfirmed(Integer.MAX_VALUE);

        vetorMeioAoFim.inserirElemento(new DataBase(), tamMeioAoFim);
        vetorMeioAoFim.encontrarElemento(tamMeioAoFim).setAvailableConfirmed(Integer.MAX_VALUE);

        int indiceInicioAoMeio = 0;
        int indiceMeioAoFim = 0;

        for (int k = inicio; k <= fim; k++) {
            if (vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio).getAvailableConfirmed() < vetorMeioAoFim.encontrarElemento(indiceMeioAoFim).getAvailableConfirmed()) {
                vetor.inserirElemento(vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio), k);
                indiceInicioAoMeio++;
            } else {
                vetor.inserirElemento(vetorMeioAoFim.encontrarElemento(indiceMeioAoFim), k);
                indiceMeioAoFim++;
            }
        }
    }

    public static void intercalaObitosConfirmados(Vetor<DataBase> vetor, int inicio, int meio, int fim) {
        int tamInicioAoMeio = meio - inicio + 1;
        int tamMeioAoFim = fim - meio;

        Vetor<DataBase> vetorInicioAoMeio = new Vetor<>(tamInicioAoMeio + 1);
        Vetor<DataBase> vetorMeioAoFim = new Vetor<>(tamMeioAoFim + 1);

        for (int i = 0; i < tamInicioAoMeio; i++) {
            vetorInicioAoMeio.inserirElemento(vetor.encontrarElemento(inicio + i), i);
        }

        for (int j = 0; j < tamMeioAoFim; j++) {
            vetorMeioAoFim.inserirElemento(vetor.encontrarElemento(meio + j + 1), j);
        }

        vetorInicioAoMeio.inserirElemento(new DataBase(), tamInicioAoMeio);
        vetorInicioAoMeio.encontrarElemento(tamInicioAoMeio).setAvailableDeaths(Integer.MAX_VALUE);

        vetorMeioAoFim.inserirElemento(new DataBase(), tamMeioAoFim);
        vetorMeioAoFim.encontrarElemento(tamMeioAoFim).setAvailableDeaths(Integer.MAX_VALUE);

        int indiceInicioAoMeio = 0;
        int indiceMeioAoFim = 0;

        for (int k = inicio; k <= fim; k++) {
            if (vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio).getAvailableDeaths() < vetorMeioAoFim.encontrarElemento(indiceMeioAoFim).getAvailableDeaths()) {
                vetor.inserirElemento(vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio), k);
                indiceInicioAoMeio++;
            } else {
                vetor.inserirElemento(vetorMeioAoFim.encontrarElemento(indiceMeioAoFim), k);
                indiceMeioAoFim++;
            }
        }
    }

    public static void intercalaCidades(Vetor<DataBase> vetor, int inicio, int meio, int fim) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int tamInicioAoMeio = meio - inicio + 1;
        int tamMeioAoFim = fim - meio;

        Vetor<DataBase> vetorInicioAoMeio = new Vetor<>(tamInicioAoMeio + 1);
        Vetor<DataBase> vetorMeioAoFim = new Vetor<>(tamMeioAoFim + 1);

        for (int i = 0; i < tamInicioAoMeio; i++) {
            vetorInicioAoMeio.inserirElemento(vetor.encontrarElemento(inicio + i), i);
        }

        for (int j = 0; j < tamMeioAoFim; j++) {
            vetorMeioAoFim.inserirElemento(vetor.encontrarElemento(meio + j + 1), j);
        }

        vetorInicioAoMeio.inserirElemento(new DataBase(), tamInicioAoMeio);
        vetorInicioAoMeio.encontrarElemento(tamInicioAoMeio).setCity(MAX_VALUE);

        vetorMeioAoFim.inserirElemento(new DataBase(), tamMeioAoFim);
        vetorMeioAoFim.encontrarElemento(tamMeioAoFim).setCity(MAX_VALUE);

        int indiceInicioAoMeio = 0;
        int indiceMeioAoFim = 0;

        for (int k = inicio; k <= fim; k++) {
            if (collator.compare(vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio).getCity(), vetorMeioAoFim.encontrarElemento(indiceMeioAoFim).getCity()) < 0) {
                vetor.inserirElemento(vetorInicioAoMeio.encontrarElemento(indiceInicioAoMeio), k);
                indiceInicioAoMeio++;
            } else {
                vetor.inserirElemento(vetorMeioAoFim.encontrarElemento(indiceMeioAoFim), k);
                indiceMeioAoFim++;
            }
        }
    }
}
