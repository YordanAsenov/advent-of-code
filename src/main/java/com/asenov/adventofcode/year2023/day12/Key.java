package com.asenov.adventofcode.year2023.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Key {
    private String pattern;
    private List<Integer> damagedGroups;
}
