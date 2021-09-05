package algoritmos;

import tools.DataBase;
import tools.Vetor;

import java.text.Collator;

public class MergeSort {

    public static final int OBITOS = 0;
    public static final int CASOS = 1;
    public static final int CIDADES = 2;
    private static final String MAX_VALUE = "ZZZZZ";

    public static void sort(Vetor<DataBase> vetor, int start, int end, int type) {
        if(hasMoreElements(start, end)) {
            int middle = (int) Math.floor((start + end)/ 2);
            sort(vetor, start, middle, type);
            sort(vetor, middle + 1, end,  type);

            switch (type) {
                case OBITOS:
                    intercalaObitos(vetor, start, middle, end);
                    break;
                case CASOS:
                    intercalaCasos(vetor, start, middle, end);
                    break;
                case CIDADES:
                    intercalaCidades(vetor, start, middle, end);
                    break;
            }
        }
    }

    public static void intercalaObitos(Vetor<DataBase> vetor, int start, int middle, int end) {
        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vetor<DataBase> vetorStartToMid = new Vetor<DataBase>(sizeStartToMiddle + 1);
        Vetor<DataBase> vetorMidToEnd = new Vetor<DataBase>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vetorStartToMid.inserirElemento(vetor.encontrarElemento(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vetorMidToEnd.inserirElemento(vetor.encontrarElemento(middle + currentIndex + 1), currentIndex);
        }

        vetorStartToMid.inserirElemento( new DataBase(), sizeStartToMiddle);
        vetorStartToMid.encontrarElemento(sizeStartToMiddle).setAvailableDeaths(Integer.MAX_VALUE);

        vetorMidToEnd.inserirElemento( new DataBase(), sizeMiddleToEnd);
        vetorMidToEnd.encontrarElemento(sizeMiddleToEnd).setAvailableDeaths(Integer.MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex).getAvailableDeaths() <= vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex).getAvailableDeaths()) {
                vetor.inserirElemento(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else {
                vetor.inserirElemento(vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static void intercalaCasos(Vetor<DataBase> vetor, int start, int middle, int end) {
        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vetor<DataBase> vetorStartToMid = new Vetor<DataBase>(sizeStartToMiddle + 1);
        Vetor<DataBase> vetorMidToEnd = new Vetor<DataBase>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vetorStartToMid.inserirElemento(vetor.encontrarElemento(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vetorMidToEnd.inserirElemento(vetor.encontrarElemento(middle + currentIndex + 1), currentIndex);
        }

        vetorStartToMid.inserirElemento( new DataBase(), sizeStartToMiddle);
        vetorStartToMid.encontrarElemento(sizeStartToMiddle).setAvailableConfirmed(Integer.MAX_VALUE);

        vetorMidToEnd.inserirElemento( new DataBase(), sizeMiddleToEnd);
        vetorMidToEnd.encontrarElemento(sizeMiddleToEnd).setAvailableConfirmed(Integer.MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex).getAvailableConfirmed() <= vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex).getAvailableConfirmed()) {
                vetor.inserirElemento(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else {
                vetor.inserirElemento(vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static void intercalaCidades(Vetor<DataBase> vetor, int start, int middle, int end) {

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);

        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        Vetor<DataBase> vetorStartToMid = new Vetor<DataBase>(sizeStartToMiddle + 1);
        Vetor<DataBase> vetorMidToEnd = new Vetor<DataBase>(sizeMiddleToEnd + 1);

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vetorStartToMid.inserirElemento(vetor.encontrarElemento(start + currentIndex), currentIndex);
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vetorMidToEnd.inserirElemento(vetor.encontrarElemento(middle + currentIndex + 1), currentIndex);
        }

        vetorStartToMid.inserirElemento( new DataBase(), sizeStartToMiddle);
        vetorStartToMid.encontrarElemento(sizeStartToMiddle).setCity(MAX_VALUE);

        vetorMidToEnd.inserirElemento( new DataBase(), sizeMiddleToEnd);
        vetorMidToEnd.encontrarElemento(sizeMiddleToEnd).setCity(MAX_VALUE);

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if (collator.compare(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex).getCity(), vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex).getCity()) < 0) {
                vetor.inserirElemento(vetorStartToMid.encontrarElemento(currentStartToMiddleIndex), currentVectorIndex);
                currentStartToMiddleIndex++;
            } else  {
                vetor.inserirElemento(vetorMidToEnd.encontrarElemento(currentMiddleToEndIndex), currentVectorIndex);
                currentMiddleToEndIndex++;
            }
        }
    }

    public static boolean hasMoreElements(int start, int end) {
        return start < end;
    }

}
