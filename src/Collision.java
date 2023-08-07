import RailRoadCars.TrainSet;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Collision implements Runnable {
    private static ArrayList<TrainSet> trains;
    private static ArrayList<Thread> threads;
    private int indexOfSleep = -1;
    private LinkedBlockingDeque<String> msgDeque;

    public Collision(ArrayList<TrainSet> trains, ArrayList<Thread> threads) {
        this.trains = trains;
        this.threads = threads;
        this.msgDeque = new LinkedBlockingDeque<>();
    }

    public int getIndexOfSleep() {
        return indexOfSleep;
    }

    @Override
    public void run() {
        String msg;
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }
            ArrayList<String> nextStations = new ArrayList<>();
            ArrayList<String> currentStation = new ArrayList<>();
            for (TrainSet train : trains) {
                nextStations.add(train.getNextStation());
                currentStation.add(train.getsCurrentStation());
            }
            for (int i = 0; i < trains.size() - 1; i++) {
                for (int j = 1; j <= trains.size() - 2; j++) {
                    if (i != j && nextStations.get(i).equals(nextStations.get(j))) {
                        double x = Math.random();
                        if (x > 0.5) {
                            this.indexOfSleep = i;
                        } else {
                            this.indexOfSleep = j;
                        }
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                    this.indexOfSleep = -1;
                }
            }
        }
    }
}

