package Interfaces;
import Exceptions.CarIsInAnotherSet;
import RailRoadCars.RailRoadCar;
import java.util.ArrayList;
public interface CheckForUniqueCar {
    ArrayList<RailRoadCar> uniqueCars = new ArrayList<>();
    default void checkForUniqueCar(RailRoadCar car) throws CarIsInAnotherSet {
       if (uniqueCars.contains(car)){
           throw new CarIsInAnotherSet(car + " is already in this/another set");
       }
       uniqueCars.add(car);
    }
}
