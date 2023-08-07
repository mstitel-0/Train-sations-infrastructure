package RailRoadCars;
import Interfaces.Electricity;
import Interfaces.GenerateNum;

public class Locomotive implements GenerateNum , Electricity, Runnable {
    private int speed = 135;
    private String number;
    private String homeRailwayStation;
    private String destinationRailwayStation;
    private int maximumNumOfRailRoadCars;
    private int maximumNumOfRailRoadCarsInGrid;
    private int weightOfTransportedLoad;
    private boolean isConnectedToTheGrid;
    private static int countLocomotive = 0;
    private String source;
    public Locomotive() {
        genMaxAmountOfRailRoadCars();
        genMaximumNumOfRailRoadCarsInGrid();
        genMaxWeightOfTransportedLoad();
        this.number = generateNum();
        setConnection();
        countLocomotive++;
    }
    public int genMaxAmountOfRailRoadCars(){
        return maximumNumOfRailRoadCars = (int)((Math.random()*6)+5);
    }
    public int genMaximumNumOfRailRoadCarsInGrid(){
        return this.maximumNumOfRailRoadCarsInGrid = (int)((Math.random()*11)+10);
    }
    public int genMaxWeightOfTransportedLoad(){
        return this.weightOfTransportedLoad = (int)((Math.random()*60000)+90000);
    }

    public int getMaximumNumOfRailRoadCars() {
        return maximumNumOfRailRoadCars;
    }

    public int getMaximumNumOfRailRoadCarsInGrid() {
        return maximumNumOfRailRoadCarsInGrid;
    }

    public int getWeightOfTransportedLoad() {
        return weightOfTransportedLoad;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHomeRailwayStation(String homeRailwayStation) {
        this.homeRailwayStation = homeRailwayStation;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setDestinationRailwayStation(String destinationRailwayStation) {
        this.destinationRailwayStation = destinationRailwayStation;
    }

    public static Locomotive genLocomotive(){
        return new Locomotive();
    }
    @Override
    public boolean setConnection() {
        return this.isConnectedToTheGrid = true;
    }

    @Override
    public String toString() {
        return "Locomotive[Home station is : " + homeRailwayStation + ", source is : " + source + " and destination station is : " + destinationRailwayStation + ",maximum weight locomotive can transport is : " + weightOfTransportedLoad + ", number : " + number + "]";
    }

    public String fullInformation() {
        return "Locomotive -" + countLocomotive + "{" +
                " homeRailwayStation='" + homeRailwayStation + '\'' +
                ", destinationRailwayStation='" + destinationRailwayStation + '\'' +
                ", maximumNumOfRailRoadCars=" + maximumNumOfRailRoadCars +
                ", maximumNumOfRailRoadCarsInGrid=" + maximumNumOfRailRoadCarsInGrid +
                ", weightOfTransportedLoad=" + weightOfTransportedLoad +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public void run() {
            while (true) {
                    double move = Math.random();
                    if (move <= 0.5) {
                        this.speed = (int) (this.speed + (0.03 * this.speed));
                    } else if (this.speed < 80) this.speed = 150;
                    else {
                        this.speed = (int) (this.speed - (0.03 * this.speed));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
            }
    }
}
