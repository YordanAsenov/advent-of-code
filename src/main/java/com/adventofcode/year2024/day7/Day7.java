package com.adventofcode.year2024.day7;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    @AllArgsConstructor
    @Data
    private static class Equation {
        private long testValue;
        private List<Long> numbers;

        public boolean isValid() {
            return compute(this.testValue, numbers.getFirst(), numbers.subList(1, numbers.size())) != -1;
        }

        // return -1 if not found or the correct testValue
        private long compute(long testValue, long partial, List<Long> otherNumbers) {
            if (otherNumbers.isEmpty()) {
                return -1;
            }

            long next = otherNumbers.getFirst();

            long partialSum = partial + next;
            long partialMultiplication = partial * next;

            if (otherNumbers.size() == 1 && (partialSum == testValue || partialMultiplication == testValue)) {
                return testValue;
            }

            var t1 = compute(testValue, partialSum, otherNumbers.subList(1, otherNumbers.size()));
            if (t1 != -1) {
                return t1;
            }

            return compute(testValue, partialMultiplication, otherNumbers.subList(1, otherNumbers.size()));
        }

        public boolean isValid2() {
            return compute2(this.testValue, numbers.getFirst(), numbers.subList(1, numbers.size())) != -1;
        }

        // return -1 if not found or the correct testValue
        private long compute2(Long testValue, Long partial, List<Long> otherNumbers) {
            if (otherNumbers.isEmpty()) {
                return -1;
            }

            Long next = otherNumbers.getFirst();

            long partialSum = partial + next;
            long partialMultiplication = partial * next;
            long partialConcatenation = Long.parseLong(partial + "" + next);

            if (otherNumbers.size() == 1 && (partialSum == testValue || partialMultiplication == testValue || partialConcatenation == testValue)) {
                return testValue;
            }

            var t1 = compute2(testValue, partialSum, otherNumbers.subList(1, otherNumbers.size()));
            if (t1 != -1) {
                return t1;
            }

            var t2 = compute2(testValue, partialMultiplication, otherNumbers.subList(1, otherNumbers.size()));
            if (t2 != -1) {
                return t2;
            }

            return compute2(testValue, partialConcatenation, otherNumbers.subList(1, otherNumbers.size()));
        }
    }

    public static long solve(List<String> input) {
        List<Equation> equations = new ArrayList<>();

        for (String row : input) {
            String[] split = row.split(":");
            equations.add(new Equation(
                Long.parseLong(split[0]),
                Arrays.stream(split[1].substring(1).split(" ")).map(Long::parseLong).toList()
            ));
        }

        return equations.stream()
            .filter(Equation::isValid)
            .peek((e) -> System.out.println("The equation is valid: " + e.testValue))
            .map(Equation::getTestValue)
            .reduce(0L, Long::sum);
    }

    public static long solve2(List<String> input) {
        List<Equation> equations = new ArrayList<>();

        for (String row : input) {
            String[] split = row.split(":");
            equations.add(new Equation(
                Long.parseLong(split[0]),
                Arrays.stream(split[1].substring(1).split(" ")).map(Long::parseLong).toList()
            ));
        }

        return equations.stream()
            .filter(Equation::isValid2)
            .peek((e) -> System.out.println("The equation is valid: " + e.testValue))
            .map(Equation::getTestValue)
            .reduce(0L, Long::sum);
    }
}
