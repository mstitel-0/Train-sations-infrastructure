package Interfaces;
import java.util.Random;
import java.util.ArrayList;
public interface GenerateNum {
   ArrayList<String> trainNumbers = new ArrayList<>();
    default String generateNum(){
        Random rand = new Random();
        String number ="";
            int x;
            char y;
        while (trainNumbers.contains(number) || number.equals("")) {
            for (int i = 0; i < 4; i++) {
                x = rand.nextInt(10);
                number += String.valueOf(x);
            }
            number += "-";
            for (int i = 0; i < 2; i++) {
                y = (char) (97 + rand.nextInt(26));
                number += String.valueOf(y).toUpperCase();
            }
        }
        trainNumbers.add(number);
        return number;
    }


}
