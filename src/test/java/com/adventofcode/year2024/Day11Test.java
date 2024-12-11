package com.adventofcode.year2024;

import com.adventofcode.year2024.day11.Day11;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    @Test
    void solveFirstPartExampleOne() {
        String input = "0 1 10 99 999";
        long result = Day11.solve(input, 1);
        assertEquals(7, result);
    }

    @Test
    void solveFirstPartExampleTwo() {
        String input = "125 17";
        long result = Day11.solve(input, 25);
        assertEquals(55312, result);
    }

    @Test
    void solveFirstPartPuzzle() {
        String input = "70949 6183 4 3825336 613971 0 15 182";
        long result = Day11.solve(input, 25);
        assertEquals(185205, result);
    }

    @Test
    void solveSecondPartPuzzle() {
        String input = "70949 6183 4 3825336 613971 0 15 182";
        long result = Day11.solve(input, 75);
        assertEquals(185205, result);
    }
}
