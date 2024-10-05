package com.adventofcode.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static List<String> transpose(List<String> rows) {
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
}
