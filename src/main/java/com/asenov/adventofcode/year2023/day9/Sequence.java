package com.asenov.adventofcode.year2023.day9;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Sequence {
    private List<Long> values;

    private List<Long> getValues(String input) {
        return Arrays.stream(input.split(" "))
            .map(Long::parseLong)
            .toList();
    }

    public Sequence(String input) {
        this.values = getValues(input);
    }

    public Long predictNextValue() {
        return predictNextValue(this.values, null);
    }

    private Long predictNextValue(List<Long> values, Long nextValue) {
        List<Long> subSequence = new ArrayList<>();
        for (int index = 0; index < values.size() - 1; index++) {
            subSequence.add(values.get(index + 1) - values.get(index));
        }

        if (subSequence.stream().allMatch(v -> v.equals(0L))) {
            return values.get(values.size() - 1);
        }

        return values.get(values.size() - 1) + predictNextValue(subSequence, nextValue);
    }
}
