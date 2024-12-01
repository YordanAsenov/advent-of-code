package com.adventofcode.year2024.day1;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static int solve(List<String> input) {
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for (String s : input) {
            String[] numbers = s.split("   ");
            firstList.add(Integer.parseInt(numbers[0]));
            secondList.add(Integer.parseInt(numbers[1]));
        }

        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);

        int result = 0;
        for (int i = 0; i < firstList.size(); i++) {
            result += Math.abs(firstList.get(i) - secondList.get(i));
        }

        return result;
    }

    public static int solve2(List<String> input) {
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for (String s : input) {
            String[] numbers = s.split("   ");
            firstList.add(Integer.parseInt(numbers[0]));
            secondList.add(Integer.parseInt(numbers[1]));
        }

        firstList.sort(Integer::compareTo);

        int result = 0;
        for (int i = 0; i < firstList.size(); i++) {
            int currentNumber = firstList.get(i);
            long occurences = secondList.stream()
                .filter(n -> n.equals(currentNumber))
                .count();

            result += (currentNumber * occurences);
        }

        return result;
    }
}
