package com.asenov.adventofcode.year2023.day12;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private final List<Composition> compositions;

    private List<Composition> initCompositions(List<String> input) {
        return input.stream()
            .map(Composition::new)
            .toList();
    }

    public Inventory(List<String> input) {
        this.compositions = initCompositions(input);
    }

    public Integer getMaxUnknownValues() {
        return this.compositions.stream()
            .map(Composition::countUnknownValues)
            .max(Integer::compareTo)
            .orElse(0);
    }

    public Integer getCombinationsSum() {
        return this.compositions.stream()
            .map(Composition::getCombinations)
            .reduce(Integer::sum)
            .orElse(0);
    }
}
