package com.asenov.adventofcode.year2023.day5;

import lombok.Data;

@Data
public class Range {
    private final Long to;
    private final Long from;
    private final Long size;

    public Range(String row) {
        String[] range = row.split(" ");
        this.to = Long.parseLong(range[0]);
        this.from = Long.parseLong(range[1]);
        this.size = Long.parseLong(range[2]);
    }

    public boolean contains(Long sourceValue) {
        return sourceValue >= from &&
            sourceValue < (from + size);
    }

    public Long offset() {
        return from - to;
    }
}
