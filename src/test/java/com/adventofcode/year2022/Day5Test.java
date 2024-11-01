package com.adventofcode.year2022;

import com.adventofcode.year2022.day5.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day5Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day5/first-example.txt"));
        var sequence = Game.solve(input);
        assertEquals("CMZ", sequence);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day5/first-puzzle.txt"));
        var sequence = Game.solve(input);
        assertEquals("TPGVQPFDH", sequence);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day5/second-example.txt"));
        var sequence = Game.solve2(input);
        assertEquals("MCD", sequence);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day5/second-puzzle.txt"));
        var sequence = Game.solve2(input);
        assertEquals("DMRDFRHHH", sequence);
    }
}
