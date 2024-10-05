package com.adventofcode.year2023;

import com.adventofcode.year2023.day2.Day2;
import com.adventofcode.year2023.day2.Game;
import com.adventofcode.year2023.day2.GameConfiguration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day2Test {
    @Test
    void gameDetails() {
        // String input = "Game 1: 7 blue, 4 red, 11 green; 2 red, 2 blue, 7 green; 2 red, 13 blue, 8 green; 18 blue, 7 green, 5 red";
        String input = "Game 89: 1 blue, 6 red, 12 green; 9 red, 13 green, 3 blue; 11 green, 6 red, 3 blue";
        Game game = Day2.getGameDetails(input);
        assertEquals(89, game.getId());
    }

    @Test
    void gameFits() {
        GameConfiguration limits = new GameConfiguration(12, 13, 14);
        GameConfiguration firstSet = new GameConfiguration(4, 11, 7);
        assertTrue(firstSet.fits(limits));
    }

    @Test
    void solveExample() throws IOException {
        GameConfiguration limits = new GameConfiguration(12, 13, 14);
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day2-example.txt"));

        int result = Day2.solve(input, limits);

        assertEquals(8, result);
    }

    @Test
    void solvePuzzle() throws IOException {
        GameConfiguration limits = new GameConfiguration(12, 13, 14);
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day2-puzzle.txt"));

        int result = Day2.solve(input, limits);

        assertEquals(2476, result);
    }

    @Test
    void solvePartTwo() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day2-puzzle.txt"));
        int power = Day2.solvePartTwo(input);
        assertEquals(2286, power);
    }
}
