package BasicFreightCars;

import Exceptions.FreightCarCapacityException;
import Interfaces.LiquidCarInterface;

import java.util.Arrays;

public class LiquidCar extends BasicFreightCar implements LiquidCarInterface {
    private int weightOfBarrel=200;
    private int volumeOfMaterial;
    private String[] liquidMaterial = {"Water", "Oil" , "Motor oil" , "Antifreeze"};

    public LiquidCar() {
        genVolumeOfMaterial();
        genMaterial();
        genCompWeight();
    }
    public String genMaterial(){
        setNameOfMaterial(liquidMaterial[(int)(Math.random()*4)]);
        return getNameOfMaterial();
    }
    public void putAnotherMaterial(String nameOfMaterial){
        setNameOfMaterial(nameOfMaterial);
    }
    public int genVolumeOfMaterial(){
        return this.volumeOfMaterial = (int)(Math.random()*getVolume());
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight() + weightOfBarrel + volumeOfMaterial);
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "LiquidCar[ " + "Contains: " + volumeOfMaterial + " liters of " + getNameOfMaterial() + ", volume of the barrel: " + getVolume() + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "LiquidCar{" +
                "weightOfBarrel=" + weightOfBarrel +
                ", volumeOfMaterial=" + volumeOfMaterial +
                ", loadCapacity=" + getLoadCapacity() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", nameOfMaterial=" + getNameOfMaterial() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
