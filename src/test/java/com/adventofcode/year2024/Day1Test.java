package com.adventofcode.year2024;

import com.adventofcode.year2024.day1.Day1;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day1Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day1/first-example.txt"));
        int result = Day1.solve(input);
        assertEquals(11, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day1/first-puzzle.txt"));
        int result = Day1.solve(input);
        assertEquals(1938424, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day1/second-example.txt"));
        int result = Day1.solve2(input);
        assertEquals(31, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day1/second-puzzle.txt"));
        int result = Day1.solve2(input);
        assertEquals(31, result);
    }
}
