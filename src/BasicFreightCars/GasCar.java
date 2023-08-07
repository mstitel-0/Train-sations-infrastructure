package BasicFreightCars;

import Exceptions.FreightCarCapacityException;

public class GasCar extends BasicFreightCar {
    private int weightOfGasBarrel = 200;
    private int amountOfGas;
    private boolean isLeaked;
    private String[] gasMaterial = {"Nitrogen" , "Helium" , "Xenon" , "Argon"};

    public GasCar() {
        genAmountOfGas();
        genMaterial();
        isGasLeaked();
        genCompWeight();
    }
    public String genMaterial(){
        setNameOfMaterial(gasMaterial[(int)(Math.random()*4)]);
        return getNameOfMaterial();
    }
    public boolean isGasLeaked(){
        double x = Math.random();
        if (x<0.01){
            return isLeaked = true;
        }
        else return isLeaked = false;
    }
    public int genAmountOfGas(){
        return amountOfGas= (int)(Math.random()*getVolume());
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight() +weightOfGasBarrel + amountOfGas);
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "GasCar[" + "Contains: " + amountOfGas + " m3 of " + getNameOfMaterial() + ", volume of the barrel: " + getVolume() + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "GasCar{" +
                "weightOfGasBarrel=" + weightOfGasBarrel +
                ", amountOfGas=" + amountOfGas +
                ", loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", nameOfMaterial=" + getNameOfMaterial() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
