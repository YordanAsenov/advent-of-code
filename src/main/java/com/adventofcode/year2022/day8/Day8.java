package com.adventofcode.year2022.day8;

import java.util.List;

public class Day8 {

    public static int solve(List<String> input) {
        Forest forest = new Forest(input);
        return forest.countVisibleTrees();
    }
    
    public static int solve2(List<String> input) {
        Forest forest = new Forest(input);
        return forest.getMaxScenicScore();
    }
}
