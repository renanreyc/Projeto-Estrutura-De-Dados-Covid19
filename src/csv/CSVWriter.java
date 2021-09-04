package csv;

import tools.CovidData;
import tools.Vector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void escreverDados(Vector<CovidData> dados, String caminhoParaSalvar, String cabecalho) {
        try {
            File arquivo = new File(caminhoParaSalvar);
            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(cabecalho);

            for(int i = 0; i < dados.size(); i++) {
                String covidDataEmString = CSVWriter.juntarDadosEFormatar(dados.findWithIndex(i));
                writer.write(covidDataEmString);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException error) {
            System.err.println("Erro ao escrever linha.");
        }
    }

    private static String juntarDadosEFormatar(CovidData data) {
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
