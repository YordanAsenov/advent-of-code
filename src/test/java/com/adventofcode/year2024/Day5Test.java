package com.adventofcode.year2024;

import com.adventofcode.year2024.day5.Day5;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day5/first-example.txt"));
        int result = Day5.solve(input);
        assertEquals(143, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day5/first-puzzle.txt"));
        int result = Day5.solve(input);
        assertEquals(6612, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day5/second-example.txt"));
        int result = Day5.solve2(input);
        assertEquals(123, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day5/second-puzzle.txt"));
        int result = Day5.solve2(input);
        assertEquals(4944, result);
    }
}
