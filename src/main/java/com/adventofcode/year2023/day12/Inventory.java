package com.adventofcode.year2023.day12;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private final List<Composition> compositions;

    private List<Composition> initCompositions(List<String> input, Integer repeat) {
        return input.stream()
            .map(i -> new Composition(i, repeat))
            .toList();
    }

    public Inventory(List<String> input, Integer repeat) {
        this.compositions = initCompositions(input, repeat);
    }

    public Long solve() {
        return this.compositions.stream()
            .map(Composition::countArrangements)
            .reduce(Long::sum)
            .orElse(0L);
    }
}
