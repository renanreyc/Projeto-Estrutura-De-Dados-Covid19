package algoritmos;

import tools.DataBase;
import tools.Vetor;

public class CountingSort {

    public static Vetor<DataBase> ordenarPorCasos(Vetor<DataBase> originalVetor) {
        Vetor<DataBase> exitVetor = new Vetor<DataBase>(originalVetor.getTamanho());
        exitVetor.setTamanho();

        int numberMaximum = findMaximumNumberCasos(originalVetor);
        int[] vectorOfPositionsInExitVector = new int[numberMaximum + 1];

        for (int i = 0; i < originalVetor.getTamanho(); i++) {
            vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableConfirmed()] += 1;
        }

        for (int i = 1; i <= numberMaximum; i++) {
            vectorOfPositionsInExitVector[i] += vectorOfPositionsInExitVector[i - 1];
        }

        for(int i = originalVetor.getTamanho() - 1; i >= 0; i--) {
            exitVetor.inserirElemento(originalVetor.encontrarElemento(i), vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableConfirmed()] - 1);
            vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableConfirmed()] -= 1;
        }
        return exitVetor;
    }

    public static Vetor<DataBase> ordenarPorObitos(Vetor<DataBase> originalVetor) {
        Vetor<DataBase> exitVetor = new Vetor<DataBase>(originalVetor.getTamanho());
        exitVetor.setTamanho();

        int numberMaximum = findMaximumNumberObitos(originalVetor);
        int[] vectorOfPositionsInExitVector = new int[numberMaximum + 1];

        for (int i = 0; i < originalVetor.getTamanho(); i++) {
            vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableDeaths()] += 1;
        }

        for (int i = 1; i <= numberMaximum; i++) {
            vectorOfPositionsInExitVector[i] += vectorOfPositionsInExitVector[i - 1];
        }

        for(int i = originalVetor.getTamanho() - 1; i >= 0; i--) {
            exitVetor.inserirElemento(originalVetor.encontrarElemento(i), vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableDeaths()] - 1);
            vectorOfPositionsInExitVector[originalVetor.encontrarElemento(i).getAvailableDeaths()] -= 1;
        }
        return exitVetor;
    }


    private static int findMaximumNumberObitos(Vetor<DataBase> vetor) {
        int numberMaximum = 0;
        for (int i = 0; i < vetor.getTamanho(); i++) {
            if(vetor.encontrarElemento(i).getAvailableDeaths() > numberMaximum) numberMaximum = vetor.encontrarElemento(i).getAvailableDeaths();
        }
        return numberMaximum;
    }

    private static int findMaximumNumberCasos(Vetor<DataBase> vetor) {
        int numberMaximum = 0;
        for (int i = 0; i < vetor.getTamanho(); i++) {
            if(vetor.encontrarElemento(i).getAvailableConfirmed() > numberMaximum) numberMaximum = vetor.encontrarElemento(i).getAvailableConfirmed();
        }
        return numberMaximum;
    }

}
