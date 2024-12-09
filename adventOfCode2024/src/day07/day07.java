package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day07 {

    public static boolean canBeSum(long total, String[] numbers, long result) {

        if (result == total) {
            return true;
        }

        if (numbers.length == 0) {
            return false;
        }

        if (result > total) {
            return false;
        }

        long num1 = Long.parseLong(numbers[0]);

        String[] remainingNumbers = Arrays.copyOfRange(numbers, 1, numbers.length);

        long resultAddition = num1 + result;
        long resultMolti = num1 * result;
        //add part2
        long resultConcatenation = Long.parseLong( result + String.valueOf(num1));
        return canBeSum(total, remainingNumbers, resultAddition) || canBeSum(total, remainingNumbers, resultMolti) || canBeSum(total,remainingNumbers,resultConcatenation);
    }

    public static void main(String[] args) {

        try {
            File myObj = new File("src/day07/data.txt");
            Scanner scanner = new Scanner(myObj);
            List<String> value = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                value.add(line);
            }
            scanner.close();

            long totalMatchLine = 0;

            for (String line : value) {
                String[] components = line.split(":");

                long testValue = Long.parseLong(components[0].trim());
                String[] numbers = components[1].trim().split(" ");
                String[] numbersWithoutFirstElements = Arrays.copyOfRange(numbers, 1, numbers.length);

                if (canBeSum(testValue, numbersWithoutFirstElements, Long.parseLong(numbers[0]))) {
                    totalMatchLine += testValue;
                }
            }

            System.out.println("Total sum: " + totalMatchLine);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
