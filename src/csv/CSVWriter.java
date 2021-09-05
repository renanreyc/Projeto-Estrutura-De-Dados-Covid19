package csv;

import tools.DataBase;
import tools.Vetor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void writerFile(Vetor<DataBase> data, String newPath, String primeira_linha) {
        try {
            File arquivo = new File(newPath);
            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(primeira_linha);

            for(int i = 0; i < data.getTamanho(); i++) {
                String covidDataEmString = CSVWriter.juntarColunas(data.encontrarElemento(i));
                writer.write(covidDataEmString);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    private static String juntarColunas(DataBase data) {
        String separador = ",";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data.getYearWeek() + separador);
        stringBuilder.append(data.getDate() + separador);
        stringBuilder.append(data.getOrderForPlace() + separador);
        stringBuilder.append(data.getState() + separador);
        stringBuilder.append(data.getCity() + separador);
        stringBuilder.append(data.getIbgeCode() + separador);
        stringBuilder.append(data.getPlaceType() + separador);
        stringBuilder.append(data.getAvailableConfirmed() + separador);
        stringBuilder.append(data.getAvailableConfirmedPer100K() + separador);
        stringBuilder.append(data.getNewConfirmed() + separador);
        stringBuilder.append(data.getAvailableDeaths() + separador);
        stringBuilder.append(data.getNewDeaths() + separador);
        stringBuilder.append(data.getLastAvailableDeathRate() + separador);
        stringBuilder.append(data.getEstimatedPopulation() + separador);
        stringBuilder.append(data.isLast() + separador);
        stringBuilder.append(data.isRepeated() + "\n");

        return stringBuilder.toString();
    }

}
