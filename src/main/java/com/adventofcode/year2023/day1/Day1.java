package com.adventofcode.year2023.day1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Day1 {
    private final static Map<String, Integer> letterDigits = Map.of(
        "one", 1,
        "two", 2,
        "three", 3,
        "four", 4,
        "five", 5,
        "six", 6,
        "seven", 7,
        "eight", 8,
        "nine", 9
    );

    public static Integer getIndexOfFirstNumberDigit(String input) {
        int index = 999;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static Integer getIndexOfLastNumberDigit(String input) {
        int index = -1;
        for (int i = input.length() - 1; i > -1; i--) {
            if (Character.isDigit(input.charAt(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static Integer getIndexOfFirstLetterDigit(String input) {
        return letterDigits.keySet().stream()
            .map(input::indexOf)
            .filter(index -> index > -1)
            .min(Comparator.naturalOrder())
            .orElse(99999);
    }

    public static Integer getIndexOfLastLetterDigit(String input) {
        return letterDigits.keySet().stream()
            .map(input::lastIndexOf)
            .filter(index -> index > -1)
            .max(Comparator.naturalOrder())
            .orElse(-1);
    }

    public static Integer getFirstLetterDigit(String input, int index) {
        return letterDigits.entrySet().stream()
            .filter(digit -> input.indexOf(digit.getKey()) == index)
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(-1);
    }

    public static Integer getLastLetterDigit(String input, int index) {
        return letterDigits.entrySet().stream()
            .filter(digit -> input.lastIndexOf(digit.getKey()) == index)
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(-1);
    }

    public static Integer firstNumber(String input) {
        Integer indexOfFirstLetterDigit = getIndexOfFirstLetterDigit(input);
        Integer indexOfFirstNumberDigit = getIndexOfFirstNumberDigit(input);

        if (indexOfFirstNumberDigit < indexOfFirstLetterDigit) {
            return Integer.parseInt("" + input.charAt(indexOfFirstNumberDigit));
        } else {
            return getFirstLetterDigit(input, indexOfFirstLetterDigit);
        }
    }

    public static Integer lastNumber(String input) {
        Integer indexOfLastLetterDigit = getIndexOfLastLetterDigit(input);
        Integer indexOfLastNumberDigit = getIndexOfLastNumberDigit(input);

        if (indexOfLastNumberDigit > indexOfLastLetterDigit) {
            return Integer.parseInt("" + input.charAt(indexOfLastNumberDigit));
        } else {
            return getLastLetterDigit(input, indexOfLastLetterDigit);
        }
    }

    public static Integer combineFirstAndLastNumber(String input) {
        Integer firstNumber = firstNumber(input);
        Integer lastNumber = lastNumber(input);
        return Integer.parseInt(firstNumber + "" + lastNumber);
    }

    public static Integer sumCalibrationValues(List<String> input) {
        return input.stream()
            .map(Day1::combineFirstAndLastNumber)
            .reduce(0, Integer::sum);
    }
}
