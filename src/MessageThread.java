
import RailRoadCars.TrainSet;

import java.util.concurrent.LinkedBlockingDeque;

public class MessageThread implements Runnable {
    private LinkedBlockingDeque<String> msgQueue;
    private TrainSet train;
    public MessageThread(LinkedBlockingDeque<String> msgQueue, TrainSet train) {
        this.train = train;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
            while (true) {
                String msg;
                msg = msgQueue.pollFirst();
                if (msg != null) {
                    try {
                        System.out.print(msg);
                        if (msg.contains("Reached") || msg.contains("Next")) {
                            Thread.sleep(2000);
                        }
                        if (msg.contains("renewed")) {
                            Thread.sleep(3000);
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                if (this.train.isMsgSleep() == true){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {

                    }
                }
            }
    }

}
