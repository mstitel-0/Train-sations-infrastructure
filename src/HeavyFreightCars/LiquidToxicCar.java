package HeavyFreightCars;

import Interfaces.LiquidCarInterface;
public class LiquidToxicCar extends ToxicCar implements LiquidCarInterface {
    private int weightOfBarrel = 200;

    public LiquidToxicCar(){
        super();
        genVolumeOfMaterial();
    }


    @Override
    public int genCompWeight() {
        setCompWeight(getWeight() + weightOfBarrel + getAmountOfToxicMaterial());
        return getCompWeight();
    }

    @Override
    public int genVolumeOfMaterial() {
        setAmountOfToxicMaterial((int)(Math.random()*getVolume()));
        return getAmountOfToxicMaterial();
    }

    @Override
    public void putAnotherMaterial(String nameOfMaterial) {
        setNameOfMaterial(nameOfMaterial);
    }

    @Override
    public String toString() {
        return "LiquidToxicCar[ " + "Contains: "+ getAmountOfToxicMaterial() + " liters of " + getNameOfMaterial() + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
        ']';
    }

    public String fullInformation() {
        return "LiquidToxicCar{" +
                "weightOfBarrel=" + weightOfBarrel +
                ", isToxic=" + isToxic() +
                ", amountOfToxicMaterial=" + getAmountOfToxicMaterial() +
                ", loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", nameOfMaterial='" + getNameOfMaterial() + '\'' +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
