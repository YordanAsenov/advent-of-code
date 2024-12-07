package com.adventofcode.year2024;

import com.adventofcode.year2024.day7.Day7;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day7/first-example.txt"));
        long result = Day7.solve(input);
        assertEquals(3749, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day7/first-puzzle.txt"));
        long result = Day7.solve(input);
        assertEquals(5030892084481L, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day7/second-example.txt"));
        long result = Day7.solve2(input);
        assertEquals(11387, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day7/second-puzzle.txt"));
        long result = Day7.solve2(input);
        assertEquals(91377448644679L, result);
    }
}
