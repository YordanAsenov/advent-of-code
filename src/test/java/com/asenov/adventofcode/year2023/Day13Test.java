package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day13.Notes;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day13Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example.txt"));

        Notes notes = new Notes(input);

        assertEquals(405, notes.solve());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-puzzle.txt"));

        Notes notes = new Notes(input);

        assertEquals(41859, notes.solve());
    }

    @Test
    void solveSecondPartExample() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-example.txt"));
        assertTrue(true);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-puzzle.txt"));
        assertTrue(true);
    }
}
