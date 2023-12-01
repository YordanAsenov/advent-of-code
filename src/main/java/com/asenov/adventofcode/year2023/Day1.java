package com.asenov.adventofcode.year2023;

import java.util.List;

public class Day1 {
    public static Integer firstNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return Integer.parseInt("" + c);
            }
        }
        return -1;
    }

    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();
        return sb.toString();
    }

    public static Integer combineFirstAndLastNumber(String input) {
        Integer firstNumber = firstNumber(input);
        Integer lastNumber = firstNumber(reverseString(input));
        return Integer.parseInt(firstNumber + "" + lastNumber);
    }

    public static Integer sumCalibrationValues(List<String> input) {
        return input.stream()
            .map(Day1::combineFirstAndLastNumber)
            .reduce(0, Integer::sum);
    }
}
