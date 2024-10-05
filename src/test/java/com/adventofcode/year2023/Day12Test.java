package com.adventofcode.year2023;

import com.adventofcode.year2023.day12.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    @Test
    void testNoSprings() {
        String input = "";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(0, inventory.solve());
    }

    @Test
    void testNoDamagedSprings() {
        String input = ". 0";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(0, inventory.solve());
    }

    @Test
    void testOneDamagedSpring() {
        String input = "# 1";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testTwoDamagedSprings() {
        String input = "## 2";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testTwoSeparatedDamagedSprings() {
        String input = "#.# 1,1";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testTwoUnknownSprings() {
        String input = "??.# 1,1";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(2, inventory.solve());
    }

    @Test
    void testThreeSeparatedUnknownSprings() {
        String input = "???.### 1,1,3";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testLastUnknownSpring() {
        String input = "##? 3";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testSevenAlternatedUnknownSprings() {
        String input = "?#?#?#?#?#?#?#? 1,3,1,6";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testFourConsecutiveUnknownSprings() {
        String input = "????.#...#... 4,1,1";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(1, inventory.solve());
    }

    @Test
    void testMultipleArrangements1() {
        String input = "????.######..#####. 1,6,5";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(4, inventory.solve());
    }

    @Test
    void testMultipleArrangements2() {
        String input = "?###???????? 3,2,1";
        Inventory inventory = new Inventory(List.of(input), 1);
        assertEquals(10, inventory.solve());
    }

    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-example.txt"));
        Inventory inventory = new Inventory(input, 1);
        assertEquals(21, inventory.solve());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-puzzle.txt"));
        Inventory inventory = new Inventory(input, 1);
        assertEquals(8075, inventory.solve());
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-example.txt"));
        Inventory inventory = new Inventory(input, 5);
        assertEquals(525152, inventory.solve());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day12-puzzle.txt"));
        Inventory inventory = new Inventory(input, 5);
        assertEquals(4232520187524L, inventory.solve());
    }
}
