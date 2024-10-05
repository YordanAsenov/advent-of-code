package com.adventofcode.year2023.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Data
public class Composition {
    private String conditionRecord;
    private List<Integer> damagedGroups = new ArrayList<>();

    private static String unfold(String value, Integer times, String delimiter) {
        return IntStream.range(0, times)
            .mapToObj(i -> value)
            .collect(Collectors.joining(delimiter));
    }

    private static List<Integer> initGroups(String input) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .toList();
    }

    public Composition(String input, Integer repeat) {
        if (input.length() > 1) {
            String[] row = input.split(" ");
            this.conditionRecord = unfold(row[0], repeat, "?");
            this.damagedGroups = initGroups(unfold(row[1], repeat, ","));
        }
    }

    public Long countArrangements() {
        final Map<Key, Long> cache = new HashMap<>();
        return compute(this.conditionRecord, new ArrayList<>(this.damagedGroups), cache);
    }

    public Long compute(String pattern, List<Integer> damagedGroups, Map<Key, Long> cache) {
        Key key = new Key(pattern, damagedGroups);
        if (!cache.containsKey(key)) {
            cache.put(key, process(pattern, new ArrayList<>(damagedGroups), cache));
        }
        return cache.get(key);
    }

    public Long processWorking(String pattern, List<Integer> damagedGroups, Map<Key, Long> cache) {
        return compute(pattern.substring(1), new ArrayList<>(damagedGroups), cache);
    }

    public Long processUnknown(String pattern, List<Integer> damagedGroups, Map<Key, Long> cache) {
        return compute("." + pattern.substring(1), new ArrayList<>(damagedGroups), cache) +
            compute("#" + pattern.substring(1), new ArrayList<>(damagedGroups), cache);
    }

    public Long processDamaged(String pattern, List<Integer> damagedGroups, Map<Key, Long> cache) {
        if (damagedGroups.isEmpty()) {
            return 0L;
        }

        Integer damagedSprings = damagedGroups.get(0);
        damagedGroups.removeFirst();

        char[] inputArray = pattern.toCharArray();
        int potentiallyDamaged = (int) IntStream.range(0, inputArray.length)
            .map(i -> inputArray[i])
            .takeWhile(c -> c == '#' || c == '?')
            .count();

        if (potentiallyDamaged < damagedSprings) {
            return 0L;
        } else if (pattern.length() == damagedSprings) {
            return compute("", damagedGroups, cache);
        } else if (pattern.charAt(damagedSprings) == '#') {
            return 0L;
        } else {
            return compute(pattern.substring(damagedSprings + 1), new ArrayList<>(damagedGroups), cache);
        }
    }

    public Long process(String pattern, List<Integer> damagedGroups, Map<Key, Long> cache) {
        if (pattern == null) {
            return 0L;
        }

        char spring = pattern.length() > 0 ? pattern.charAt(0) : ' ';
        return switch (spring) {
            case '.' -> processWorking(pattern, damagedGroups, cache);
            case '?' -> processUnknown(pattern, damagedGroups, cache);
            case '#' -> processDamaged(pattern, damagedGroups, cache);
            default -> damagedGroups.isEmpty() ? 1L : 0L;
        };
    }
}
