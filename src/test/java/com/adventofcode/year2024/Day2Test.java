package com.adventofcode.year2024;

import com.adventofcode.year2024.day2.Day2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day2/first-example.txt"));
        int result = Day2.solve(input);
        assertEquals(2, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day2/first-puzzle.txt"));
        int result = Day2.solve(input);
        assertEquals(663, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day2/second-example.txt"));
        int result = Day2.solve2(input);
        assertEquals(4, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day2/second-puzzle.txt"));
        int result = Day2.solve2(input);
        assertEquals(692, result);
    }
}
