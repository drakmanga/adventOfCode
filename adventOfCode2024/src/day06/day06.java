package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day06 {

    public static List<Integer> findGuard(List<List<String>> map) {
        List<Integer> position = new ArrayList<>();

        int colOut = 0;
        int rowOut = 0;

        for (int row = 0; row < map.size() - 1; row++) {
            for (int col = 0; col < map.get(0).size(); col++) {
                if (map.get(row).get(col).equals("^")) {
                    colOut = col;
                    rowOut = row;
                    break;
                }
            }
        }
        position.add(rowOut);
        position.add(colOut);
        return position;
    }

    public static void visitPlaceFromGuardPart1(List<Integer> startPosition, List<List<String>> map) {

        int visitPlace = 1;
        int directionPosition = 0;

        List<List<Integer>> directions = new ArrayList<>();
        directions.add(Arrays.asList(-1, 0));
        directions.add(Arrays.asList(0, 1));
        directions.add(Arrays.asList(1, 0));
        directions.add(Arrays.asList(0, -1));

        List<Integer> guardPosition = new ArrayList<>();
        guardPosition.add(startPosition.get(0));
        guardPosition.add(startPosition.get(1));

        while (true) {

            int newRow = guardPosition.get(0) + directions.get(directionPosition).get(0);
            int newCol = guardPosition.get(1) + directions.get(directionPosition).get(1);

            if (newRow < 0 || newCol < 0 || newRow >= map.size() || newCol >= map.get(0).size()) {
                System.out.println("Out " + newRow + " " + newCol);
                break;
            }
            String newChar = map.get(newRow).get(newCol);

            if (newChar.equals("#")) {
                directionPosition = (directionPosition + 1) % directions.size();
            } else {
                guardPosition.set(0, newRow);
                guardPosition.set(1, newCol);
            }
            if (newChar.equals(".")) {
                map.get(newRow).set(newCol, "X");
                visitPlace++;
            }
        }
        System.out.println("Visit place: " + visitPlace);
    }

    public static void visitPlaceFromGuardPart2(List<Integer> startPosition, List<List<String>> map) {

        int directionPosition;
        int loop = 0;
        List<List<Integer>> directions = new ArrayList<>();

        directions.add(Arrays.asList(-1, 0));
        directions.add(Arrays.asList(0, 1));
        directions.add(Arrays.asList(1, 0));
        directions.add(Arrays.asList(0, -1));

        for (int row = 0; row < map.size(); row++) {

            for (int col = 0; col < map.get(row).size(); col++) {

                if (row == startPosition.get(0) && col == startPosition.get(1)) {
                    continue;
                }
                String charMap = map.get(row).get(col);
                if (!charMap.equals("#")) {
                    map.get(row).set(col, "#");
                    List<Integer> guardPosition = new ArrayList<>();

                    guardPosition.add(startPosition.get(0));
                    guardPosition.add(startPosition.get(1));
                    directionPosition = 0;
                    HashMap<String, Boolean> hashPosition = new HashMap<>();

                    while (true) {

                        int newRow = guardPosition.get(0) + directions.get(directionPosition).get(0);
                        int newCol = guardPosition.get(1) + directions.get(directionPosition).get(1);

                        if (newRow < 0 || newCol < 0 || newRow >= map.size() || newCol >= map.get(0).size()) {
//                            System.out.println("Out " + newRow + " " + newCol + "row: " + row + "-" + col);
                            break;
                        }
                        String newChar = map.get(newRow).get(newCol);
                        String keyHash = newRow + "-" + newCol + "-" + directionPosition;

                        if (newChar.equals("#")) {
                            directionPosition = (directionPosition + 1) % directions.size();
                        } else {
                            guardPosition.set(0, newRow);
                            guardPosition.set(1, newCol);
                        }

                        if (hashPosition.containsKey(keyHash)) {
                            loop++;
                            break;
                        } else {
                            hashPosition.put(keyHash, true);
                        }
                    }
                    map.get(row).set(col, ".");
                }
            }
        }
        System.out.println("Loop total: " + loop);
    }

    public static void main(String[] args) {
        try {

            File myObj = new File("src/day06/data.txt");
            Scanner scanner = new Scanner(myObj);

            List<List<String>> map = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                map.add(new ArrayList<>(List.of(line.split(""))));
            }
            scanner.close();

            visitPlaceFromGuardPart1(findGuard(map),map);
            visitPlaceFromGuardPart2(findGuard(map), map);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
