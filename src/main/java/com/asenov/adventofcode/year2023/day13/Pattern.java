package com.asenov.adventofcode.year2023.day13;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class Pattern {
    private List<String> rows;

    public Pattern(List<String> rows) {
        this.rows = rows;
    }

    private List<String> transpose(List<String> rows) {
        List<String> result = new ArrayList<>();
        final int columns = rows.get(0).length();
        for (int i = 0; i < columns; i++) {
            StringBuilder sb = new StringBuilder();
            for (String row : rows) {
                sb.append(row.charAt(i));
            }
            result.add(sb.toString());
        }
        return result;
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

    private static List<Integer> validReflections(List<String> rows, Integer rowsSize, Integer excludedIndex) {
        List<Integer> reflections = consecutiveRows(rows);

        for (Integer reflectionIndex : reflections) {
            boolean isValid = true;
            int count = 0;

            for (int i = reflectionIndex - 1, j = reflectionIndex; i >= 0 && j < rowsSize && isValid && (count < rowsSize / 2); i--, j++) {
                isValid &= rows.get(i).equals(rows.get(j));
                count++;
            }

            if (isValid && !reflectionIndex.equals(excludedIndex)) {
                return List.of(reflectionIndex);
            }
        }

        return new ArrayList<>();
    }

    public Integer countReflectedRows(Integer excludedIndex) {
        List<Integer> reflectedRows = validReflections(this.rows, this.rows.size(), excludedIndex);
        if (reflectedRows.size() != 1) //) || reflectedRows.get(0).equals(excludedIndex))
            return 0;
        return reflectedRows.get(0);
    }

    public Integer countReflectedColumns(Integer excludedRowIndex, Integer excludedColumnIndex) {
//        if (countReflectedRows(excludedRowIndex) > 0) {
//            return 0;
//        }

        List<String> columns = transpose(this.rows);
        List<Integer> reflectedColumns = validReflections(columns, columns.size(), excludedColumnIndex);
        if (reflectedColumns.size() != 1) // || reflectedColumns.get(0).equals(excludedColumnIndex))
            return 0;
        return reflectedColumns.get(0);
    }

    public static Integer differentCharacters(String first, String second) {
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
                 if (Pattern.differentCharacters(rows.get(i), rows.get(j)) == 1) {
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

    public List<Pattern> getAlternativePatterns() {
        List<Pattern> alternatives = new ArrayList<>();
        List<Variation> variations = getVariations(this.rows);
        for (Variation variation : variations) {
            alternatives.add(new Pattern(replaceVariation(this.rows, variation)));
        }
        return alternatives;
    }
}
