package com.asenov.adventofcode.year2023.day3;

import java.util.ArrayList;
import java.util.List;

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

    public static int solve(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {
            String upperRow = (i == 0) ? "" : input.get(i - 1);
            String row = input.get(i);
            String lowerRow = (i == input.size() - 1) ? "" : input.get(i + 1);

            List<Number> numbers = getNumbers(row);
            for (Number number : numbers) {
                if (number.isPartNumber(upperRow, row, lowerRow)) {
                    sum += Integer.parseInt(number.getValue());
                } else {
                    System.out.println("Number: " + number.getValue() + " is not a part number");
                }
            }
        }

        return sum;
    }
}
