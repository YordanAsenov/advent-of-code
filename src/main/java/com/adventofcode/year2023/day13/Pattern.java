package com.adventofcode.year2023.day13;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Pattern {
    private List<String> rows;

    public Pattern(List<String> rows) {
        this.rows = rows;
    }
}
