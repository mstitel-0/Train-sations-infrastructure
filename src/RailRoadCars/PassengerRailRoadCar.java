package RailRoadCars;

import Interfaces.Electricity;

public class PassengerRailRoadCar extends RailRoadCar implements Electricity {
    private   int amountOfSeats;
    private int weightOfSeats = 10;
    private int amountOfPassengers;
    public PassengerRailRoadCar() {
        genAmountOfSeats();
        genCompWeight();
        setConnection();
        genAmountOfPeople();
    }

    @Override
    public boolean setConnection() {
        setConnectedToTheGrid(true);
        return getIsConnectedToTheGrid();
    }


    public int genAmountOfSeats(){
        return this.amountOfSeats = (int)((Math.random()*101)+70);
    }
    public int genAmountOfPeople(){
        return amountOfPassengers = (int)(Math.random()* (amountOfSeats-20));
    }
    public void addPeople(int x){
        if (this.amountOfPassengers + x <= this.amountOfSeats){
            this.amountOfPassengers += x;
        }
        else System.out.println("Too many people");
    }
    @Override
    public int genCompWeight() {
        setCompWeight(getWeight()+(amountOfSeats*weightOfSeats));
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "PassengerRailRoadCar[" + "Contains: "+ amountOfSeats + " seats, " + "Connected : " + getIsConnectedToTheGrid() + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "PassengerRailRoadCar{" +
                " amountOfSeats=" + amountOfSeats +
                " amountOfPassengers=" + amountOfPassengers +
                ", weightOfSeats=" + weightOfSeats +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                ", isConnected='" + getIsConnectedToTheGrid() + '\'' +
                '}';
    }
}
