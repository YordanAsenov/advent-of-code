package com.asenov.adventofcode.year2023.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {
    private static final List<Character> separators =
        List.of('.', '*', '+', '-', '=', '/', '%', '$', '&', '#', '@');

    public static List<Number> getNumbers(String row) {
        List<Number> numbers = new ArrayList<>();

        char[] rowCharArray = row.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowCharArray.length; i++) {
            if (separators.contains(rowCharArray[i])) {
                String number = sb.toString();
                if (!number.isEmpty()) {
                    numbers.add(new Number(i - number.length(), number));
                }
                sb = new StringBuilder();
                continue;
            }
            sb.append(rowCharArray[i]);
            if (i == rowCharArray.length - 1) {
                String number = sb.toString();
                if (!number.isEmpty()) {
                    numbers.add(new Number(i - number.length() + 1, number));
                }
                sb = new StringBuilder();
            }
        }
        return numbers;
    }

    public static int solveFirstPart(List<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String upperRow = (i == 0) ? "" : input.get(i - 1);
            String row = input.get(i);
            String lowerRow = (i == input.size() - 1) ? "" : input.get(i + 1);

            List<Number> numbers = getNumbers(row);
            for (Number number : numbers) {
                if (number.isPartNumber(upperRow, row, lowerRow))
                    sum += Integer.parseInt(number.getValue());
                }
        }

        return sum;
    }

    public static int solve(List<String> input) {
        Map<Gear, List<Number>> gears = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            String upperRow = (i == 0) ? "" : input.get(i - 1);
            String row = input.get(i);
            String lowerRow = (i == input.size() - 1) ? "" : input.get(i + 1);

            List<Number> numbers = getNumbers(row);
            for (Number number : numbers) {
                if (number.isPartNumber(upperRow, row, lowerRow) &&
                    number.hasGear(upperRow, row, lowerRow)) {

                    Gear adjacentGear = number.getAdjacentGear(upperRow, row, lowerRow, i);
                    if (adjacentGear != null) {
                        if (!gears.containsKey(adjacentGear)) {
                            ArrayList<Number> partNumbers = new ArrayList<>();
                            partNumbers.add(number);
                            gears.put(adjacentGear, partNumbers);
                        } else {
                            List<Number> partNumbers = gears.get(adjacentGear);
                            partNumbers.add(number);
                            gears.put(adjacentGear, partNumbers);
                        }
                    }
                }
            }
        }

        return gears.values().stream()
            .filter(numbers -> numbers.size() == 2)
            .map(numbers -> numbers.stream()
                .map(n -> Integer.parseInt(n.getValue()))
                .reduce(1, (n1, n2) -> n1 * n2))
            .reduce(0, Integer::sum);
    }
}
