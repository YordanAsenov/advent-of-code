package com.asenov.adventofcode.year2023.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Composition {
    private String value;

    private String conditionRecord;
    private List<Integer> contiguousDamagedGroups;

    private static final Integer UNFOLD_TIMES = 5;

    private List<Integer> initGroups(String input) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .toList();
    }

    private static String unfold(String value, Integer times, String delimiter) {
        return IntStream.range(0, times)
            .mapToObj(i -> value)
            .collect(Collectors.joining(delimiter));
    }

    public Composition(String value) {
        this.value = value;
        String[] row = value.split(" ");
        this.conditionRecord = unfold(row[0], UNFOLD_TIMES, "?");
        this.contiguousDamagedGroups = initGroups(unfold(row[1], UNFOLD_TIMES, ","));

    }

    public boolean isValid() {
        return isValid(this.value, this.contiguousDamagedGroups);
    }

    private static boolean isValid(String value, List<Integer> damagedContiguousGroups) {
        long operational = value.chars().filter(c -> c == '.').count();
        long damaged = value.chars().filter(c -> c == '#').count();

        int knownDamaged = damagedContiguousGroups.stream().reduce(0, Integer::sum);
        int minimumOperationalRequired = damagedContiguousGroups.size() - 1;

        List<Integer> contiguousGroups = extractGroups(value);
        boolean groupsMatch = IntStream.range(0, damagedContiguousGroups.size())
            .allMatch(i -> i < contiguousGroups.size() &&
                damagedContiguousGroups.get(i).equals(contiguousGroups.get(i)));

        return operational >= minimumOperationalRequired &&
            damaged == knownDamaged &&
            groupsMatch;
    }


    public Integer countUnknownValues() {
        return ((Long) this.value.chars().filter(c -> c == '?').count()).intValue();
    }

    public double getCombinations() {
        return getCombinations(
            this.conditionRecord,
            this.countUnknownValues() * UNFOLD_TIMES * (UNFOLD_TIMES - 1),
            this.contiguousDamagedGroups
        );
    }

    public List<Integer> extractGroups() {
        return extractGroups(this.value);
    }

    private static List<Integer> extractGroups(String value) {
        String[] row = value.split(" ");
        return Arrays.stream(row[0].split("\\."))
            .filter(s -> !s.isEmpty() && !s.isBlank())
            .map(s -> s.chars().filter(c -> c == '#').count())
            .map(Long::intValue)
            .toList();
    }

    private static Map<Double, String> buildUnknownValuesCombinations(
        Double possibleCombinations,
        Long missingDamagedValues
    ) {
        Map<Double, String> combinations = new HashMap<>();

        String maxDigits = Integer.toBinaryString(possibleCombinations.intValue() - 1);

        for (Double i = 0D; i < possibleCombinations; i++) {
            String combination = Integer.toBinaryString(i.intValue());
            combination = String.format("%" + maxDigits.length() + "s", combination).replace(" ", "0");
            combination = combination.replace("0", ".");
            combination = combination.replace("1", "#");

            if (combination.chars().filter(c -> c == '#').count() == missingDamagedValues) {
                combinations.put(i, combination);
            }
        }

        return combinations;
    }

    private static String replaceUnknownValues(String conditionRecord, String combinationOfUnknownValues) {
        String tmp = conditionRecord;
        char[] charArray = combinationOfUnknownValues.toCharArray();
        for (char c : charArray) {
            int index = tmp.indexOf("?");
            tmp = tmp.substring(0, index) + c + tmp.substring(index + 1);
        }

        return tmp;
    }

    private static double getCombinations(
        String conditionRecord,
        Integer unknownValues,
        List<Integer> contiguousDamagedGroups
    ) {
        double count = 0;
        double possibleCombinations = Math.pow(2, unknownValues);

        long damaged = conditionRecord.chars().filter(c -> c == '#').count();
        int knownDamaged = contiguousDamagedGroups.stream().reduce(0, Integer::sum);
        long missingDamaged = knownDamaged - damaged;

        Map<Double, String> unknownValuesCombinations = buildUnknownValuesCombinations(possibleCombinations, missingDamaged);

        Map<Double, String> validCombinations = new HashMap<>();
        for (double i = 0; i < possibleCombinations; i++) {
            String unknownValuesCombination = unknownValuesCombinations.get(i);
            String result = replaceUnknownValues(conditionRecord, unknownValuesCombination);
            if (isValid(result, contiguousDamagedGroups)) {
                count++;
                validCombinations.put(i, result);
            }
        }

        return count;
    }
}
