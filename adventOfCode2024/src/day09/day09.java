package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day09 {

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        File myObj = new File("src/day09/data.txt");
        Scanner scanner = new Scanner(myObj);

        String line = scanner.nextLine();

        List<Integer> lineCompletePart1 = new ArrayList<>(lineFormattorPt1(line));
        List<List<Integer>> lineCompletePart2 = new ArrayList<>(lineFormattorPt2(line));

        scanner.close();

        System.out.println("Result part1: " + multiplicationCompactLinePart1(compactLinePart1(lineCompletePart1)));
        System.out.println("Result part2: " + multiplicationCompactLinePart2(compactLinePart2(lineCompletePart2)));

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " +
                ((endTime - startTime) / 1000) + "seconds");
    }

    public static List<List<Integer>> compactLinePart2(List<List<Integer>> line) {
        int spotFree = 0;
        int lineLength = line.size() - 1;

        while (lineLength >= 0) {

            if (line.get(spotFree).get(2) != -1) {
                spotFree++;
                continue;
            }
            if (line.get(lineLength).get(2) == -1) {
                lineLength--;
                continue;
            }

            if (spotFree > lineLength) {
                spotFree = 0;
                lineLength--;
                continue;
            }

            if (line.get(lineLength).get(1) <= line.get(spotFree).get(1)) {
                line.get(lineLength).set(0, line.get(spotFree).get(0));
                line.get(spotFree).set(1, line.get(spotFree).get(1) - line.get(lineLength).get(1));
                line.get(spotFree).set(0, line.get(spotFree).get(0) + line.get(lineLength).get(1));
                spotFree = 0;
                lineLength--;
            }else {
                spotFree++;
            }
        }
        return line;
    }

    public static List<Integer> compactLinePart1(List<Integer> line) {
        int spotFree = 1;

        int lineLength = line.size() - 1;
        while (spotFree < lineLength) {

            if (line.get(spotFree) != -1) {
                spotFree++;
                continue;
            }
            if (line.get(lineLength) == -1) {
                lineLength--;
                continue;
            }
            Collections.swap(line, spotFree, lineLength);
            lineLength--;

            spotFree++;
        }
        return line;
    }

    public static List<List<Integer>> lineFormattorPt2(String line) {
        List<List<Integer>> lineComplete = new ArrayList<>();
        int id = 0;
        boolean file = true;
        int start = 0;


        for (char symbol : line.toCharArray()) {

            int gi = symbol - 48;

            if (file) {
                lineComplete.add(Arrays.asList(start, gi, id));
                id++;
            } else {
                lineComplete.add(Arrays.asList(start, gi, -1));
            }
            file = !file;
            start += gi;
        }
        return lineComplete;
    }

    public static List<Integer> lineFormattorPt1(String line) {
        List<Integer> lineComplete = new ArrayList<>();
        int id = 0;
        boolean file = true;

        for (char symbol : line.toCharArray()) {
            int gi = symbol - 48;

            if (file) {
                for (int i = 0; i < gi; i++) {
                    lineComplete.add(id);
                }
                id++;
                file = false;
            } else {
                for (int i = 0; i < gi; i++) {
                    lineComplete.add(-1);
                }
                file = true;
            }
        }
        return lineComplete;
    }

    public static Long multiplicationCompactLinePart1(List<Integer> line) {
        long sum = 0;

        for (int row = 0; row < line.size(); row++) {
            if (line.get(row) == -1) break;

            sum += (long) row * line.get(row);
        }
        return sum;
    }

    public static Long multiplicationCompactLinePart2(List<List<Integer>> line) {
        long sum = 0;

        for (List<Integer> position : line) {
            if (position.get(2) == -1) continue;
            long value = 0;
            for (int i = 0; i < position.get(1); i++) {
                value += (long) (position.get(0) + i) * position.get(2);
            }
            sum += value;
        }
        return sum;
    }
}
