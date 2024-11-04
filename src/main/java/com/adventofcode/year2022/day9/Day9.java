package com.adventofcode.year2022.day9;

import java.util.List;

public class Day9 {
    public static int solve(List<String> input) {
        Game game = new Game(input);
        game.move();
        return game.getVisitedPositions();
    }
    
    public static int solve2(List<String> input) {
        return 0;
    }
}
