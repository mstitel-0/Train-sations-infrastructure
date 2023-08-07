import BasicFreightCars.GasCar;
import BasicFreightCars.LiquidCar;
import BasicFreightCars.RefrigeratedRailRoadCar;
import Exceptions.*;
import HeavyFreightCars.ExplosiveCar;
import HeavyFreightCars.LiquidToxicCar;
import HeavyFreightCars.ToxicCar;
import RailRoadCars.*;
import Stations.Route;
import Stations.Station;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws TooMuchWeight, CarIsInAnotherSet, TooManyCarsInGrid, CarIsNotInThisTrainSet {
        ArrayList<TrainSet> trains = new ArrayList<>();
        ArrayList<Thread> locomotiveThreads = new ArrayList<>();
        ArrayList<Thread> trainThreads = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            trains.add(new TrainSet());
            locomotiveThreads.add(new Thread(trains.get(i).getLocomotive()));
            trainThreads.add(new Thread(trains.get(i)));
            locomotiveThreads.get(i).start();
            trainThreads.get(i).start();
        }
        ArrayList<RailRoadCar> createdCars = new ArrayList<>();
        ArrayList<Locomotive> createdLocomotives = new ArrayList<>();
        ArrayList<Station> createdStations = new ArrayList<>();
        ArrayList<Route> createdRoutes = new ArrayList<>();
        ArrayList<TrainSet> createdTrainSet = new ArrayList<>();
        Collision collisionThread = new Collision(trains, trainThreads);
        Thread collision = new Thread(collisionThread);
        Thread appSave = new Thread(new SaveThread(trains));
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        boolean trainMenu = true;
        boolean createMenu = true;
        Thread msgReceiver = null;
        appSave.start();
        collision.start();
        while (start) {
            createMenu = true;
            System.out.println(
                    "1 - Select train to see the route\n" +
                            "2 - Add people\n" +
                            "3 - Add cargo\n" +
                            "4 - Remove train set\n" +
                            "5 - Remove railroad car from train set\n" +
                            "6 - Create menu\n" +
                            "7 - Full information about train set\n" );
            int navigate = scan.nextInt();
            switch (navigate) {
                case 1:
                    while (true) {
                        String nav = scan.nextLine();
                        if (nav.equals("q")) {
                            break;
                        }
                        try {
                            int checkForInt = Integer.parseInt(nav);
                            if (Integer.parseInt(nav) <= trains.size() && Integer.parseInt(nav) >= 1) {
                                msgReceiver = new Thread(new MessageThread(trains.get(Integer.parseInt(nav) - 1).getMsgQueue(),trains.get(Integer.parseInt(nav) - 1)));
                                System.out.println("Train number is :" + trains.get(Integer.parseInt(nav) - 1).getOrderNum());
                                msgReceiver.start();
                                if (msgReceiver != null) {
                                    System.out.println("Press enter to interact with another train");
                                    scan.nextLine();
                                    msgReceiver.interrupt();
                                    msgReceiver = null;
                                    System.out.println("Choose another train or enter q to leave");
                                }
                            } else System.out.println("There is no train with such order number,try again");
                        } catch (NumberFormatException ex) {
                            System.out.println("\n\r Write an order number of train set to interact with it or q to leave");
                        }
                        if (collisionThread.getIndexOfSleep() != -1) {
                            try {
                                trainThreads.get(collisionThread.getIndexOfSleep()).sleep(20000);
                                locomotiveThreads.get(collisionThread.getIndexOfSleep()).sleep(20000);
                            } catch (InterruptedException e) {
                            }
                        }

                    }
                    break;
                case 2:
                    System.out.println("Which train set?");
                    int train = scan.nextInt();
                    if (train > trains.size() - 1) {
                        System.out.println("There is no such train");
                        break;
                    }
                    System.out.println(trains.get(train - 1));
                    System.out.println("In which car?");
                    int car = scan.nextInt();
                    if (car < trains.get(train - 1).getTrainSetRailRoadCars().size() && trains.get(train - 1).getTrainSetRailRoadCars().get(car - 1) instanceof PassengerRailRoadCar) {
                        System.out.println("How many?");
                        int people = scan.nextInt();
                        ((PassengerRailRoadCar) trains.get(train - 1).getTrainSetRailRoadCars().get(car - 1)).addPeople(people);
                        System.out.println("People added");
                        break;
                    } else {
                        System.out.println("It's not a Passenger Rail Road or there is no such car");
                        break;
                    }
                case 3:
                    System.out.println("Which train set?");
                    train = scan.nextInt();
                    if (train > trains.size() - 1) {
                        System.out.println("There is no such train");
                        break;
                    }
                    System.out.println(trains.get(train - 1));
                    System.out.println("In which car?");
                    car = scan.nextInt();
                    if (car < trains.get(train - 1).getTrainSetRailRoadCars().size() && !(trains.get(train - 1).getTrainSetRailRoadCars().get(car - 1) instanceof PassengerRailRoadCar)) {
                        System.out.println("How much?");
                        int cargo = scan.nextInt();
                        ((RailRoadCar) trains.get(train - 1).getTrainSetRailRoadCars().get(car - 1)).addCargo(cargo);
                        System.out.println("Cargo added");
                        break;
                    } else {
                        System.out.println("It's  a Passenger Rail Road or there is no such car");
                        break;
                    }
                case 4:
                    System.out.println("Which train set?");
                    train = scan.nextInt();
                    if (train <= trains.size()) {
                        trains.remove(train - 1);
                        locomotiveThreads.get(train - 1).interrupt();
                        locomotiveThreads.remove(train - 1);
                        trainThreads.get(train - 1).interrupt();
                        trainThreads.remove(train - 1);
                        System.out.println("Train removed");
                        break;
                    } else {
                        System.out.println("There is no such train set");
                        break;
                    }
                case 5:
                    System.out.println("Choose train set to delete from");
                    System.out.println(trains);
                    train = scan.nextInt();
                    if (train > trains.size()) break;
                    System.out.println("Which railroad car?");
                    car = scan.nextInt();
                    if (trains.get(train-1).getTrainSetRailRoadCars().size() < car)
                    trains.get(train-1).getTrainSetRailRoadCars().remove(car-1);
                    trains.get(train-1).setCarsCount(trains.get(train-1).getCarsCount()-1);
                    System.out.println("Success");
                    break;
                case 6:
                    while (createMenu) {
                        System.out.println("1 - Create rail road car\n" + "2 - Create locomotive\n" + "3 - Create station\n" + "4 - Create connection\n" + "5 - Create train set\n" + "6 - Launch train set\n" + "7 - leave");
                        int nav = scan.nextInt();
                        switch (nav) {
                            case 1:
                                System.out.println("Which one?\n" + "1 - passenger \n" + "2 -Bagaggemail \n" + "3 - Postoffice\n" + "4 - Restaurant \n" + "5 - Loxic car\n" + "6 - Liquid toxic car\n" + "7 - Explosive\n" + "8 - Liquid\n" + "9 - Gas\n" + "10 - Refrigerated\n");
                                car = scan.nextInt();
                                switch (car) {
                                    case 1:
                                        createdCars.add(new PassengerRailRoadCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 2:
                                        createdCars.add(new RailRoadBaggageMail());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 3:
                                        createdCars.add(new RailRoadPostOffice());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 4:
                                        createdCars.add(new RailRoadRestaurant());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 5:
                                        createdCars.add(new ToxicCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 6:
                                        createdCars.add(new LiquidToxicCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 7:
                                        createdCars.add(new ExplosiveCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 8:
                                        createdCars.add(new LiquidCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 9:
                                        createdCars.add(new GasCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    case 10:
                                        createdCars.add(new RefrigeratedRailRoadCar());
                                        System.out.println("Success\n" + createdCars);
                                        break;
                                    default:
                                        System.out.println("Not an option");
                                        break;
                                }
                                break;
                            case 2:
                                createdLocomotives.add(new Locomotive());
                                System.out.println("Success\n" + createdLocomotives);
                                break;
                            case 3:
                                createdStations.add(new Station());
                                System.out.println("Success\n" + createdStations);
                                break;
                            case 4:
                                createdRoutes.add(new Route());
                                System.out.println("Success\n" + createdRoutes);
                                break;
                            case 5:
                                System.out.println("1 - Random train set\n" + "2 - Custom train set");
                                int r = scan.nextInt();
                                switch (r) {
                                    case 1:
                                    createdTrainSet.add(new TrainSet());
                                    System.out.println("Success\n" + createdTrainSet);
                                    break;
                                    case 2:
                                        System.out.println("Choose locomotive");
                                        System.out.println(createdLocomotives);
                                        int loc = scan.nextInt();
                                        System.out.println("Choose rail road");
                                        System.out.println(createdCars);
                                        car = scan.nextInt();
                                        try {
                                            createdTrainSet.add(new TrainSet(createdLocomotives.get(loc-1),createdCars.get(car-1)));
                                            createdLocomotives.remove(loc-1);
                                            createdCars.remove(car-1);
                                        } catch (TooManyCarsInTrain e) {

                                        }
                                        while (true) {
                                            System.out.println("More cars?\n" + "1 - yes\n" + "2 - no");
                                            int more = scan.nextInt();
                                            if (more == 2) {
                                                break;
                                            }
                                            else {
                                                System.out.println("Choose rail road");
                                                System.out.println(createdCars);
                                                car = scan.nextInt();
                                                try {
                                                    createdTrainSet.get(createdTrainSet.size() - 1).addToTrainSet(createdCars.get(car-1));
                                                    createdCars.remove(car-1);
                                                } catch (TooManyCarsInTrain e) {
                                                    System.out.println("Too many cars in 1 trainset");
                                                }
                                            }
                                        }
                                            System.out.println("Choose stations ");
                                            System.out.println(createdStations);
                                            int stat = scan.nextInt();
                                            createdRoutes.add(new Route(createdStations.get(stat-1)));
                                            createdTrainSet.get(createdTrainSet.size()-1).setRoute(createdRoutes.get(createdRoutes.size()-1));
                                            createdStations.remove(stat-1);
                                            while (true){
                                                System.out.println("Add more?\n" + "1 - yes" + "2 - no");
                                                int more = scan.nextInt();
                                                if (more == 2) {
                                                    break;
                                                }
                                                else {
                                                    System.out.println("Choose station");
                                                    System.out.println(createdStations);
                                                    stat = scan.nextInt();
                                                    createdRoutes.get(createdRoutes.size() - 1).addStation(createdStations.get(stat - 1));
                                                    createdTrainSet.get(createdTrainSet.size()-1).getRoute().addStation(createdStations.get(stat-1));
                                                    createdStations.remove(stat-1);
                                                }
                                            }
                                            createdTrainSet.get(createdTrainSet.size()-1).setRoute(createdRoutes.get(createdRoutes.size()-1));
                                            createdTrainSet.get(createdTrainSet.size()-1).reNew();
                                        System.out.println("Success");
                                        System.out.println(createdTrainSet);
                                        break;
                                    default: break;
                                }
                                break;
                            case 6:
                                System.out.println("Which train set you want to launch?\n" + createdTrainSet);
                                train = scan.nextInt();
                                if (train != 1 && train<createdTrainSet.size() || createdTrainSet.size() == 0){
                                    System.out.println("There is no such train set");
                                    break;
                                }
                                trains.add(createdTrainSet.get(train-1));
                                locomotiveThreads.add(new Thread(createdTrainSet.get(train-1).getLocomotive()));
                                trainThreads.add(new Thread(createdTrainSet.get(train-1)));
                                locomotiveThreads.get(locomotiveThreads.size()-1).start();
                                trainThreads.get(trainThreads.size()-1).start();
                                System.out.println("Train successfully launched");
                                break;
                            default: break;
                        }
                       break;
                    }
                    break;
                case 7 :
                    System.out.println("Choose train set to see full information");
                    System.out.println(trains);
                    train = scan.nextInt();
                    if (train > trains.size()) break;
                    System.out.println(trains.get(train-1).toSort());
                    break;
                default: break;
            }
        }
    }
}


