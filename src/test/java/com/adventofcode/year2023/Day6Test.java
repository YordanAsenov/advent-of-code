package com.adventofcode.year2023;

import com.adventofcode.year2023.day6.Simulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day6Test {
    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day6-example.txt"));

        Simulator simulator = new Simulator(input);

        assertEquals(71503L, simulator.solve());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day6-puzzle.txt"));

        Simulator simulator = new Simulator(input);

        assertEquals(23632299L, simulator.solve());
    }
}
