package com.adventofcode.year2022;

import com.adventofcode.year2022.day7.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day7Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day7/first-example.txt"));
        long size = Game.solve(input);
        assertEquals(95437, size);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day7/first-puzzle.txt"));
        long size = Game.solve(input);
        assertEquals(1334506, size);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day7/second-example.txt"));
        long size = Game.solve2(input);
        assertEquals(24933642, size);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2022/day7/second-puzzle.txt"));
        long size = Game.solve2(input);
        assertEquals(1334506, size);
    }
}
