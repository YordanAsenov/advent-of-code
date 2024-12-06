package com.adventofcode.year2024.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5 {

    private static HashMap<Integer, List<Integer>> initOrder(List<String> input) {
        var order = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            if (row.isEmpty() || row.isBlank()) {
                break;
            }

            String[] numbers = row.split("\\|");
            var key = Integer.parseInt(numbers[0]);
            var value = Integer.parseInt(numbers[1]);
            
            List<Integer> numbersList = order.get(key);
            if (numbersList == null) {
                numbersList = new ArrayList<>();
            }
            if (!numbersList.contains(value)) {
                numbersList.add(value);
            }
            order.put(key, numbersList);
        }

        return order;
    }

    private static List<List<Integer>> initPageUpdates(List<String> input) {
        var pageUpdates = new ArrayList<List<Integer>>();

        int firstEmptyRow = input.indexOf("");
        for (int i = firstEmptyRow + 1; i < input.size(); i++) {
            String row = input.get(i);
            List<Integer> list = Arrays.stream(row.split(","))
                .map(Integer::parseInt)
                .toList();
            pageUpdates.add(list);
        }

        return pageUpdates;
    }

    private static boolean isOrdered(
        List<Integer> pageUpdate,
        HashMap<Integer, List<Integer>> order
    ) {
        var sequence = Arrays.toString(pageUpdate.toArray());

        for (Integer number : pageUpdate) {
            int index = sequence.indexOf(number.toString());

            List<Integer> numbersBefore = order.get(number);
            if (numbersBefore == null) {
                continue; // no validation required
            }

            for (Integer otherNumber : numbersBefore) {
                int otherIndex = sequence.indexOf(otherNumber.toString());
                if (otherIndex == -1) {
                    continue;
                }

                if (index > otherIndex) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int solve(List<String> input) {
        HashMap<Integer, List<Integer>> order = initOrder(input);
        List<List<Integer>> pageUpdates = initPageUpdates(input);

        return pageUpdates.stream()
            .filter(u -> isOrdered(u, order))
            .peek(u -> System.out.println("Page update: " + u + " is sorted"))
            .map(i -> i.get(i.size() / 2))
            .reduce(0, Integer::sum);
    }

    public static int solve2(List<String> input) {
        HashMap<Integer, List<Integer>> order = initOrder(input);
        List<List<Integer>> pageUpdates = initPageUpdates(input);

        List<List<Integer>> unorderedPageUpdates = pageUpdates.stream()
            .filter(u -> !isOrdered(u, order))
            .toList();



        return 0;
    }
}
