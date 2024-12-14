package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day08 {

    public static boolean isInRangeOfMap(int length, int col, int row) {
        return col < length && row < length && row >= 0 && col >= 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        File myObj = new File("src/day08/data.txt");
        Scanner scanner = new Scanner(myObj);

        List<List<String>> map = new ArrayList<>();

        int index = 0;
        int lengthMap = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lengthMap = line.length();
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '.') {
                    String value = c + ":" + index + "-" + col;
                    map.add(List.of(value.split("[:-]")));
                }
            }
            index++;
        }
        scanner.close();

        HashMap<String, Character> antinodePart1 = new HashMap<>();
        HashMap<String, Character> antinodePart2 = new HashMap<>();

        for (int t1Index = 0; t1Index < map.size(); t1Index++) {
            for (int t2Index = t1Index + 1; t2Index < map.size(); t2Index++) {

                String tf1 = map.get(t1Index).getFirst();
                String tf2 = map.get(t2Index).getFirst();
                if (tf1.equals(tf2)) {
                    int t1row = Integer.parseInt(String.valueOf(map.get(t1Index).get(1)));
                    int t1col = Integer.parseInt(String.valueOf(map.get(t1Index).get(2)));
                    int t2row = Integer.parseInt(String.valueOf(map.get(t2Index).get(1)));
                    int t2col = Integer.parseInt(String.valueOf(map.get(t2Index).get(2)));

                    int differenceRowT1T2 = t1row - t2row;
                    int differenceRowT2T1 = t2row - t1row;
                    int differenceColT1T2 = t1col - t2col;
                    int differenceColT2T1 = t2col - t1col;

                    int specularRowT1 = differenceRowT1T2 + t1row;
                    int specularColT1 = differenceColT1T2 + t1col;

                    int specularRowT2 = differenceRowT2T1 + t2row;
                    int specularColT2 = differenceColT2T1 + t2col;


                    boolean checkValueLengthT1 =
                            isInRangeOfMap(lengthMap, specularColT1, specularRowT1);
                    boolean checkValueLengthT2 =
                            isInRangeOfMap(lengthMap, specularColT2, specularRowT2);

                    if (checkValueLengthT1) {
                        antinodePart1.put(specularRowT1 + "," + specularColT1, '#');
                    }
                    if (checkValueLengthT2) {
                        antinodePart1.put(specularRowT2 + "," + specularColT2, '#');
                    }

                    //part2
                    if (checkValueLengthT1) {
                        antinodePart2.put(specularRowT1 + "," + specularColT1, '#');
                    }
                    if (checkValueLengthT2) {
                        antinodePart2.put(specularRowT2 + "," + specularColT2, '#');
                    }

                    for (specularRowT1 = t1row, specularColT1 = t1col; isInRangeOfMap(lengthMap, specularColT1,
                            specularRowT1); specularRowT1 += differenceRowT1T2,
                            specularColT1 += differenceColT1T2) {

                            antinodePart2.put(specularRowT1 + "," + specularColT1, '#');
                    }

                    for (specularRowT2 = t2row, specularColT2 = t2col; isInRangeOfMap(lengthMap, specularColT2,
                            specularRowT2); specularRowT2 += differenceRowT2T1, specularColT2 += differenceColT2T1) {

                            antinodePart2.put(specularRowT2 + "," + specularColT2, '#');
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Number of antinodePart1 part1:" + antinodePart1.size());
        System.out.println("Number of antinodePart1 part2:" + antinodePart2.size());

        System.out.println("Time: " +
                ((endTime - startTime)/1000) + "seconds") ;
    }
}

