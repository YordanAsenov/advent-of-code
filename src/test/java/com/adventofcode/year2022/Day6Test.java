package com.adventofcode.year2022;

import com.adventofcode.year2022.day6.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day6Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day6/first-example.txt"));
        int index = Game.solve(input);
        assertEquals(5, index);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day6/first-puzzle.txt"));
        int index = Game.solve(input);
        assertEquals(1142, index);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day6/second-example.txt"));
        int index = Game.solve2(input);
        assertEquals(5, index);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day6/second-puzzle.txt"));
        int index = Game.solve2(input);
        assertEquals(2803, index);
    }
}
