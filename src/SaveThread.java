import RailRoadCars.RailRoadCar;
import RailRoadCars.TrainSet;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SaveThread implements Runnable{
    private ArrayList<TrainSet> trains;
    private File AppStateFile;

    public SaveThread(ArrayList<TrainSet> trainsOld) {
        ArrayList<TrainSet>trainsNew = new ArrayList<>();
        for (TrainSet train:trainsOld){
            trainsNew.add(train);
        }
        for(TrainSet train : trainsNew){
           Collections.sort(train.getTrainSetRailRoadCars(),new Comparator<RailRoadCar>(){
               @Override
               public int compare(RailRoadCar o1, RailRoadCar o2) {
                   return Integer.compare(o1.getCompWeight(),o2.getCompWeight());
               }
           } );
        }
        Collections.sort(trainsNew,new Comparator<TrainSet>(){
            @Override
            public int compare(TrainSet o1, TrainSet o2) {
                return Integer.compare(o2.getRoute().getRouteDistance(),o1.getRoute().getRouteDistance());
            }
        });
        this.trains = trainsNew;
        this.AppStateFile = new File("AppState.txt");
    }
    @Override
    public void run() {
        while (true){
            try {
                FileWriter fWriter = new FileWriter(AppStateFile);

               for(TrainSet train:trains){
                   fWriter.write(train.toSort() + "\n");
               }
               fWriter.close();
            } catch (IOException e) {
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }

    }

}
