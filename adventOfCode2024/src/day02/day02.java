package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day02 {

    public static boolean isAscending(String[] numberString) {
        for (int i =1; i < numberString.length; i++) {
            if ((Integer.parseInt(numberString[i])) < (Integer.parseInt(numberString[i -1]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean bruteForceCheck(String[] numberString) {
        for (int i = 0; i < numberString.length; i++){
            String[] left = Arrays.copyOfRange(numberString ,0, i);
            String[] right = Arrays.copyOfRange(numberString, i + 1, numberString.length);

            List<String> combinedList = new ArrayList<>();
            combinedList.addAll(Arrays.asList(left));
            combinedList.addAll(Arrays.asList(right));

            String[] complete = combinedList.toArray(new String[0]);

            if ((isAscending(complete) != isDescending(complete)) && !differenceElevateOrNoDifference(complete)) {
                System.out.println("-" + Arrays.toString(complete) + " NEW SAFE");
                return true;

            }
        }
        return false;
    }

    public static boolean isDescending(String[] numberString) {
        for (int i = 1; i < numberString.length; i++) {
            if ((Integer.parseInt(numberString[i])) > (Integer.parseInt(numberString[i -1]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean differenceElevateOrNoDifference(String[] numberString) {

        for (int i = 1; i < numberString.length; i++) {
            int digit1 = Integer.parseInt(numberString[i - 1]);
            int digit2 = Integer.parseInt(numberString[i]);
            int difference = Math.abs(digit1 - digit2);
            if (difference > 3 || difference == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("src/day02/data.txt");
            Scanner myReader = new Scanner(myObj);
            List<String[]> lines = new ArrayList<>();

            //Insert all data into a List
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(" ");
                lines.add(line);
            }
            int counter = 0;
            for (String[] word : lines) {

                if ((isAscending(word) == isDescending(word)) || differenceElevateOrNoDifference(word)) {
                    System.out.println("-" + Arrays.toString(word) + " REAL UNSAFE");
                    if (bruteForceCheck(word)) {
                        counter++;
                    }
                }
                else {
                    System.out.println("-" + Arrays.toString(word) + " REAL SAFE");
                    counter++;
                }
            }
            System.out.println("Report safe count: " + counter);
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e. fillInStackTrace();
        }
    }
}
