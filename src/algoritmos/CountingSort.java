package algoritmos;

import tools.DataBase;
import tools.Vetor;

public class CountingSort {

    public static Vetor<DataBase> ordenarPorCasosConfirmados(Vetor<DataBase> originalVetor) {
        Vetor<DataBase> arrayDeSaida = new Vetor<>(originalVetor.getTamanho());
        arrayDeSaida.setTamanho();

        //encontrar o maior valor do array
        int numeroMaximo = maiorValorCasosConfirmados(originalVetor);
        int[] vectorDePosicaoDeSaida = new int[numeroMaximo + 1];

        //contar cada número possível do array original
        for (int i = 0; i < originalVetor.getTamanho(); i++) {
            vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableConfirmed()] += 1;
        }

        //posicionar cada número possível na saída do array de posição
        for (int i = 1; i <= numeroMaximo; i++) {
            vectorDePosicaoDeSaida[i] += vectorDePosicaoDeSaida[i - 1];
        }

        for (int i = originalVetor.getTamanho() - 1; i >= 0; i--) {
            arrayDeSaida.inserirElemento(originalVetor.encontrarElemento(i), vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableConfirmed()] - 1);
            vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableConfirmed()] -= 1;
        }
        return arrayDeSaida;
    }

    public static Vetor<DataBase> ordenarPorObitosConfirmados(Vetor<DataBase> originalVetor) {
        Vetor<DataBase> arrayDeSaida = new Vetor<>(originalVetor.getTamanho());
        arrayDeSaida.setTamanho();

        //encontrar o maior valor do array
        int numeroMaximo = maiorValorObitosConfirmados(originalVetor);
        int[] vectorDePosicaoDeSaida = new int[numeroMaximo + 1];

        //contar cada número possível do array original
        for (int i = 0; i < originalVetor.getTamanho(); i++) {
            vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableDeaths()] += 1;
        }

        //posicionar cada número possível na saída do array de posição
        for (int i = 1; i <= numeroMaximo; i++) {
            vectorDePosicaoDeSaida[i] += vectorDePosicaoDeSaida[i - 1];
        }

        for (int i = originalVetor.getTamanho() - 1; i >= 0; i--) {
            arrayDeSaida.inserirElemento(originalVetor.encontrarElemento(i), vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableDeaths()] - 1);
            vectorDePosicaoDeSaida[originalVetor.encontrarElemento(i).getAvailableDeaths()] -= 1;
        }
        return arrayDeSaida;
    }

    private static int maiorValorCasosConfirmados(Vetor<DataBase> vetor) {
        int numeroMaximo = 0;
        for (int i = 0; i < vetor.getTamanho(); i++) {
            if (vetor.encontrarElemento(i).getAvailableConfirmed() > numeroMaximo) {
                numeroMaximo = vetor.encontrarElemento(i).getAvailableConfirmed();
            }
        }
        return numeroMaximo;
    }

    private static int maiorValorObitosConfirmados(Vetor<DataBase> vetor) {
        int numeroMaximo = 0;
        for (int i = 0; i < vetor.getTamanho(); i++) {
            if (vetor.encontrarElemento(i).getAvailableDeaths() > numeroMaximo){
                numeroMaximo = vetor.encontrarElemento(i).getAvailableDeaths();
            }
        }
        return numeroMaximo;
    }

}
