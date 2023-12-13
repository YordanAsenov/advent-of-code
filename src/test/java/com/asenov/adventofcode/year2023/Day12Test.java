package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day12.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-example1.txt"));

        Inventory inventory = new Inventory(input);

        assertEquals(6, inventory.getCompositions().size());
        assertEquals(3, inventory.getCompositions().get(0).getContiguousDamagedGroups().size());

        assertTrue(inventory.getCompositions().get(0).isValid());
        assertTrue(inventory.getCompositions().get(1).isValid());
        assertTrue(inventory.getCompositions().get(2).isValid());
        assertTrue(inventory.getCompositions().get(3).isValid());
        assertTrue(inventory.getCompositions().get(4).isValid());
        assertTrue(inventory.getCompositions().get(5).isValid());

        assertEquals(3, inventory.getCompositions().get(0).extractGroups().size());
        assertEquals(3, inventory.getCompositions().get(1).extractGroups().size());
        assertEquals(4, inventory.getCompositions().get(2).extractGroups().size());
        assertEquals(3, inventory.getCompositions().get(3).extractGroups().size());
        assertEquals(3, inventory.getCompositions().get(4).extractGroups().size());
        assertEquals(3, inventory.getCompositions().get(5).extractGroups().size());
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-example2.txt"));

        Inventory inventory = new Inventory(input);

        assertEquals(9, inventory.getMaxUnknownValues());

        assertEquals(1, inventory.getCompositions().get(0).getCombinations());
        assertEquals(4, inventory.getCompositions().get(1).getCombinations());
        assertEquals(1, inventory.getCompositions().get(2).getCombinations());
        assertEquals(1, inventory.getCompositions().get(3).getCombinations());
        assertEquals(4, inventory.getCompositions().get(4).getCombinations());
        assertEquals(10, inventory.getCompositions().get(5).getCombinations());

        assertEquals(21, inventory.getCombinationsSum());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-puzzle.txt"));

        Inventory inventory = new Inventory(input);

        assertEquals(8075, inventory.getCombinationsSum());
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
