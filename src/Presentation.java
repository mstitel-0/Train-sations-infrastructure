import BasicFreightCars.BasicFreightCar;
import BasicFreightCars.GasCar;
import BasicFreightCars.LiquidCar;
import BasicFreightCars.RefrigeratedRailRoadCar;
import Exceptions.*;
import HeavyFreightCars.ExplosiveCar;
import HeavyFreightCars.HeavyFreightCar;
import HeavyFreightCars.LiquidToxicCar;
import HeavyFreightCars.ToxicCar;
import RailRoadCars.*;
import Stations.Route;
import Stations.Station;
public class Presentation extends Main{
    public static void Presentation(String [] args) throws TooManyCarsInTrain, TooMuchWeight, CarIsInAnotherSet, TooManyCarsInGrid, CarIsNotInThisTrainSet {
        RailRoadCar r1 = new RailRoadCar();
        RailRoadRestaurant r2 = new RailRoadRestaurant();
        RailRoadPostOffice r3 = new RailRoadPostOffice();
        RailRoadBaggageMail r4 = new RailRoadBaggageMail();
        PassengerRailRoadCar r5 = new PassengerRailRoadCar();
        Locomotive l1 = new Locomotive();
        ToxicCar r6 = new ToxicCar();
        LiquidToxicCar r7 = new LiquidToxicCar();
        HeavyFreightCar r8 = new HeavyFreightCar();
        ExplosiveCar r9 = new ExplosiveCar();
        BasicFreightCar r10 = new BasicFreightCar();
        GasCar r11 = new GasCar();
        LiquidCar r12 = new LiquidCar();
        RefrigeratedRailRoadCar r13 = new RefrigeratedRailRoadCar();
        r1.getCompWeight();//you can use this method on every object of RailRoadCar,it returns цушпре brutto weight(with everything)
        r1.fullInformation();//you can use this method to call up the full information for each railroad car
        r1.getNumber();//you can use this method to show the unique number of each railroad car and locomotive
        r1.genCompWeight();//you can use this method in constructor for example to calculate brutto weight of the car, you can use on each railroadcar
        r1.isConnectedToTheGrid();//connects railroad car to the locomotive's grid
        r2.genAmountOfTables();//generates amount of tables
        r2.genAmountOfSeats();// generates amount of seats
        r2.isPremiumRestaurant();//randomly sets premium of the restaurant
        r2.setConnection();//sets connection for each railroad car
        r2.genVisitors();//generates amount of visitors in the restaurant
        r3.workersAmount();//generates amount of workers in the post office
        r3.genFinalWeightOFMail();//count weight of the mail
        r3.genAmountOfMail();//generates amount iof mail
        r4.genAmountOfBaggage();//amount of baggage it transports
        r4.genFinalWeightOfBaggage();//weight of the baggage
        r4.isMess();//10% for car to be messy
        r5.addPeople(0);// add amount of people to the car
        r5.genAmountOfPeople();//generates amount of people
        r5.genAmountOfSeats();//generates amount of seats
        l1.genMaxAmountOfRailRoadCars();//generates amount of cars which could be connected to the locomotive
        l1.genMaximumNumOfRailRoadCarsInGrid();//generates amount of cars which could be connected to the locomotive's grid
        l1.genMaxWeightOfTransportedLoad();//generates amount of maximum weight locomotive can transport
        r6.chanceToGetPoisoned();//generates is there is a possibility to get poisoned
        r6.genMaterial();//generates name of toxic material
        r7.genMaterial();//generates name of liquid toxic material
        //r8,r10 freaights cars, have the same methods as railroad car , but in constructor you can't create a basic freight car with weight more than 15000, and heavy with less than 15000 load capacity
        r9.genChanceOfExplosion();//calculates chance of explosion
        r9.carIsDamaged();//checks if it's damaged due to explosion
        r9.genAmountOfExplosiveMaterial();//generates amount of explosive material
        r9.genMaterial();// generates explosive material
        r11.isGasLeaked();// checks if gas is leaking
        r11.genMaterial();//generates gas material
        r12.genMaterial();//generates liquid material
        r12.genVolumeOfMaterial();//generates volume of material
        r13.genAmountOfMaterial();//generates amount of material
        r13.genMaterial();//generates material which is stored in refrigerator car
        //Exceptions
        //CarIsInAnotherSet - checks for cars int other trainsets
        //CarIsNotInThisTrainSet - checks for this car in this train set
        //FreightCarCapacityException = doesn't allow to create basi bfreaight car with load capacity more than 15000,and heave with less than 15000
        //RailRoadHazard - occurs when locomotive's speed is 200km/h or higher
        //TooManyCarsInGrid - occurs when user is trying to add to many cars to this locomotive
        //TooManyCarsInGrid - occurs when use is trying to add too many cars which must be connected to locomotive's grid
        //TooMuchWeight - occurs when weight of the connected cars are bigger than locomotive can trasport
        TrainSet t1 = new TrainSet(l1,r2,r4,r5);//creates a train by hand
        TrainSet t2 = new TrainSet();//creates automatically
        t2.addToTrainSet(r4);// adds a railroad car to existing trainset
        t2.deleteRailRoadCar(6);//deletes car from existing train by order number
        t2.reNew();//when route is assigned to train set it refreshes fields of home,source and destination
        Station s1 = new Station();//creates station and distance
        Station s2 = new Station();
        s1.genHomeStation();//generates home station
        s1.genLastStation();//generates destination station
        s1.genStationName();//generates middles station
        Route ro1 = new Route();//makes connection between stations for train to move
        ro1.addStation(s1);//add connection to the station in the same route
        ro1.sumRouteDistance();//calculates distance of the route
        //Threads and run() methods
        //run() method in locomotive calculates train speed
        //run() method in trainset calculates route of the train
        //Thread msg = new Thread(new MessageThread())
        //MessageThread is a thread which takes LinkedBlockingDeque where contains all the messages which are displayed in menu
        //Thread col = new Thread(new Collision());
        //Collision is a thread which take an array of train,looping for all of them ,find train swith the same stations and displays and index of it ,
        // which is taken later and used for stoping this thread in main to prevent collision
        //Thread save = new Thread(new SaveThread());
        //SaveThread is a thead which takes every 5 seconds an array of trin sets in main and sort it and saves infromation in file AppState.txt
        //Interfaces
        //CheckForUniqueCar - check if the created car is unique
        //ConstructorOfFreightCars - contains common elements of Freight cars for their constructors
        //Electricity - interface which implements connection of railroad cars with locomotive's grid
        //GenCompWeight -interfave with common method in all rai;road cars to count Brutto weight
        //GenerateNum - interface with common method for all cars and locomotive which generates unique numbers
        //LiquidCarInterface - interface with common fields for liquid cars
    }
}


