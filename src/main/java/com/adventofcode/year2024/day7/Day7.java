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
        private List<Integer> numbers;

        public boolean isValid() {
            return compute(this.testValue, numbers.getFirst(), numbers.subList(1, numbers.size())) != -1;
        }

        // return -1 if not found or the correct testValue
        private long compute(long testValue, long partial, List<Integer> otherNumbers) {
            if (otherNumbers.isEmpty()) {
                return -1;
            }

            long next = otherNumbers.getFirst();
            if (otherNumbers.size() == 1 && (partial + next == testValue || partial * next == testValue)) {
                return testValue;
            }

            long newPartial = partial + next;
            var t1 = compute(testValue, newPartial, otherNumbers.subList(1, otherNumbers.size()));
            if (t1 != -1) {
                return t1;
            }

            newPartial = partial * next;
            return compute(testValue, newPartial, otherNumbers.subList(1, otherNumbers.size()));
        }
    }

    public static long solve(List<String> input) {
        List<Equation> equations = new ArrayList<>();

        for (String row : input) {
            String[] split = row.split(":");
            equations.add(new Equation(
                Long.parseLong(split[0]),
                Arrays.stream(split[1].substring(1).split(" ")).map(Integer::parseInt).toList()
            ));
        }

        return equations.stream()
            .filter(Equation::isValid)
            .peek((e) -> System.out.println("The equation is valid: " + e.testValue))
            .map(Equation::getTestValue)
            .reduce(0L, Long::sum);
    }

    public static int solve2(List<String> input) {
        return 0;
    }
}
