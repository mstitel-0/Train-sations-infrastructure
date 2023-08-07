package RailRoadCars;
import Exceptions.*;
import Interfaces.CheckForUniqueCar;
import Stations.Route;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class TrainSet implements CheckForUniqueCar ,Runnable  {
    private Locomotive locomotive;
    private RailRoadCar railRoad;
    private ArrayList<RailRoadCar> trainSetRailRoadCars;
    private int carsCount = 1;
    private int gridCount = 1;
    private  int weightCount = 0;

    private Route route;
    private boolean isRunning = true;
    private LinkedBlockingDeque<String> msgQueue;
    private int orderNum;
    private static int count = 0;
    private  int currentStation = 1;
    private String nextStation;
    private String sCurrentStation;
    boolean msgSleep = false;

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public TrainSet(Locomotive locomotive, RailRoadCar... railRoadCars) throws TooManyCarsInTrain, TooMuchWeight, TooManyCarsInGrid, CarIsInAnotherSet {
        this.locomotive = locomotive;
        this.msgQueue = new LinkedBlockingDeque<>();
        count++;
        this.orderNum = count;
        int weightMax = this.locomotive.getWeightOfTransportedLoad();
        this.trainSetRailRoadCars = new ArrayList<>();
        if (locomotive.getMaximumNumOfRailRoadCars() > railRoadCars.length){
            for(RailRoadCar car : railRoadCars){
                if (locomotive.getMaximumNumOfRailRoadCarsInGrid() > gridCount) {
                    if (weightMax > weightCount + car.getCompWeight() ) {
                        checkForUniqueCar(car);
                        this.trainSetRailRoadCars.add(car);
                        weightCount += car.getCompWeight();
                        carsCount++;
                        if (car.getIsConnectedToTheGrid()) {
                            gridCount++;
                        }
                    } else throw new TooMuchWeight(car + " weights more,than this locomotive can run");
                } else throw new TooManyCarsInGrid("Exceeded maximum amount of connected in Locomotive Grid");
            }
        }
        else throw new TooManyCarsInTrain("Exceeded maximum amount of connected RailRoadCars to this Locomotive");
    }
    public void addToTrainSet(RailRoadCar car) throws TooMuchWeight, TooManyCarsInGrid, TooManyCarsInTrain, CarIsInAnotherSet {
        int weightMax = this.locomotive.getWeightOfTransportedLoad();
        if (this.locomotive.getMaximumNumOfRailRoadCars() > this.trainSetRailRoadCars.size()){
                if (this.locomotive.getMaximumNumOfRailRoadCarsInGrid() > gridCount) {
                    if (weightMax > weightCount + car.getCompWeight() ) {
                        checkForUniqueCar(car);
                        this.trainSetRailRoadCars.add(car);
                        weightCount += car.getCompWeight();
                        carsCount++;
                        if (car.getIsConnectedToTheGrid()) {
                            gridCount++;
                        }
                    } else throw new TooMuchWeight(car + " weights more,than this locomotive can run");
                } else throw new TooManyCarsInGrid("Exceeded maximum amount of connected in Locomotive Grid");
        }
        else throw new TooManyCarsInTrain("Exceeded maximum amount of connected RailRoadCars to this Locomotive");
    }
    public TrainSet() throws TooMuchWeight, TooManyCarsInGrid, CarIsInAnotherSet {
        this.locomotive = Locomotive.genLocomotive();
        this.route = Route.genRoute();
        this.msgQueue = new LinkedBlockingDeque<>();
        count++;
        this.orderNum = count;
        this.locomotive.setHomeRailwayStation(this.route.getHomeStation());
        this.locomotive.setDestinationRailwayStation(this.route.getLastStation());
        this.locomotive.setSource(this.route.getRoute().get(currentStation).getStationName());
        int weightMax = this.locomotive.getWeightOfTransportedLoad();
        this.trainSetRailRoadCars = new ArrayList<>();
        for (int i = 0; i < this.locomotive.getMaximumNumOfRailRoadCars(); i++) {
            railRoad = RailRoadCar.genRailRoads();
            if (locomotive.getMaximumNumOfRailRoadCarsInGrid() > gridCount) {
                if (weightMax > weightCount + railRoad.getCompWeight() ) {
                    checkForUniqueCar(railRoad);
                    this.trainSetRailRoadCars.add(railRoad);
                    weightCount += railRoad.getCompWeight();
                    carsCount++;
                    if (railRoad.getIsConnectedToTheGrid()) {
                        gridCount++;
                    }
                } else throw new TooMuchWeight(railRoad + " weights more,than this locomotive can run");
            } else throw new TooManyCarsInGrid("Exceeded maximum amount of connected in Locomotive Grid");
        }
    }

    public void deleteRailRoadCar(int n) throws CarIsNotInThisTrainSet {
        int index = n-1;
        if (!(this.trainSetRailRoadCars.contains(this.trainSetRailRoadCars.get(index)))){ throw new CarIsNotInThisTrainSet("This train set does not contain an RailRoadCar with given index");}
        this.trainSetRailRoadCars.remove(index);
        this.carsCount--;
        System.out.println("RailRoadCar removed\n" + this.trainSetRailRoadCars);

    }
    @Override
    public void run() {

            double distanceTravelledNear = 0;
            double distanceTravelledWhole = 0;
            String lastStation = null;
            String msg;
            while (isRunning) {
                if (this.locomotive.getSpeed() >= 200) {
                    try {
                        throw new RailRoadHazard();
                    } catch (RailRoadHazard e) {
                        double percentTravelledWhole = 0;
                        double percentTravelledNear = 0;
                        percentTravelledWhole = ((distanceTravelledWhole / this.route.getRouteDistance()) * 100);
                        if (currentStation == 0) {
                            percentTravelledNear = ((distanceTravelledNear / this.route.getRoute().get(currentStation).getDistance()) * 100);
                        } else {
                            percentTravelledNear = ((distanceTravelledNear / this.route.getRoute().get(currentStation - 1).getDistance()) * 100);
                        }
                        msg = "+\r" + "Distance travelled from home to the last station in percents :" + percentTravelledWhole + ", distance from nearest stations is:" + percentTravelledNear + "\u001B[0m";
                        //System.out.println(msg);
                        this.msgQueue.offerFirst(msg);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {

                        }
                        this.locomotive.setSpeed(170);
                    }
                }
                if ((this.route.getRoute().get(currentStation).getDistance() - this.locomotive.getSpeed() == 0 || this.route.getRoute().get(currentStation).getDistance() - this.locomotive.getSpeed() < 0) && (currentStation != (this.route.getRoute().size() - 1))) {
                   // this.locomotive.setReached(true);
                        msg = "\u001B[32m" + "\rReached : " + this.route.getRoute().get(currentStation).getStationName() + ",stop for 5 seconds" + "\u001B[0m";
                        //System.out.println(msg);
                        this.msgQueue.offerFirst(msg);
                        currentStation++;
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException ex) {
                        }
                        msg = "\u001B[34m" + "\rNext station is: " + this.route.getRoute().get(currentStation).getStationName() + "\u001B[0m";
                        //System.out.println(msg);
                        this.msgQueue.offerFirst(msg);
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException ex) {
                        }
                        distanceTravelledNear = 0;

                        //this.locomotive.setReached(false);
                }
                if ((this.route.getRoute().get(currentStation).getDistance() - this.locomotive.getSpeed() == 0 || this.route.getRoute().get(currentStation).getDistance() - this.locomotive.getSpeed() < 0) && (currentStation == (this.route.getRoute().size() - 1))) {
                    //this.locomotive.setReached(true);
                        lastStation = this.route.getRoute().get(currentStation).getStationName();
                        this.route = Route.genRoute();
                        this.locomotive.setHomeRailwayStation(this.route.getHomeStation());
                        this.locomotive.setDestinationRailwayStation(this.route.getLastStation());
                        distanceTravelledWhole = 0;
                        msg = "\u001B[32m" + "\rTrain has reached destination point, rout is going to be renewed" + "\n\r\u001B[0m" + "\u001B[32m" +"\rNew route is: " + this.route + "\u001B[0m";
                        this.msgQueue.offerFirst(msg);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                    msgSleep = true;
                    try {
                        Thread.sleep(28000);
                    } catch (InterruptedException ex) {
                    }
                        msgSleep = false;
                    currentStation = 0;
                        msg = "\u001B[34m" + "\rNext station is: " + this.route.getRoute().get(currentStation).getStationName() + "\u001B[0m";
                       // System.out.println(msg);
                        this.msgQueue.offerFirst(msg);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        //this.locomotive.setReached(false);
                }
                this.route.getRoute().get(currentStation).setDistance(this.route.getRoute().get(currentStation).getDistance() - this.locomotive.getSpeed());
                msg = "\r" + "\u001B[31m" + "Locomotive is moving " + this.locomotive.getSpeed() + "km/h" + ",route " + (currentStation == 0 ? lastStation : this.route.getRoute().get(currentStation - 1).getStationName()) + " - " + this.route.getRoute().get(currentStation).getStationName() + " in " + this.route.getRoute().get(currentStation).getDistance() + "km" + "\u001B[0m";
                //System.out.println(msg);
                this.msgQueue.offerFirst(msg);
                distanceTravelledNear += this.locomotive.getSpeed();
                distanceTravelledWhole += this.locomotive.getSpeed();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                nextStation = this.route.getRoute().get(currentStation).getStationName();
                if (currentStation == this.route.getRoute().size() - 1) {
                    sCurrentStation = lastStation;
                } else {
                    sCurrentStation = String.valueOf(this.route.getRoute().get(currentStation));
                }
                this.locomotive.setSource(this.route.getRoute().get(currentStation).getStationName());

        }
    }
    public LinkedBlockingDeque<String> getMsgQueue() {
        return msgQueue;
    }
    public int getOrderNum() {
        return orderNum;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;

    }

    public boolean isMsgSleep() {
        return msgSleep;
    }

    public void reNew (){
        this.locomotive.setHomeRailwayStation(this.route.getHomeStation());
        this.locomotive.setSource(this.route.getRoute().get(currentStation).getStationName());
        this.locomotive.setDestinationRailwayStation(this.route.getLastStation());
    }

    public String getNextStation() {
        return nextStation;
    }

    public String getsCurrentStation() {
        return sCurrentStation;
    }

    public ArrayList<RailRoadCar> getTrainSetRailRoadCars() {
        return trainSetRailRoadCars;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }

    public int getCarsCount() {
        return carsCount;
    }

    @Override
    public String toString() {
        return "[Train set #" + orderNum + ", number of cars: " + carsCount + ", distance is" + this.route.getRouteDistance() + "]";
    }

    public String toSort() {
        return  "Train set #" + orderNum + ", distance : " + this.route.getRouteDistance() + ",contains " + carsCount + " railroad cars [" +
                locomotive + ", " +  trainSetRailRoadCars +
                "]";
    }
}
