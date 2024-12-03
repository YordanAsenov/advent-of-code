package com.adventofcode.year2024.day3;

import java.util.List;

public class Day3 {

    public static boolean allDigits(String input) {
        if (input.isEmpty() || input.contains(" ")) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        int test = Integer.parseInt(input);
        return test >= 0 && test <= 999;
    }

    public static int solve(List<String> input) {
        int sum = 0;

        for (String sequence : input) {
            while (!sequence.isEmpty()) {
                int start = sequence.indexOf("mul(");
                int firstValueIndex = start + 4;
                int end = sequence.indexOf(")", firstValueIndex);
                if (start == -1 || end == -1) {
                    break;
                }

                String section = sequence.substring(firstValueIndex, end);
                if (section.contains("mul(")) {
                    sequence = sequence.substring(firstValueIndex);
                    continue;
                }

                String[] numbers = section.split(",");
                if (numbers.length > 2 || !allDigits(numbers[0]) || !allDigits(numbers[1])) {
                    sequence = sequence.substring(firstValueIndex);
                    continue;
                }

                Integer first = Integer.parseInt(numbers[0]);
                Integer second = Integer.parseInt(numbers[1]);
                sum += (first * second);

                sequence = sequence.substring(end + 1);
            }
        }

        return sum;
    }

    public static int solve2(List<String> input) {
        int sum = 0;

        boolean shouldDo = true;

        for (String sequence : input) {
            String initialSequence = sequence;
            int index;

            while (!sequence.isEmpty()) {
                int start = sequence.indexOf("mul(");
                int firstValueIndex = start + 4;
                int end = sequence.indexOf(")", firstValueIndex);
                if (start == -1 || end == -1) {
                    break;
                }

                String section = sequence.substring(firstValueIndex, end);
                if (section.contains("mul(")) {
                    sequence = sequence.substring(firstValueIndex);
                    continue;
                }

                String[] numbers = section.split(",");
                if (numbers.length > 2 || !allDigits(numbers[0]) || !allDigits(numbers[1])) {
                    sequence = sequence.substring(firstValueIndex);
                    continue;
                }

                String operation = sequence.substring(start, end + 1);
                index = (initialSequence.lastIndexOf(operation));

                String lastCommand = initialSequence.substring(0, index);
                int lastDoIndex = lastCommand.lastIndexOf("do()");
                int lastDontIndex = lastCommand.lastIndexOf("don't()");

                if (lastDoIndex > lastDontIndex) {
                    shouldDo = true;
                } else if (lastDontIndex > lastDoIndex ) {
                    shouldDo = false;
                }

                if (shouldDo) {
                    Integer first = Integer.parseInt(numbers[0]);
                    Integer second = Integer.parseInt(numbers[1]);
                    sum += (first * second);
                }

                sequence = sequence.substring(end + 1);
            }
        }

        return sum;
    }
}
