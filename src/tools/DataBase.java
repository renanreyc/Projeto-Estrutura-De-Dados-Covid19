package tools;

public class DataBase {

    private String yearWeek;
    private String date;
    private String orderForPlace;
    private String state;
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
    private String isLast;
    private String isRepeated;

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

    public DataBase() {}
    public DataBase(String CVSLine) {
        this.setAttributos(CVSLine);
    }

    private void setAttributos(String CSVLine) {
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
        this.availableDeaths = convertToInt(columns[availableDeathsId]);
        this.newDeaths = convertToInt(columns[newDeathsId]);
        this.lastAvailableDeathRate = convertToDouble(columns[lastAvailableDeathRateId]);
        this.estimatedPopulation = convertToInt(columns[estimatedPopulationId]);
        this.isLast = columns[isLastId];
        this.isRepeated = columns[isRepeatedId];
//        //valida columns test
//        for (int i = 0; i >= columns.length;i++)
//            System.out.println(columns);
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


    public int getAvailableConfirmed() {
        return availableConfirmed;
    }

    public int getAvailableDeaths() {
        return availableDeaths;
    }

    public String getCity() {
        return city;
    }

    public String getYearWeek() {
        return yearWeek;
    }

    public String getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getOrderForPlace() {
        return orderForPlace;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public String getPlaceType() {
        return placeType;
    }

    public double getAvailableConfirmedPer100K() {
        return availableConfirmedPer100K;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public double getLastAvailableDeathRate() {
        return lastAvailableDeathRate;
    }

    public int getEstimatedPopulation() {
        return estimatedPopulation;
    }

    public String isLast() {
        return isLast;
    }

    public String isRepeated() {
        return isRepeated;
    }

    public void setAvailableDeaths(int availableDeaths) {
        this.availableDeaths = availableDeaths;
    }

    public void setAvailableConfirmed(int availableConfirmed) {
        this.availableConfirmed = availableConfirmed;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "yearWeek='" + yearWeek + '\'' +
                ", date='" + date + '\'' +
                ", orderForPlace='" + orderForPlace + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", ibgeCode='" + ibgeCode + '\'' +
                ", placeType='" + placeType + '\'' +
                ", availableConfirmed=" + availableConfirmed +
                ", availableConfirmedPer100K=" + availableConfirmedPer100K +
                ", newConfirmed=" + newConfirmed +
                ", availableDeaths=" + availableDeaths +
                ", newDeaths=" + newDeaths +
                ", lastAvailableDeathRate=" + lastAvailableDeathRate +
                ", estimatedPopulation=" + estimatedPopulation +
                ", isLast='" + isLast + '\'' +
                ", isRepeated='" + isRepeated + '\'' +
                ", yearWeekId=" + yearWeekId +
                ", dateId=" + dateId +
                ", orderForPlaceId=" + orderForPlaceId +
                ", stateId=" + stateId +
                ", cityId=" + cityId +
                ", ibgeCodeId=" + ibgeCodeId +
                ", placeTypeId=" + placeTypeId +
                ", availableConfirmedId=" + availableConfirmedId +
                ", availableConfirmedPer100KId=" + availableConfirmedPer100KId +
                ", newConfirmedId=" + newConfirmedId +
                ", availableDeathsId=" + availableDeathsId +
                ", newDeathsId=" + newDeathsId +
                ", lastAvailableDeathRateId=" + lastAvailableDeathRateId +
                ", estimatedPopulationId=" + estimatedPopulationId +
                ", isLastId=" + isLastId +
                ", isRepeatedId=" + isRepeatedId +
                '}';
    }
}
