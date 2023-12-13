package com.asenov.adventofcode.year2023.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Composition {
    private String value;

    private String conditionRecord;
    private List<Integer> contiguousDamagedGroups;

    public Integer countUnknownValues() {
        return ((Long) this.value.chars().filter(c -> c == '?').count()).intValue();
    }

    private List<Integer> initGroups(String input) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .toList();
    }

    public Composition(String value) {
        this.value = value;
        String[] row = value.split(" ");
        this.conditionRecord = row[0];
        this.contiguousDamagedGroups = initGroups(row[1]);
    }

    public Composition(String conditionRecord, List<Integer> contiguousDamagedGroups) {
        this.conditionRecord = conditionRecord;
        this.contiguousDamagedGroups = contiguousDamagedGroups;
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

    public int getCombinations() {
        return getCombinations(this.conditionRecord, this.countUnknownValues(), this.contiguousDamagedGroups);
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

    private static Map<Integer, String> buildUnknownValuesCombinations(Integer possibleCombinations) {
        Map<Integer, String> combinations = new HashMap<>();

        String maxDigits = Integer.toBinaryString(possibleCombinations - 1);

        for (int i = 0; i < possibleCombinations; i++) {
            String combination = Integer.toBinaryString(i);
            combination = String.format("%" + maxDigits.length() + "s", combination).replace(" ", "0");
            combination = combination.replace("0", ".");
            combination = combination.replace("1", "#");
            combinations.put(i, combination);
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

    private static int getCombinations(String conditionRecord, Integer unknownValues, List<Integer> contiguousDamagedGroups) {
        int count = 0;
        int possibleCombinations = ((Double) Math.pow(2, unknownValues)).intValue();
        Map<Integer, String> unknownValuesCombinations = buildUnknownValuesCombinations(possibleCombinations);

        Map<Integer, String> validCombinations = new HashMap<>();
        for (int i = 0; i < possibleCombinations; i++) {
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
