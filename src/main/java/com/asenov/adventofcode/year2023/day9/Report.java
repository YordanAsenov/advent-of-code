package com.asenov.adventofcode.year2023.day9;

import lombok.Data;

import java.util.List;

@Data
public class Report {
    private List<Sequence> sequences;

    private List<Sequence> getSequences(List<String> input) {
        return input.stream()
            .map(Sequence::new)
            .toList();
    }

    public Report(List<String> input) {
        this.sequences = getSequences(input);
    }

    public Long predictNext() {
        return this.sequences.stream()
            .map(Sequence::predictNextValue)
            .reduce(0L, Long::sum);
    }

    public Long predictPrevious() {
        return this.sequences.stream()
            .map(Sequence::predictPreviousValue)
            .reduce(0L, Long::sum);
    }
}
