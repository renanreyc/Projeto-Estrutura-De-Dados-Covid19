package algoritmos;

import tools.CovidData;
import tools.Vector;

import java.text.Collator;

public class MergeSort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;
    private static final String MAX_VALUE = "ŹŹŹŹŹ";

    public static void sort(Vector<CovidData> vector, int start, int end, int type) {
        if(hasMoreElements(start, end)) {
            int middle = (int) Math.floor((start + end)/ 2);
            sort(vector, start, middle, type);
            sort(vector, middle + 1, end,  type);

            switch (type) {
                case OBITOS:
                    intercalaObitos(vector, start, middle, end);
                    break;
                case CASOS:
                    intercalaCasos(vector, start, middle, end);
                    break;
                case CIDADES:
                    intercalaCidades(vector, start, middle, end);
                    break;
            }
        }
    }

    public static void intercalaObitos(Vector<CovidData> vector, int start, int middle, int end) {
        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vector<CovidData> vectorStartToMid = new Vector<CovidData>(sizeStartToMiddle + 1);
        Vector<CovidData>  vectorMidToEnd = new Vector<CovidData>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vectorStartToMid.insert(vector.findWithIndex(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vectorMidToEnd.insert(vector.findWithIndex(middle + currentIndex + 1), currentIndex);
        }

        vectorStartToMid.insert( new CovidData(), sizeStartToMiddle);
        vectorStartToMid.findWithIndex(sizeStartToMiddle).setAvailableDeaths(Integer.MAX_VALUE);

        vectorMidToEnd.insert( new CovidData(), sizeMiddleToEnd);
        vectorMidToEnd.findWithIndex(sizeMiddleToEnd).setAvailableDeaths(Integer.MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if(vectorStartToMid.findWithIndex(currentStartToMiddleIndex).getAvailableDeaths() <= vectorMidToEnd.findWithIndex(currentMiddleToEndIndex).getAvailableDeaths()) {
                vector.insert(vectorStartToMid.findWithIndex(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else {
                vector.insert(vectorMidToEnd.findWithIndex(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static void intercalaCasos(Vector<CovidData> vector, int start, int middle, int end) {
        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vector<CovidData> vectorStartToMid = new Vector<CovidData>(sizeStartToMiddle + 1);
        Vector<CovidData>  vectorMidToEnd = new Vector<CovidData>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vectorStartToMid.insert(vector.findWithIndex(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vectorMidToEnd.insert(vector.findWithIndex(middle + currentIndex + 1), currentIndex);
        }

        vectorStartToMid.insert( new CovidData(), sizeStartToMiddle);
        vectorStartToMid.findWithIndex(sizeStartToMiddle).setAvailableConfirmed(Integer.MAX_VALUE);

        vectorMidToEnd.insert( new CovidData(), sizeMiddleToEnd);
        vectorMidToEnd.findWithIndex(sizeMiddleToEnd).setAvailableConfirmed(Integer.MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if(vectorStartToMid.findWithIndex(currentStartToMiddleIndex).getAvailableConfirmed() <= vectorMidToEnd.findWithIndex(currentMiddleToEndIndex).getAvailableConfirmed()) {
                vector.insert(vectorStartToMid.findWithIndex(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else {
                vector.insert(vectorMidToEnd.findWithIndex(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static void intercalaCidades(Vector<CovidData> vector, int start, int middle, int end) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vector<CovidData> vectorStartToMid = new Vector<CovidData>(sizeStartToMiddle + 1);
        Vector<CovidData>  vectorMidToEnd = new Vector<CovidData>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vectorStartToMid.insert(vector.findWithIndex(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vectorMidToEnd.insert(vector.findWithIndex(middle + currentIndex + 1), currentIndex);
        }

        vectorStartToMid.insert( new CovidData(), sizeStartToMiddle);
        vectorStartToMid.findWithIndex(sizeStartToMiddle).setCity(MAX_VALUE);

        vectorMidToEnd.insert( new CovidData(), sizeMiddleToEnd);
        vectorMidToEnd.findWithIndex(sizeMiddleToEnd).setCity(MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if (collator.compare(vectorStartToMid.findWithIndex(currentStartToMiddleIndex).getCity(), vectorMidToEnd.findWithIndex(currentMiddleToEndIndex).getCity()) < 0) {
                vector.insert(vectorStartToMid.findWithIndex(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else  {
                vector.insert(vectorMidToEnd.findWithIndex(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static boolean hasMoreElements(int start, int end) {
        return start < end;
    }

}
