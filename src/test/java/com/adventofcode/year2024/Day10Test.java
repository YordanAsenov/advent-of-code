package com.adventofcode.year2024;

import com.adventofcode.year2024.day10.Day10;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/example-1.txt"));
        long result = Day10.solve(input);
        assertEquals(2, result);
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/example-2.txt"));
        long result = Day10.solve(input);
        assertEquals(4, result);
    }

    @Test
    void solveFirstPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/example-3.txt"));
        long result = Day10.solve(input);
        assertEquals(3, result);
    }

    @Test
    void solveFirstPartExample4() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/example-4.txt"));
        long result = Day10.solve(input);
        assertEquals(36, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/first-puzzle.txt"));
        long result = Day10.solve(input);
        assertEquals(733, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/second-example.txt"));
        long result = Day10.solve2(input);
        assertEquals(81, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day10/second-puzzle.txt"));
        long result = Day10.solve2(input);
        assertEquals(1514, result);
    }
}
