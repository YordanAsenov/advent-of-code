package com.asenov.adventofcode.year2023.day13;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Pattern {
    private List<String> rows;

    public Pattern(List<String> rows) {
        this.rows = rows;
    }

    private static List<String> transpose(List<String> rows) {
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

    private static List<Integer> findReflectedRows(List<String> rows) {
        List<Integer> matchingRows = new ArrayList<>();
        boolean found;

        for (int i = 1; i < rows.size(); i++) {
            found = true;
            int length = Math.min(i, rows.size() - i);

            for (int j = 0; j < length && found; j++) {
                found &= rows.get(i - j - 1).equals(rows.get(i + j));
            }

            if (found) {
                matchingRows.add(i);
            }
        }

        return matchingRows;
    }

    public Integer countReflectedRows() {
        List<Integer> reflectedRows = findReflectedRows(this.rows);
        if (reflectedRows.isEmpty())
            return 0;
        return reflectedRows.get(0);
    }

    public Integer countReflectedColumns() {
        List<Integer> reflectedColumns = findReflectedRows(transpose(this.rows));
        if (reflectedColumns.isEmpty())
            return 0;
        return reflectedColumns.get(0);
    }
}
