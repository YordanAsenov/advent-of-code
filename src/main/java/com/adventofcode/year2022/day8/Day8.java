package com.adventofcode.year2022.day8;

import java.util.List;

public class Day8 {

    public static long solve(List<String> input) {
        Forest forest = new Forest(input);
        return forest.countVisibleTrees();
    }
    
    public static long solve2(List<String> input) {
        return 0;
    }
}
