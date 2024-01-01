package com.asenov.adventofcode.year2023.day13;

import com.asenov.adventofcode.utils.ListUtils;
import lombok.Data;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
public class Puzzle {
    private List<Pattern> patterns;

    private List<Pattern> getPatterns(List<String> input) {
        List<Pattern> patternsList = new ArrayList<>();

        List<String> rows = new ArrayList<>();
        for (String row : input) {
            if (!row.isEmpty()) {
                rows.add(row);
            } else {
                Pattern pattern = new Pattern(rows);
                patternsList.add(pattern);
                rows = new ArrayList<>();
            }
        }

        if (!rows.isEmpty()) {
            Pattern pattern = new Pattern(rows);
            patternsList.add(pattern);
        }

        return patternsList;
    }

    public Puzzle(List<String> input) {
        this.patterns = getPatterns(input);
    }

    private static List<Integer> consecutiveRows(List<String> rows) {
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < rows.size() - 1; i++) {
            if (rows.get(i).equals(rows.get(i + 1))) {
                candidates.add(i + 1);
            }
        }
        return candidates;
    }

    private static List<Integer> validReflections(List<String> rows, Integer rowsSize) {
        List<Integer> reflections = consecutiveRows(rows);
        List<Integer> reflectedLines = new ArrayList<>();

        for (Integer reflectionIndex : reflections) {
            boolean isValid = true;
            int count = 0;

            for (int i = reflectionIndex - 1, j = reflectionIndex; i >= 0 && j < rowsSize && isValid && (count < rowsSize / 2); i--, j++) {
                isValid &= rows.get(i).equals(rows.get(j));
                count++;
            }

            if (isValid) {
                reflectedLines.add(reflectionIndex);
            }
        }

        return reflectedLines;
    }

    public Integer countReflections(List<String> rows, Integer valueToExclude) {
        List<Integer> reflectedRows = validReflections(rows, rows.size());
        reflectedRows.remove(valueToExclude);
        return reflectedRows.size() != 0 ? reflectedRows.get(0) : 0;
    }

    private static Integer differentCharacters(String first, String second) {
        if (first.length() != second.length()) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < first.toCharArray().length; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    private List<Variation> getVariations(List<String> rows) {
        List<Variation> variations = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = i + 1; j < rows.size(); j++) {
                if (differentCharacters(rows.get(i), rows.get(j)) == 1) {
                    variations.add(new Variation(i, j));
                }
            }
        }

        return variations;
    }

    private List<String> replaceVariation(List<String> rows, Variation variation) {
        List<String> variations = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            if (i == variation.getSecondIndex()) {
                variations.add(rows.get(variation.getFirstIndex()));
            } else {
                variations.add(rows.get(i));
            }
        }
        return variations;
    }

    public List<Pattern> getAlternativePatterns(List<String> rows) {
        List<Pattern> alternatives = new ArrayList<>();
        List<Variation> variations = getVariations(rows);
        for (Variation variation : variations) {
            alternatives.add(new Pattern(replaceVariation(rows, variation)));
        }
        return alternatives;
    }

    public Integer solve() {
        return this.patterns.stream()
            .map(p -> {
                List<String> columns = ListUtils.transpose(p.getRows());
                return countReflections(columns, null) +
                        countReflections(p.getRows(), null) * 100;
            })
            .reduce(0, Integer::sum);
    }

    public Integer solve2() {
        int result = 0;

        for (Pattern pattern : this.patterns) {
            Integer originalRowReflections = countReflections(pattern.getRows(), null);
            Integer originalColumnReflections = countReflections(ListUtils.transpose(pattern.getRows()), null);

            List<Pair<Integer, Integer>> reflections = new ArrayList<>();

            List<Pattern> alternativeRowPatterns = getAlternativePatterns(pattern.getRows());
            List<Pair<Integer, Integer>> rowPatterns = alternativeRowPatterns.stream()
                .distinct()
                .map(p -> {
                    var rows = countReflections(p.getRows(), originalRowReflections);
                    var cols = countReflections(ListUtils.transpose(p.getRows()), originalColumnReflections);
                    if (rows > 0) {
                        cols = 0;
                    }
                    return new Pair<>(cols, rows);
                })
                .toList();
            if (!rowPatterns.isEmpty()) {
                reflections.addAll(rowPatterns);
            }

            List<Pattern> alternativeColumnPatterns = getAlternativePatterns(ListUtils.transpose(pattern.getRows()));
            List<Pair<Integer, Integer>> columnPatterns = alternativeColumnPatterns.stream()
                .distinct()
                .map(p -> {
                    var rows = countReflections(p.getRows(), originalColumnReflections);
                    var cols = countReflections(ListUtils.transpose(p.getRows()), originalRowReflections);
                    if (rows > 0) {
                        cols = 0;
                    }
                    return new Pair<>(rows, cols);
                })
                .toList();
            if (!columnPatterns.isEmpty()) {
                reflections.addAll(columnPatterns);
            }

            Integer sum = reflections.stream()
                    .distinct()
                    .filter(r -> (r.getValue0() != null && r.getValue1() != null))
                    .map(r -> r.getValue0() + (r.getValue1() * 100))
                    .reduce(0, Integer::sum);

            result += sum;
        }

        return result;
    }
}
