package algoritmos;

import dataBase.CovidBase;
import tools.Vector;

import java.text.Collator;

public class SelectionSort {

    public static final int INCREASE = 0;
    public static final int DECREASE = 1;

    public static void orderAvailableDeaths(java.util.Vector vector, int typeSort){
//        for (int i = 0; i < vector.size();i++){
//            CovidBase baseCovidParaOrdenar;
//
//            int indexMenorNumero = i;
//            int proximoATestar = i + 1;
//
//            for (int j = proximoATestar; j < vector.size(); j++) {
//                boolean compareResult;
//                if(typeSort == SelectionSort.INCREASE) compareResult = vector.encontrarIndex(j).getAvailableDeaths() < vector.encontrarIndex(indexMenorNumero).getAvailableDeaths();
//                else compareResult =  vector.encontrarIndex(j).getAvailableDeaths() > vector.encontrarIndex(indexMenorNumero).getAvailableDeaths();
//
//                if(compareResult) {
//                    indexMenorNumero = j;
//                }
//            }
//            vector.insert(vector.encontrarIndex(indexMenorNumero), i);
//            vector.insert(baseCovidParaOrdenar, indexMenorNumero);
//        }
//    }
//
//    public static void orderAvailableConfirmed(Vector<CovidBase> vector, int typeSort){
//        for (int i = 0; i < vector.size();i++){
//            CovidBase baseCovidParaOrdenar = vector.encontrarIndex(i);
//
//            int indexMenorNumero = i;
//            int proximoATestar = i + 1;
//
//            for (int j = proximoATestar; j < vector.size(); j++) {
//                boolean compareResult;
//                if(typeSort == SelectionSort.INCREASE) compareResult = vector.encontrarIndex(j).getAvailableConfirmed() < vector.encontrarIndex(indexMenorNumero).getAvailableConfirmed();
//                else compareResult =  vector.encontrarIndex(j).getAvailableConfirmed() > vector.encontrarIndex(indexMenorNumero).getAvailableConfirmed();
//
//                if(compareResult) {
//                    indexMenorNumero = j;
//                }
//            }
//            vector.insert(vector.encontrarIndex(indexMenorNumero), i);
//            vector.insert(baseCovidParaOrdenar, indexMenorNumero);
//        }
//    }
//
//    public static void orderCityName(Vector<CovidBase> vector, int typeSort){
//
//        Collator collator = Collator.getInstance();
//        collator.setStrength(Collator.NO_DECOMPOSITION);
//
//        for (int i = 0; i < vector.size();i++){
//            CovidBase baseCovidParaOrdenar = vector.encontrarIndex(i);
//
//            int indexMenorNumero = i;
//            int proximoATestar = i + 1;
//
//            for (int j = proximoATestar; j < vector.size(); j++) {
//                boolean compareResult;
//                if(typeSort == SelectionSort.INCREASE) compareResult = collator.compare(vector.encontrarIndex(j).getCity(),vector.encontrarIndex(indexMenorNumero).getCity()) < 0;
//                else compareResult =  collator.compare(vector.encontrarIndex(j).getCity(), vector.encontrarIndex(indexMenorNumero).getCity()) > 0;
//
//                if(compareResult) {
//                    indexMenorNumero = j;
//                }
//            }
//            vector.insert(vector.encontrarIndex(indexMenorNumero), i);
//            vector.insert(baseCovidParaOrdenar, indexMenorNumero);
//        }
    }
}
