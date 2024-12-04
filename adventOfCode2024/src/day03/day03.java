package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day03 {
    public static void main(String[] args ) throws FileNotFoundException {

        File myObj = new File("src/day03/data.txt");
        Scanner myReader = new Scanner(myObj);
        String input = myReader.useDelimiter("\\Z").next();
        myReader.close();

        int part1 = 0, part2 = 0;
        boolean doMultiply = true;

        Pattern pattern = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group(1);
            if (match.equals("do()")) {
                doMultiply = true;
            } else if (match.equals("don't()")) {
                doMultiply = false;
            } else {
                String[] numbers = match.substring(4, match.length() - 1).split(",");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                int sum = num1 * num2;
                part1 += sum;
                if (doMultiply) {
                    part2 += sum;
                }
            }
        }
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }
}
