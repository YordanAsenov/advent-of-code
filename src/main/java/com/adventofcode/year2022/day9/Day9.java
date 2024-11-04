package com.adventofcode.year2022.day9;

import java.util.List;

public class Day9 {
    public static int solve(List<String> input) {
        Game game = new Game(input, 2);
        game.move();
        return game.getVisitedPositions(1);
    }
    
    public static int solve2(List<String> input) {
        Game game = new Game(input, 10);
        game.move();
        return game.getVisitedPositions(9);
    }
}
