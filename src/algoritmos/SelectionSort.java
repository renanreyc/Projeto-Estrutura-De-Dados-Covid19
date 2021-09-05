package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class SelectionSort {

    public static final byte CRESCENTE = 1;
    public static final byte DECRESCENTE = -1;

    public static void ordenarPorCasosConfirmados(Vetor<DataBase> vetor, int classificacao) {
        for (int i = 0; i < vetor.getTamanho(); i++) {
            DataBase LinhaParaOrdenar = vetor.encontrarElemento(i);
            int indexMenorNumero = i;
            int proximoATestar = i + 1;
            for (int j = proximoATestar; j < vetor.getTamanho(); j++) {

                boolean test;
                if (classificacao == SelectionSort.CRESCENTE) {
                    test = vetor.encontrarElemento(j).getAvailableConfirmed() < vetor.encontrarElemento(indexMenorNumero).getAvailableConfirmed();
                } else {
                    test = vetor.encontrarElemento(j).getAvailableConfirmed() > vetor.encontrarElemento(indexMenorNumero).getAvailableConfirmed();
                }

                if (test) {
                    indexMenorNumero = j;
                }
            }
            vetor.inserirElemento(vetor.encontrarElemento(indexMenorNumero), i);
            vetor.inserirElemento(LinhaParaOrdenar, indexMenorNumero);
        }
    }

    public static void ordenarPorObitosConfirmados(Vetor<DataBase> vetor, int classificacao) {
        for (int i = 0; i < vetor.getTamanho(); i++) {
            DataBase LinhaParaOrdenar = vetor.encontrarElemento(i);
            int indexMenorNumero = i;
            int proximoATestar = i + 1;

            for (int j = proximoATestar; j < vetor.getTamanho(); j++) {

                boolean test;
                if (classificacao == SelectionSort.CRESCENTE) {
                    test = vetor.encontrarElemento(j).getAvailableDeaths() < vetor.encontrarElemento(indexMenorNumero).getAvailableDeaths();
                } else {
                    test = vetor.encontrarElemento(j).getAvailableDeaths() > vetor.encontrarElemento(indexMenorNumero).getAvailableDeaths();
                }

                if (test) {
                    indexMenorNumero = j;
                }
            }

            vetor.inserirElemento(vetor.encontrarElemento(indexMenorNumero), i);
            vetor.inserirElemento(LinhaParaOrdenar, indexMenorNumero);
        }
    }


    public static void ordenarPorCidades(Vetor<DataBase> vetor, int classificacao) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for (int i = 0; i < vetor.getTamanho(); i++) {
            DataBase LinhaParaOrdenar = vetor.encontrarElemento(i);
            int indexMenorNumero = i;
            int proximoATestar = i + 1;
            for (int j = proximoATestar; j < vetor.getTamanho(); j++) {

                boolean test;
                if (classificacao == SelectionSort.CRESCENTE) {
                    test = collator.compare(vetor.encontrarElemento(j).getCity(), vetor.encontrarElemento(indexMenorNumero).getCity()) < 0;
                } else {
                    test = collator.compare(vetor.encontrarElemento(j).getCity(), vetor.encontrarElemento(indexMenorNumero).getCity()) > 0;
                }

                if (test) {
                    indexMenorNumero = j;
                }
            }
            vetor.inserirElemento(vetor.encontrarElemento(indexMenorNumero), i);
            vetor.inserirElemento(LinhaParaOrdenar, indexMenorNumero);
        }
    }

}
