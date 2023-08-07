package BasicFreightCars;
import HeavyFreightCars.HeavyFreightCar;
import RailRoadCars.RailRoadCar;
import Exceptions.FreightCarCapacityException;
import Interfaces.ConstructorOfFreightCars;

public class BasicFreightCar extends RailRoadCar implements ConstructorOfFreightCars {
    private int loadCapacity;
    private int volume;
    private String nameOfMaterial;

    public BasicFreightCar() {
            genLoadCapacity();
            genVolumeOfTheCar();
            //isOverflow();
    }

     public void isOverflow()throws FreightCarCapacityException{
        if (loadCapacity < 15000 && this instanceof HeavyFreightCar) {
            throw new FreightCarCapacityException("Too low for heavy car");
        } else if (loadCapacity > 15000 && this instanceof BasicFreightCar && !(this instanceof HeavyFreightCar)) {
            throw new FreightCarCapacityException("Too much for basi freight car");
        }
    }

    public int genLoadCapacity() {
        return this.loadCapacity = (int)((Math.random()*14001)+1000);
    }
    public int genVolumeOfTheCar(){
        return this.volume = (int)((Math.random()*671)+40);
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public String getNameOfMaterial() {
        return nameOfMaterial;
    }

    public int getVolume() {
        return volume;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setNameOfMaterial(String nameOfMaterial) {
        this.nameOfMaterial = nameOfMaterial;
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight());
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "BasicFreightCar[Load capacity is :" + loadCapacity + ", volume is: " + volume + ",weight is: " + getCompWeight() + ", number : " + getNumber()  + "]";
    }

    public String fullInformation() {
        return "BasicFreightCar{" +
                "loadCapacity=" + loadCapacity +
                ", volume=" + volume +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }


}
