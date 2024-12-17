package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day04 {

    public static String findCharacter(int row, int col, List<List<String>> value) {
        int rows = value.size();
        int cols = value.getFirst().size();

        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return value.get(row).get(col);
        }
        return "";
    }

    public static void main(String[] args) {

        try {

            File myObj = new File("src/day04/data.txt");
            Scanner scanner = new Scanner(myObj);

            int cols = 0;
            List<List<String>> value = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                value.add(cols, List.of(line.split("")));
                cols++;
            }
            scanner.close();

            int totalRows = value.size();
            int totalCols = value.getFirst().size();
            int counter = 0;
            int counter2= 0;

            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalCols; j++) {
                    if (value.get(i).get(j).equals("X")) {
                        int[][] directions = {{-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {0, 1}, {0, -1}, {1,0}, {1,-1}};
                        for (int[] dir : directions) {
                            StringBuilder chararacter = new StringBuilder();
                            chararacter.append("X");
                            System.out.println("x found:" + i + j);
                            int newRow = i;
                            int newCol = j;
                            for (int x = 0; x < 3; x++) {
                                newRow += dir[0];
                                newCol += dir[1];
                                chararacter.append(findCharacter(newRow,newCol,value));
                            }
                            System.out.println(chararacter);
                            if (chararacter.toString().equals("XMAS")) {
                                System.out.println("i: " + i + " j: " + j + Arrays.toString(dir));
                                counter++;
                            }
                        }
                    }
                }
            }

            for (int i = 1; i < totalRows -1; i++) {
                for (int j = 1; j < totalCols -1; j++) {
                    if (value.get(i).get(j).equals("A")) {
                        if ((value.get(i - 1).get(j - 1).equals("M")) && (value.get(i + 1).get(j + 1).equals("S")) ||
                                (value.get(i - 1).get(j - 1).equals("S")) && (value.get(i + 1).get(j + 1).equals("M"))) {
                            if ((value.get(i - 1).get(j + 1).equals("M")) && (value
                                    .get(i + 1)
                                    .get(j - 1)
                                    .equals("S")) ||
                                    (value.get(i - 1).get(j + 1).equals("S")) && (value
                                            .get(i + 1)
                                            .get(j - 1)
                                            .equals("M"))) {
                                counter2++;
                            }
                        }
                    }
                }
            }
            System.out.println("Part1 counter: " + counter);
            System.out.println("Part2 counter: " + counter2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
