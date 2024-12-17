package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class day10 {

    public static List<List<Integer>> findTheZero(List<List<String>> map) {
        List<List<Integer>> position = new ArrayList<>(List.of());
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                if (Integer.parseInt(map.get(row).get(col)) == 0) {
                    position.add(List.of(row, col));
                }
            }
        }
        return position;
    }

    public static void findWayPart1(List<List<String>> map, List<Integer> position,
                                    HashMap<String, Integer> nineUnique) {

        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int rowStart = position.get(0);
        int colStart = position.get(1);
        int valueBefore = Integer.parseInt(String.valueOf(map.get(rowStart).get(colStart)));

        if (valueBefore == 9 ) {
            nineUnique.put(rowStart + "-" + colStart, 9);
            return;
        }

        for (int[] direction : directions) {
            int newRow = rowStart + direction[0];
            int newCol = colStart + direction[1];
            if (newRow >= 0 && newRow < map.size() && newCol >= 0 && newCol < map.get(newRow).size()) {
                int value = Integer.parseInt(String.valueOf(map.get(newRow).get(newCol)));

                if (value - 1 == valueBefore) {
                    findWayPart1(map,List.of(newRow,newCol), nineUnique);
                }
            }
        }
    }

    public static void findWayPart2(List<List<String>> map, List<Integer> position,
                                   List<String> wayFind) {

        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int rowStart = position.get(0);
        int colStart = position.get(1);
        int valueBefore = Integer.parseInt(String.valueOf(map.get(rowStart).get(colStart)));

        if (valueBefore == 9 ) {
            wayFind.add(rowStart + "-" + colStart + ":" + 9);
            return;
        }

        for (int[] direction : directions) {
            int newRow = rowStart + direction[0];
            int newCol = colStart + direction[1];
            if (newRow >= 0 && newRow < map.size() && newCol >= 0 && newCol < map.get(newRow).size()) {
                int value = Integer.parseInt(String.valueOf(map.get(newRow).get(newCol)));

                if (value - 1 == valueBefore) {
                    findWayPart2(map,List.of(newRow,newCol), wayFind);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("src/day10/data.txt");
        Scanner scanner = new Scanner(myObj);

        List<List<String>> map = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.add(List.of(line.split("")));
        }
        List<List<Integer>> positionOfZero = new ArrayList<>(findTheZero(map));

        int wayFindPart1 = 0;
        int wayFindPart2 = 0;
        for (List<Integer> zero : positionOfZero) {
            HashMap<String, Integer> nineUnique = new HashMap<>();
            List<String> wayFind = new ArrayList<>();
            findWayPart1(map, zero, nineUnique);
            findWayPart2(map, zero, wayFind);
            wayFindPart1 += nineUnique.size();
            wayFindPart2 += wayFind.size();

            System.out.println(nineUnique);
        }

        System.out.println("Part1: " + wayFindPart1);
        System.out.println("Part2: " + wayFindPart2);

    }
}
