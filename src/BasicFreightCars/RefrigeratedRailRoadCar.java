package BasicFreightCars;
import Exceptions.FreightCarCapacityException;
import Interfaces.Electricity;

import java.util.Arrays;


public class RefrigeratedRailRoadCar extends BasicFreightCar implements Electricity {
    private int temperature = -15;
    private int weightOfRefrigerators = 30;
    private int amountOfRefrigerators;
    private int amountOfMaterial;
    private String[] fridgeMaterial = {"Beef", "Chicken", "Kovbasa" , "Pork" , "Ice" , "Fruits" , "Vegetables"};

    public RefrigeratedRailRoadCar()  {
        genAmountOfRefregirators();
        genMaterial();
        genCompWeight();
        setConnection();
        genAmountOfMaterial();
    }
    public String genMaterial(){
        setNameOfMaterial(fridgeMaterial[(int)(Math.random()*7)]);
        return getNameOfMaterial();
    }
    public int genAmountOfRefregirators(){
        return this.amountOfRefrigerators = (int)((Math.random()*91)+10);
    }
    public int genAmountOfMaterial(){ return this.amountOfMaterial = (int)(Math.random()*750)+200;}
    @Override
    public boolean setConnection() {
        setConnectedToTheGrid(true);
        return getIsConnectedToTheGrid();
    }
    @Override
    public int genCompWeight() {
        setCompWeight(getWeight()+(amountOfRefrigerators*weightOfRefrigerators));
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "RefrigeratedRailRoadCar[ " + "Contains: " + amountOfMaterial + "kg of " + getNameOfMaterial() + ", there are " + amountOfRefrigerators + " refrigerators, temperature is: " + temperature + ", weight is: " + getCompWeight() + ". Connected : " + getIsConnectedToTheGrid() +  ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "RefrigeratedRailRoadCar{" +
                "temperature=" + temperature +
                ", weightOfRefrigerators=" + weightOfRefrigerators +
                ", amountOfRefrigerators=" + amountOfRefrigerators +
                ", loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", nameOfMaterial=" + getNameOfMaterial() +
                ", number='" + getNumber()  + '\'' +
                ", isConnected='" + getIsConnectedToTheGrid() + '\'' +
                '}';
    }
}
