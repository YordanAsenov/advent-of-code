package com.adventofcode.year2023;

import com.adventofcode.year2023.day9.Report;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day9Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day9-example.txt"));

        Report report = new Report(input);

        assertEquals(3, report.getSequences().size());
        assertEquals(6, report.getSequences().get(0).getValues().size());
        assertEquals(18L, report.getSequences().get(0).predictNextValue());
        assertEquals(28L, report.getSequences().get(1).predictNextValue());
        assertEquals(68L, report.getSequences().get(2).predictNextValue());
        assertEquals(114L, report.predictNext());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day9-puzzle.txt"));

        Report report = new Report(input);

        assertEquals(1834108701L, report.predictNext());
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day9-example.txt"));

        Report report = new Report(input);

        assertEquals(3, report.getSequences().size());
        assertEquals(6, report.getSequences().get(0).getValues().size());
        assertEquals(-3L, report.getSequences().get(0).predictPreviousValue());
        assertEquals(0L, report.getSequences().get(1).predictPreviousValue());
        assertEquals(5L, report.getSequences().get(2).predictPreviousValue());
        assertEquals(2L, report.predictPrevious());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day9-puzzle.txt"));

        Report report = new Report(input);

        assertEquals(993L, report.predictPrevious());
    }
}
