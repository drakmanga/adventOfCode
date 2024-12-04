package day01;

import java.io.*;
import java.util.*;


public class day01 {
    public static void main(String[] args) {

        try {
            File myObj = new File("src/day01/data.txt");
            Scanner myReader = new Scanner(myObj);
            List<Integer> listNumber1 = new ArrayList<>();
            List<Integer> listNumber2 = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] numbers = line.split(" {3}"); // Split line by 3 spaces

                //Create two list from the column
                for (int i=0; i < numbers.length; i++) {
                    listNumber1.add(Integer.parseInt(numbers[i]));
                    listNumber2.add(Integer.parseInt(numbers[++i]));
                }
            }
            Collections.sort(listNumber1);
            Collections.sort(listNumber2);

            //calculate the total similarity score
            int maxFinding = 0;
            for (int listNumber1point : listNumber1) {
                int valueFinding = 0;
                for (int listNumber2point : listNumber2) {
                    if (listNumber1point == listNumber2point) {
                        valueFinding++;
                    }
                }
                maxFinding += listNumber1point * valueFinding;
            }
            System.out.println("Total similarity score: " + maxFinding);

            //found the total distance from difference
            int max = 0;
            while (!listNumber1.isEmpty() && !listNumber2.isEmpty()) {
                int value;
                if ( Collections.min(listNumber2) > Collections.min(listNumber1)) {
                    value = Collections.min(listNumber2) - Collections.min(listNumber1);
                } else {
                    value = Collections.min(listNumber1) - Collections.min(listNumber2);
                }
                max = max + value;
                listNumber1.remove(Collections.min(listNumber1));
                listNumber2.remove(Collections.min(listNumber2));
            }
            System.out.println("Total distance from difference: " + max);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
