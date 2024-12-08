package com.adventofcode.year2024;

import com.adventofcode.year2024.day8.Day8;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day8/first-example.txt"));
        int result = Day8.solve(input);
        assertEquals(14, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day8/first-puzzle.txt"));
        int result = Day8.solve(input);
        assertEquals(254, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day8/second-example.txt"));
        int result = Day8.solve2(input);
        assertEquals(34, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day8/second-puzzle.txt"));
        int result = Day8.solve2(input);
        assertEquals(951, result);
    }
}
