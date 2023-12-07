package com.asenov.adventofcode.year2023.day6;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class Simulator {
    private List<Race> races;

    public Simulator(List<String> input) {
        List<String> durations = Arrays.stream(input.get(0)
            .substring(5).trim()
            .split(" "))
            .filter(n -> !n.isEmpty() && !n.isBlank())
            .toList();

        List<String> distances = Arrays.stream(input.get(1)
            .substring(9).trim()
            .split(" "))
            .filter(n -> !n.isEmpty() && !n.isBlank())
            .toList();

        this.races = IntStream.range(0, durations.size())
            .boxed()
            .map(index -> new Race(durations.get(index), distances.get(index)))
            .toList();
    }

    public Integer solve() {
        return this.races.stream()
            .map(Race::countWaysToBeatTheRecord)
            .reduce(1, (a,b) -> a * b);
    }
}
