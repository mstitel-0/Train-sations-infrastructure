package RailRoadCars;
import BasicFreightCars.GasCar;
import BasicFreightCars.LiquidCar;
import BasicFreightCars.RefrigeratedRailRoadCar;
import HeavyFreightCars.ExplosiveCar;
import HeavyFreightCars.LiquidToxicCar;
import HeavyFreightCars.ToxicCar;
import Interfaces.GenCompWeight;
import Interfaces.GenerateNum;

public class RailRoadCar implements GenerateNum, GenCompWeight  {
    private int weight = 4000;
    private int compWeight;
    private String number;
    private boolean isConnectedToTheGrid;
    public RailRoadCar() {
        this.number = generateNum();
        genCompWeight();
    }

    public void setConnectedToTheGrid(boolean connectedToTheGrid) {
        isConnectedToTheGrid = connectedToTheGrid;
    }

    public int getWeight() {
        return weight;
    }

    public String getNumber() {
        return number;
    }

    public boolean isConnectedToTheGrid() {
        return isConnectedToTheGrid;
    }

    public int getCompWeight() {
        return compWeight;
    }

    public void setCompWeight(int compWeight) {
        this.compWeight = compWeight;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public boolean getIsConnectedToTheGrid() {
        return isConnectedToTheGrid;
    }
    public void addCargo(int x){
        this.compWeight += x;
    }

    @Override
    public int genCompWeight() {
        return compWeight = weight;
    }

    public static RailRoadCar genRailRoads(){
        int x =(int)(Math.random()*10)+1;
        return switch (x) {
            case 1 -> new PassengerRailRoadCar();
            case 2 -> new RailRoadBaggageMail();
            case 3 -> new RailRoadPostOffice();
            case 4 -> new RailRoadRestaurant();
            case 5 -> new ToxicCar();
            case 6 -> new LiquidToxicCar();
            case 7 -> new ExplosiveCar();
            case 8 -> new LiquidCar();
            case 9 -> new GasCar();
            case 10 -> new RefrigeratedRailRoadCar();
            default -> null;
        };
    }
    @Override
    public String toString() {
        return "RailRoadCar[" + "weight is: " + compWeight + ", number : " + number + "]";
    }

    public String fullInformation() {
        return "RailRoadCar{" +
                "weight=" + weight +
                ", compWeight=" + compWeight +
                ", number='" + number + '\'' +
                '}';
    }

}
