package RailRoadCars;

import Interfaces.Electricity;

public class RailRoadPostOffice extends RailRoadCar implements Electricity {
    private int mailWeight = 1;
    private int amountOfMail;
    private int finalMailWeight;
    private int workers;

    public RailRoadPostOffice() {
        genAmountOfMail();
        genCompWeight();
        setConnection();
        genFinalWeightOFMail();
        workersAmount();
    }
    public int genAmountOfMail(){
        return this.amountOfMail = (int)(Math.random()*100);
    }

    public int getMailWeight() {
        return mailWeight;
    }

    public int getFinalMailWeight() {
        return finalMailWeight;
    }
    public int workersAmount(){
        return this.workers = (int)(Math.random()*20)+4;
    }

    public int genFinalWeightOFMail(){
        return this.finalMailWeight = mailWeight * amountOfMail;
    }

    public int getAmountOfMail() {
        return amountOfMail;
    }

    @Override
    public boolean setConnection() {
        setConnectedToTheGrid(true);
        return getIsConnectedToTheGrid();
    }


    @Override
    public int genCompWeight() {
        setCompWeight(getWeight()+(amountOfMail *mailWeight));
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "RailRoadPostOffice[" + "Contains: "+ finalMailWeight + "kg of mail" + ", weight is: " + getCompWeight() + ",there are: " + workers + " workers" + " Connected : " + getIsConnectedToTheGrid() +  ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "RailRoadPostOffice{" +
                " amountOfMail=" + amountOfMail +
                ", mailWeight=" + mailWeight +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                ", isConnected='" + getIsConnectedToTheGrid() + '\'' +
                '}';
    }

}
