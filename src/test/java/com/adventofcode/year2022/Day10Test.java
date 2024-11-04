package com.adventofcode.year2022;

import com.adventofcode.year2022.day10.Day10;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day10/first-example.txt"));
        int count = Day10.solve(input);
        assertEquals(1, count);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day10/first-puzzle.txt"));
        int count = Day10.solve(input);
        assertEquals(1, count);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day10/second-example.txt"));
        int count = Day10.solve2(input);
        assertEquals(1, count);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day10/second-puzzle.txt"));
        int count = Day10.solve2(input);
        assertEquals(1, count);
    }
}
