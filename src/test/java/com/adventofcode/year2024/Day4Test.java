package com.adventofcode.year2024;

import com.adventofcode.year2024.day4.Day4;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day4/first-example.txt"));
        int result = Day4.solve(input).size();
        assertEquals(18, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day4/first-puzzle.txt"));
        int result = Day4.solve(input).size();
        assertEquals(2718, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day4/second-example.txt"));
        long result = Day4.solve2(input);
        assertEquals(9, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day4/second-puzzle.txt"));
        long result = Day4.solve2(input);
        assertEquals(2046, result);
    }
}
