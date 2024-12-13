package com.adventofcode.year2024;

import com.adventofcode.year2024.day12.Day12;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    void solveFirstPartExampleOne() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/first-example-one.txt"));
        long result = Day12.solve(input);
        assertEquals(140, result);
    }

    @Test
    void solveFirstPartExampleTwo() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/first-example-two.txt"));
        long result = Day12.solve(input);
        assertEquals(772, result);
    }

    @Test
    void solveFirstPartExampleThree() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/first-example-three.txt"));
        long result = Day12.solve(input);
        assertEquals(1930, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/first-puzzle.txt"));
        long result = Day12.solve(input);
        assertEquals(1359028, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/second-example.txt"));
        long result = Day12.solve2(input);
        assertEquals(0, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day12/second-puzzle.txt"));
        long result = Day12.solve2(input);
        assertEquals(0, result);
    }
}
