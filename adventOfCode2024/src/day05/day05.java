package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day05 {

    public static void part1 (List<List<String>> rules , List<List<String>> updates) {
        int correctCode = 0;
        outer:
        for (int row = 0; row < updates.size(); row++) {
//            System.out.println("row: " + row);
            List<String> line = updates.get(row);

            for (int num = 0; num < updates.get(row).size(); num++) {
//                System.out.println("num: " + num);
                String page = line.get(num);

                for (int pair = num + 1; pair < line.size(); pair++) {
//                    System.out.println("pair: " + pair);
                    String page2 = line.get(pair);

                    if (rules.contains(List.of(page2, page))) {
//                        System.out.println("regola infrantra");
                        continue outer;
                    }
                }
            }
            correctCode += Integer.parseInt(String.valueOf(line.get((line.size() - 1) / 2)));
        }
        System.out.println("Part1: " + correctCode);
    }

    public static void part2 (List<List<String>> rules , List<List<String>> updates) {
        int correctCode = 0;
        outer:
        for (int row = 0; row < updates.size(); row++) {
//            System.out.println("row: " + row);
            List<String> line = new ArrayList<>(updates.get(row));
            boolean swap = false;
            for (int num = 0; num < updates.get(row).size(); ) {

//                System.out.println("num: " + num);
                String page = line.get(num);

                for (int pair = num + 1; pair < line.size(); pair++) {
//                    System.out.println("pair: " + pair);
                    String page2 = line.get(pair);

                    if (rules.contains(List.of(page2, page))) {
//                        System.out.println("regola infrantra");
                        swap = true;
                        line.set(num, page2);
                        line.set(pair, page);
//                        System.out.println("page: " + page);
//                        System.out.println("page2: " + page2);
                        num--;
                        break;
                    }
                }
                num++;
            }
            if (swap) correctCode += Integer.parseInt(String.valueOf(line.get((line.size() - 1) / 2)));
        }
        System.out.println("Parte2: " + correctCode);
    }

    public static void main(String[] args) {

        try {

            File myObj = new File("src/day05/data.txt");
            Scanner scanner = new Scanner(myObj);

            Pattern patternData = Pattern.compile("(\\d{2}\\|\\d{2})");
            Pattern patternList = Pattern.compile("(\\d{2},\\s*\\d{2}(?:,\\s*\\d{2})*)");

            int colsValue = 0;
            int colsList = 0;
            List<List<String>> rules = new ArrayList<>();
            List<List<String>> updates = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcherData = patternData.matcher(line);
                if (matcherData.find()) {
                    rules.add(colsValue, List.of(line.split("\\|")));
                    colsValue++;
                }
                Matcher matcherList = patternList.matcher(line);
                if (matcherList.find()) {
                    updates.add(colsList, List.of(line.split(",")));
                    colsList++;
                }
            }
            scanner.close();
//            System.out.println("lista: " + updates);
//            System.out.println("rules: " + rules);

            part1(rules,updates);
            part2(rules,updates);

        } catch (
                FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
