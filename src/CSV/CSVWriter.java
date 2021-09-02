package CSV;

import dataBase.CovidBase;
import tools.Vector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void csvWriter(Vector<CovidBase> data, String pathNewFiles, String cabecalho) {
        try {
            File file = new File(pathNewFiles);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cabecalho);

            for (int i = 0; i < data.getTamanho(); i++) {
                String joinLineCovidBase = CSVWriter.joinLineCovidBase(data.encontrarIndex(i));
                bufferedWriter.write(joinLineCovidBase);
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String joinLineCovidBase(CovidBase data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data.getYearWeek() + ",");
        stringBuilder.append(data.getDate() + ",");
        stringBuilder.append(data.getOrderForPlace() + ",");
        stringBuilder.append(data.getState() + ",");
        stringBuilder.append(data.getCity() + ",");
        stringBuilder.append(data.getIbgeCode() + ",");
        stringBuilder.append(data.getPlaceType() + ",");
        stringBuilder.append(data.getAvailableConfirmed() + ",");
        stringBuilder.append(data.getAvailableConfirmedPer100K() + ",");
        stringBuilder.append(data.getNewConfirmed() + ",");
        stringBuilder.append(data.getAvailableDeaths() + ",");
        stringBuilder.append(data.getNewDeaths() + ",");
        stringBuilder.append(data.getLastAvailableDeathRate() + ",");
        stringBuilder.append(data.getEstimatedPopulation() + ",");
        stringBuilder.append(data.isLast() + ",");
        stringBuilder.append(data.isRepeated() + "\n");

        return stringBuilder.toString();
    }
}
