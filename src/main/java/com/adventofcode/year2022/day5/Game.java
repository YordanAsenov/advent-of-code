package com.adventofcode.year2022.day5;

import java.util.List;

public class Game {
    public static String solve(List<String> input) {
        Harbor harbor = new Harbor(input);
        harbor.moveContainers();
        return harbor.getContentInUpperContainers();
    }
    
    public static String solve2(List<String> input) {
        Harbor harbor = new Harbor(input);
        harbor.moveContainersWithNewCrane();
        return harbor.getContentInUpperContainers();
    }
}
