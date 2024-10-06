package com.adventofcode.year2015;

import com.adventofcode.year2015.day2.Day2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day2Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2015/day2/first-example.txt"));
        int result = Day2.solve(input);
        assertEquals(101, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2015/day2/first-puzzle.txt"));
        int result = Day2.solve(input);
        assertEquals(1586300, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2015/day2/second-example.txt"));
        int result = Day2.solve2(input);
        assertEquals(48, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2015/day2/second-puzzle.txt"));
        int result = Day2.solve2(input);
        assertEquals(48, result);
    }
}
