package com.asenov.adventofcode.year2023.day13;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Notes {
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

    public Notes(List<String> input) {
        this.patterns = getPatterns(input);
    }

    public Integer solve() {
        return this.patterns.stream()
            .map(p -> p.countReflectedColumns() + p.countReflectedRows() * 100)
            .reduce(0, Integer::sum);
    }
}
