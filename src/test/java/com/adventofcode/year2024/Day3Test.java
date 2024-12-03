package com.adventofcode.year2024;

import com.adventofcode.year2024.day3.Day3;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day3/first-example.txt"));
        int result = Day3.solve(input);
        assertEquals(161, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day3/first-puzzle.txt"));
        int result = Day3.solve(input);
        assertEquals(2, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day3/second-example.txt"));
        long result = Day3.solve2(input);
        assertEquals(48, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day3/second-puzzle.txt"));
        int result = Day3.solve2(input);
        assertEquals(71668682, result);
    }
}
