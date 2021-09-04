package algoritmos;

import tools.CovidData;
import tools.Vector;

public class CountingSort {

    public static Vector<CovidData> ordenarPorObitos(Vector<CovidData> originalVector) {
        Vector<CovidData> exitVector = new Vector<CovidData>(originalVector.size());
        exitVector.setSize();

        int numberMaximum = findMaximumNumberObitos(originalVector);
        int[] vectorOfPositionsInExitVector = new int[numberMaximum + 1];

        // Here has the amount of each number possible in original vector
        for (int i = 0; i < originalVector.size(); i++) {
            vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableDeaths()] += 1;
        }

        // Here has the positions of each number in exit vector
        for (int i = 1; i <= numberMaximum; i++) {
            vectorOfPositionsInExitVector[i] += vectorOfPositionsInExitVector[i - 1];
        }

        for(int i = originalVector.size() - 1; i >= 0; i--) {
            exitVector.insert(originalVector.findWithIndex(i), vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableDeaths()] - 1);
            vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableDeaths()] -= 1;
        }
        return exitVector;
    }

    public static Vector<CovidData> ordenarPorCasos(Vector<CovidData> originalVector) {
        Vector<CovidData> exitVector = new Vector<CovidData>(originalVector.size());
        exitVector.setSize();

        int numberMaximum = findMaximumNumberCasos(originalVector);
        int[] vectorOfPositionsInExitVector = new int[numberMaximum + 1];

        // Here has the amount of each number possible in original vector
        for (int i = 0; i < originalVector.size(); i++) {
            vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableConfirmed()] += 1;
        }

        // Here has the positions of each number in exit vector
        for (int i = 1; i <= numberMaximum; i++) {
            vectorOfPositionsInExitVector[i] += vectorOfPositionsInExitVector[i - 1];
        }

        for(int i = originalVector.size() - 1; i >= 0; i--) {
            exitVector.insert(originalVector.findWithIndex(i), vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableConfirmed()] - 1);
            vectorOfPositionsInExitVector[originalVector.findWithIndex(i).getAvailableConfirmed()] -= 1;
        }
        return exitVector;
    }

    private static int findMaximumNumberObitos(Vector<CovidData> vector) {
        int numberMaximum = 0;
        for (int i = 0; i < vector.size(); i++) {
            if(vector.findWithIndex(i).getAvailableDeaths() > numberMaximum) numberMaximum = vector.findWithIndex(i).getAvailableDeaths();
        }
        return numberMaximum;
    }

    private static int findMaximumNumberCasos(Vector<CovidData>  vector) {
        int numberMaximum = 0;
        for (int i = 0; i < vector.size(); i++) {
            if(vector.findWithIndex(i).getAvailableConfirmed() > numberMaximum) numberMaximum = vector.findWithIndex(i).getAvailableConfirmed();
        }
        return numberMaximum;
    }

}
