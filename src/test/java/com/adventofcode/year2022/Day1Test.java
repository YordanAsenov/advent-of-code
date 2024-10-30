package com.adventofcode.year2022;

import com.adventofcode.year2022.day1.Load;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day1/first-example.txt"));
        long result = new Load(input).findBiggerInventory();
        assertEquals(24000, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day1/first-puzzle.txt"));
        long result = new Load(input).findBiggerInventory();
        assertEquals(75501, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day1/second-example.txt"));
        long result = new Load(input).findThreeBiggestInventories();
        assertEquals(45000, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day1/second-puzzle.txt"));
        double result = new Load(input).findThreeBiggestInventories();
        assertEquals(215594, result);
    }
}
