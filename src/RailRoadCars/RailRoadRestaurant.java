package RailRoadCars;

import Interfaces.Electricity;

public class RailRoadRestaurant extends RailRoadCar implements Electricity {
    private int amountOfSeats;
    private int weightOfSeats=5;
    private int amountOfTables;
    private int weightOfTables = 10;
    private int foodWeight;
    private boolean isPremium;
    private int visitorsNow;

    public RailRoadRestaurant() {
        genAmountOfSeats();
        genAmountOfTables();
        genWeightOfFood();
        genCompWeight();
        genVisitors();
        setConnection();
        isPremiumRestaurant();
    }
    public void genWeightOfFood(){
        foodWeight = (int)((Math.random()*500)+200);
    }

    public int genAmountOfSeats(){
        this.amountOfSeats = (int)(Math.random()*101);
        return this.amountOfSeats ;
    }
    public int genVisitors(){
        return this.visitorsNow = (int)(Math.random()*amountOfSeats);
    }
    public int genAmountOfTables(){
        return this.amountOfTables = this.amountOfSeats/2;
    }
    public boolean isPremiumRestaurant(){
          double x = Math.random();
          if (x <= 0.5){
              return this.isPremium = true;
          }
          else return this.isPremium = false;
    }
    @Override
    public boolean setConnection() {
        setConnectedToTheGrid(true);
        return getIsConnectedToTheGrid();
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight()+((amountOfTables*weightOfTables)+(amountOfSeats*weightOfSeats)+foodWeight));
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "RailRoadRestaurant[" + "Contains: "+ amountOfSeats + " seats and " + amountOfTables + " tables, there are: " + visitorsNow + " visitors," + foodWeight +" kg of food, weight is: " + getCompWeight() + ",is it premium restaurant - " + isPremium + " .Connected : " + getIsConnectedToTheGrid() +  ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "RailRoadRestaurant{" +
                "amountOfSeats=" + amountOfSeats +
                ", weightOfSeats=" + weightOfSeats +
                ", amountOfTables=" + amountOfTables +
                ", weightOfTables=" + weightOfTables +
                ", foodWeight=" + foodWeight +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber() + '\'' +
                ", isConnected='" + getIsConnectedToTheGrid() + '\'' +
                '}';
    }

}
