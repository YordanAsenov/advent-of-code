package com.adventofcode.year2022;

import com.adventofcode.year2022.day4.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day4Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day4/first-example.txt"));
        long count = Game.solve(input);
        assertEquals(2, count);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day4/first-puzzle.txt"));
        long count = Game.solve(input);
        assertEquals(602, count);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day4/second-example.txt"));
        long count = Game.solve2(input);
        assertEquals(4, count);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day4/second-puzzle.txt"));
        long count = Game.solve2(input);
        assertEquals(891, count);
    }
}
