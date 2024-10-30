package com.adventofcode.year2022;

import com.adventofcode.year2022.day2.Game;
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
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day2/first-example.txt"));
        long score = Game.solve(input);
        assertEquals(15, score);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day2/first-puzzle.txt"));
        long score = Game.solve(input);
        assertEquals(14163, score);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day2/second-example.txt"));
        long score = Game.solve2(input);
        assertEquals(12, score);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day2/second-puzzle.txt"));
        long score = Game.solve2(input);
        assertEquals(12091, score);
    }
}
