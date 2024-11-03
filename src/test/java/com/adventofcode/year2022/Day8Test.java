package com.adventofcode.year2022;

import com.adventofcode.year2022.day8.Day8;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day8/first-example.txt"));
        int count = Day8.solve(input);
        assertEquals(21, count);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day8/first-puzzle.txt"));
        int count = Day8.solve(input);
        assertEquals(1708, count);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day8/second-example.txt"));
        long count = Day8.solve2(input);
        assertEquals(8, count);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day8/second-puzzle.txt"));
        long count = Day8.solve2(input);
        assertEquals(504000, count);
    }
}
