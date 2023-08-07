package HeavyFreightCars;

import BasicFreightCars.BasicFreightCar;
import Exceptions.FreightCarCapacityException;
import Interfaces.ConstructorOfFreightCars;

public class HeavyFreightCar extends BasicFreightCar implements ConstructorOfFreightCars {

    public HeavyFreightCar() {


    }

    @Override
    public int genLoadCapacity() {
        setLoadCapacity((int)((Math.random()*30000)+15000));
        return getLoadCapacity();
    }

    @Override
    public int genVolumeOfTheCar() {
        return super.genVolumeOfTheCar();
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight());
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "HeavyFreightCar[Load capacity is :" + getLoadCapacity() + ", volume is: " + getVolume() + ",weight is: " + getCompWeight() + ", number : " + getNumber()  + "]";
    }

    public String fullInformation() {
        return "HeavyFreightCar{" +
                "loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }

}
