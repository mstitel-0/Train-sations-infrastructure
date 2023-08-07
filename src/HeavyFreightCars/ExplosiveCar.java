package HeavyFreightCars;

import Exceptions.FreightCarCapacityException;

import java.util.Arrays;

public class ExplosiveCar extends HeavyFreightCar {
    private boolean isExplosive = true;
    private int amountOfExplosiveMaterial;
    private double chanceOfExplosion;
    private boolean isDamaged;
    private String[] explosiveMaterial = {"TNT", "Dynamite" , "C-4" , "PETN" , "RDX" , "Smokeless powder"};

    public ExplosiveCar() {
        genAmountOfExplosiveMaterial();
        genMaterial();
        genChanceOfExplosion();
        carIsDamaged();
        genCompWeight();
    }
    public String genMaterial(){
        setNameOfMaterial( explosiveMaterial[(int)(Math.random()*6)]);
        return getNameOfMaterial();
    }
    public int genAmountOfExplosiveMaterial(){
        return amountOfExplosiveMaterial = (int)(Math.random()*getVolume());
    }
public double genChanceOfExplosion(){
        return this.chanceOfExplosion = Math.random();
}
public boolean carIsDamaged(){
        double x = Math.random()*101;
        if (x<=this.chanceOfExplosion){
            return  isDamaged = true;
        }
        else return isDamaged = false;
}
    @Override
    public int genCompWeight() {
        setCompWeight(getWeight() + amountOfExplosiveMaterial);
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "ExplosiveCar[ " + "Contains: " + amountOfExplosiveMaterial + "kg of " + getNameOfMaterial() + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "ExplosiveCar{" +
                "isExplosive=" + isExplosive +
                ", amountOfExplosiveMaterial=" + amountOfExplosiveMaterial +
                ", loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", nameOfMaterial=" + getNameOfMaterial() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
