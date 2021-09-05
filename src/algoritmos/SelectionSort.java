package algoritmos;

import tools.DataBase;
import tools.Vector;

import java.text.Collator;

public class SelectionSort {

    public static final int INCREASE = 0;
    public static final int DECREASE = 1;

    public static void ordenarPorObitos(Vector<DataBase> vector, int typeSort) {
        for (int i = 0; i < vector.size(); i++) {
            DataBase currentDataBaseToOrder = vector.findWithIndex(i);
            int lowestCovidDataIndex = i;
            int nextCovidDataOfSearch = i + 1;

            for (int j = nextCovidDataOfSearch; j < vector.size(); j++) {

                boolean compareResult;
                if(typeSort == SelectionSort.INCREASE) compareResult = vector.findWithIndex(j).getAvailableDeaths() < vector.findWithIndex(lowestCovidDataIndex).getAvailableDeaths();
                else compareResult =  vector.findWithIndex(j).getAvailableDeaths() > vector.findWithIndex(lowestCovidDataIndex).getAvailableDeaths();

                if(compareResult) {
                    lowestCovidDataIndex = j;
                }
            }

            vector.insert(vector.findWithIndex(lowestCovidDataIndex), i);
            vector.insert(currentDataBaseToOrder, lowestCovidDataIndex);
        }
    }

    public static void ordenarPorCasos(Vector<DataBase> vector, int typeSort) {
        for (int i=0; i < vector.size(); i++) {
            DataBase currentDataBaseToOrder = vector.findWithIndex(i);
            int lowestCovidDataIndex = i;
            int nextCovidDataOfSearch = i + 1;
            for (int j = nextCovidDataOfSearch; j < vector.size(); j++) {

                boolean compareResult;
                if(typeSort == SelectionSort.INCREASE) compareResult = vector.findWithIndex(j).getAvailableConfirmed() < vector.findWithIndex(lowestCovidDataIndex).getAvailableConfirmed();
                else compareResult =  vector.findWithIndex(j).getAvailableConfirmed() > vector.findWithIndex(lowestCovidDataIndex).getAvailableConfirmed();

                if(compareResult) {
                    lowestCovidDataIndex = j;
                }
            }
            vector.insert(vector.findWithIndex(lowestCovidDataIndex), i);
            vector.insert(currentDataBaseToOrder, lowestCovidDataIndex);
        }
    }

    public static void ordenarPorNomeDasCidades(Vector<DataBase> vector, int typeSort) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        for (int i=0; i < vector.size(); i++) {
            DataBase currentDataBaseToOrder = vector.findWithIndex(i);
            int lowestCovidDataIndex = i;
            int nextCovidDataOfSearch = i + 1;
            for (int j = nextCovidDataOfSearch; j < vector.size(); j++) {

                boolean compareResult;
                if(typeSort == SelectionSort.INCREASE) compareResult = collator.compare(vector.findWithIndex(j).getCity(), vector.findWithIndex(lowestCovidDataIndex).getCity()) < 0;
                else compareResult = collator.compare(vector.findWithIndex(j).getCity(), vector.findWithIndex(lowestCovidDataIndex).getCity()) > 0;

                if (compareResult) {
                    lowestCovidDataIndex = j;
                }
            }
            vector.insert(vector.findWithIndex(lowestCovidDataIndex), i);
            vector.insert(currentDataBaseToOrder, lowestCovidDataIndex);
        }
    }

}
