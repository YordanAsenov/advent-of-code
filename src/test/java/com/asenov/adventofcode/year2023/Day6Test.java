package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day6.Race;
import com.asenov.adventofcode.year2023.day6.Simulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day6Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day6-example.txt"));

        Simulator simulator = new Simulator(input);
        Race race1 = simulator.getRaces().get(0);
        Race race2 = simulator.getRaces().get(1);
        Race race3 = simulator.getRaces().get(2);

        assertEquals(3, simulator.getRaces().size());
        assertEquals(4, race1.countWaysToBeatTheRecord());
        assertEquals(8, race2.countWaysToBeatTheRecord());
        assertEquals(9, race3.countWaysToBeatTheRecord());
        assertEquals(288, simulator.solve());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day6-puzzle.txt"));

        Simulator simulator = new Simulator(input);

        assertEquals(505494, simulator.solve());
    }

    @Test
    void solveSecondPartExample() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/dayX-example.txt"));
        assertTrue(true);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/dayX-puzzle.txt"));
        assertTrue(true);
    }
}
