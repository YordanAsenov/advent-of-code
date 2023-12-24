package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day13.Pattern;
import com.asenov.adventofcode.year2023.day13.Puzzle;
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
        assertEquals(0, firstPattern.countReflectedRows(null));
        assertEquals(5, firstPattern.countReflectedColumns(null, null));

        Pattern secondPattern = puzzle.getPatterns().get(1);
        assertEquals(4, secondPattern.countReflectedRows(null));
        assertEquals(0, secondPattern.countReflectedColumns(null, null));

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

        assertEquals(100, puzzle.solve());
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

        for (int i = 0; i < puzzle.getPatterns().size(); i++) {
            Pattern pattern = puzzle.getPatterns().get(i);
            Integer reflectedRows = pattern.countReflectedRows(null);
            Integer reflectedColumns = pattern.countReflectedColumns(reflectedRows, null);
            System.out.println("Pattern " + i + " | rows: " + reflectedRows + " | columns: " + reflectedColumns);

            List<Pattern> alternativePatterns = puzzle.getPatterns().get(i).getAlternativePatterns();
            for (int j = 0; j < alternativePatterns.size(); j++) {
                Integer rows = alternativePatterns.get(j).countReflectedRows(reflectedRows);
                Integer columns = alternativePatterns.get(j).countReflectedColumns(rows, reflectedColumns);
                System.out.println("Pattern " + i + " | Alternative pattern: " + j + " | rows: " + rows + " | columns: " + columns);
            }
        }

        assertEquals(400, puzzle.solve2());
    }

    @Test
    void solveSecondPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example2.txt"));

        Puzzle puzzle = new Puzzle(input);

        for (int i = 0; i < puzzle.getPatterns().size(); i++) {
            Pattern pattern = puzzle.getPatterns().get(i);
            Integer reflectedRows = pattern.countReflectedRows(null);
            Integer reflectedColumns = pattern.countReflectedColumns(reflectedRows, null);
            System.out.println("Pattern " + i + " | rows: " + reflectedRows + " | columns: " + reflectedColumns);

            List<Pattern> alternativePatterns = puzzle.getPatterns().get(i).getAlternativePatterns()
                .stream()
                .distinct()
                .toList();
            for (int j = 0; j < alternativePatterns.size(); j++) {
                Integer rows = alternativePatterns.get(j).countReflectedRows(reflectedRows);
                Integer columns = alternativePatterns.get(j).countReflectedColumns(rows, reflectedColumns);
                System.out.println("Pattern " + i + " | Alternative pattern: " + j + " | rows: " + rows + " | columns: " + columns);
            }
        }

        assertEquals(1400, puzzle.solve2());
    }

    @Test
    void solveSecondPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-example3.txt"));

        Puzzle puzzle = new Puzzle(input);

        for (int i = 0; i < puzzle.getPatterns().size(); i++) {
            Pattern pattern = puzzle.getPatterns().get(i);
            Integer reflectedRows = pattern.countReflectedRows(null);
            Integer reflectedColumns = pattern.countReflectedColumns(reflectedRows, null);
            System.out.println("Pattern " + i + " | rows: " + reflectedRows + " | columns: " + reflectedColumns);

            List<Pattern> alternativePatterns = puzzle.getPatterns().get(i).getAlternativePatterns()
                .stream()
                .distinct()
                .toList();
            for (int j = 0; j < alternativePatterns.size(); j++) {
                Integer rows = alternativePatterns.get(j).countReflectedRows(null);
                Integer columns = alternativePatterns.get(j).countReflectedColumns(rows, reflectedColumns);
                System.out.println("Pattern " + i + " | Alternative pattern: " + j + " | rows: " + rows + " | columns: " + columns);
            }
        }

        assertEquals(400, puzzle.solve2());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day13-puzzle.txt"));

        Puzzle puzzle = new Puzzle(input);

        for (int i = 0; i < puzzle.getPatterns().size(); i++) {
            Pattern pattern = puzzle.getPatterns().get(i);
            Integer reflectedRows = pattern.countReflectedRows(null);
            Integer reflectedColumns = pattern.countReflectedColumns(reflectedRows, null);
            System.out.println("Pattern " + i + " | rows: " + reflectedRows + " | columns: " + reflectedColumns);

            List<Pattern> alternativePatterns = puzzle.getPatterns().get(i).getAlternativePatterns()
                    .stream()
                    .distinct()
                    .toList();
            for (int j = 0; j < alternativePatterns.size(); j++) {
                Integer rows = alternativePatterns.get(j).countReflectedRows(null);
                Integer columns = alternativePatterns.get(j).countReflectedColumns(rows, reflectedColumns);
                System.out.println("Pattern " + i + " | Alternative pattern: " + j + " | rows: " + rows + " | columns: " + columns);
            }
        }

        assertEquals(30842, puzzle.solve2());
    }
}
