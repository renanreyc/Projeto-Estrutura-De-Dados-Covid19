package dataBase;

public class CovidBase {

    private String yearWeek;
    private String date;
    private String state;
    private String orderForPlace;
    private String city;
    private String ibgeCode;
    private String placeType;
    private int availableConfirmed;
    private double availableConfirmedPer100K;
    private int newConfirmed;
    private int availableDeaths;
    private int newDeaths;
    private double lastAvailableDeathRate;
    private int estimatedPopulation;
    private boolean isLast;
    private boolean isRepeated;

    private final int yearWeekId = 0;
    private final int dateId = 1;
    private final int orderForPlaceId = 2;
    private final int stateId = 3;
    private final int cityId = 4;
    private final int ibgeCodeId = 5;
    private final int placeTypeId = 6;
    private final int availableConfirmedId = 7;
    private final int availableConfirmedPer100KId = 8;
    private final int newConfirmedId = 9;
    private final int availableDeathsId = 10;
    private final int newDeathsId = 11;
    private final int lastAvailableDeathRateId = 12;
    private final int estimatedPopulationId = 13;
    private final int isLastId = 14;
    private final int isRepeatedId = 15;

    public CovidBase() {
    }
    public CovidBase(String CSVLine){
        this.setData(CSVLine);
    }

    private void setData(String CSVLine) {
        String[] columns = separarColunas(CSVLine);

        this.yearWeek = columns[yearWeekId];
        this.date = columns[dateId];
        this.orderForPlace = columns[orderForPlaceId];
        this.state = columns[stateId];
        this.city = columns[cityId];
        this.ibgeCode = columns[ibgeCodeId];
        this.placeType = columns[placeTypeId];
        this.availableConfirmed = convertToInt(columns[availableConfirmedId]);
        this.availableConfirmedPer100K = convertToDouble(columns[availableConfirmedPer100KId]);
        this.newConfirmed = convertToInt(columns[newConfirmedId]);
        this.availableDeaths = convertToInt(columns[availableDeaths]);
        this.newDeaths = convertToInt(columns[newDeathsId]);
        this.lastAvailableDeathRate = convertToDouble(columns[lastAvailableDeathRateId]);
        this.estimatedPopulation = convertToInt(columns[estimatedPopulationId]);
    }

    private int convertToInt(String text){
        if (text == ""){
            return  0;
        }else {
            return Integer.parseInt(text);
        }
    }

    private double convertToDouble(String text){
        if (text == ""){
            return  0;
        }else {
            return Double.parseDouble(text);
        }
    }

    private String[] separarColunas(String CSVLine){
        return CSVLine.split(",");
    }

    @Override
    public String toString() {
        return "CovidBase{" +
                "" +
                "}";
    }
}
