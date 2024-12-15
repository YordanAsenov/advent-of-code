package com.adventofcode.year2024;

import com.adventofcode.year2024.day13.Day13;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day13/first-example.txt"));
        long result = Day13.solve(input);
        assertEquals(480, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day13/first-puzzle.txt"));
        long result = Day13.solve(input);
        assertEquals(36838, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day13/second-example.txt"));
        int result = Day13.solve2(input);
        assertEquals(0, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day13/second-puzzle.txt"));
        int result = Day13.solve2(input);
        assertEquals(0, result);
    }
}
