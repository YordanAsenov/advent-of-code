package com.adventofcode.year2023;

import com.adventofcode.utils.ListUtils;
import com.adventofcode.year2023.day13.Pattern;
import com.adventofcode.year2023.day13.Puzzle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {
    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example1.txt"));

        Puzzle puzzle = new Puzzle(input);

        Pattern firstPattern = puzzle.getPatterns().get(0);
        assertEquals(0, puzzle.countReflections(firstPattern.getRows(), null));
        assertEquals(5, puzzle.countReflections(ListUtils.transpose(firstPattern.getRows()), null));

        Pattern secondPattern = puzzle.getPatterns().get(1);
        assertEquals(4, puzzle.countReflections(secondPattern.getRows(), null));
        assertEquals(0, puzzle.countReflections(ListUtils.transpose(secondPattern.getRows()), null));

        assertEquals(405, puzzle.solve());
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example2.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(709, puzzle.solve());
    }

    @Test
    void solveFirstPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example3.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(1100, puzzle.solve());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-puzzle.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(41859, puzzle.solve());
    }

    @Test
    void solveSecondPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example1.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(400, puzzle.solve2());
    }

    @Test
    void solveSecondPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example2.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(1400, puzzle.solve2());
    }

    @Test
    void solveSecondPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example3.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(5, puzzle.solve2());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-puzzle.txt"));

        Puzzle puzzle = new Puzzle(input);

        assertEquals(30842, puzzle.solve2());
    }
}
