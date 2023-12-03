package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day3.Day3;
import com.asenov.adventofcode.year2023.day3.Number;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    @Test
    void numbersInRow() {
        String row = ".664.598..";
        List<Number> numbers = Day3.getNumbers(row);
        assertEquals(2, numbers.size());
        assertEquals(1, numbers.get(0).getIndex());
        assertEquals(5, numbers.get(1).getIndex());
    }

    @Test
    void numbersInRow2() {
        String row = "664";
        List<Number> numbers = Day3.getNumbers(row);
        assertEquals(1, numbers.size());
        assertEquals(0, numbers.get(0).getIndex());
    }

    @Test
    void numbersInRow3() {
        String row = "664*";
        List<Number> numbers = Day3.getNumbers(row);
        assertEquals(1, numbers.size());
        assertEquals(0, numbers.get(0).getIndex());
    }

    @Test
    void symbolBefore1() {
        String row = "664.$598..";
        List<Number> numbers = Day3.getNumbers(row);

        assertFalse(numbers.get(0).isPartNumber("", row, ""));
        assertTrue(numbers.get(1).isPartNumber("", row, ""));
    }

    @Test
    void symbolBefore2() {
        String row = "*664.598..";
        List<Number> numbers = Day3.getNumbers(row);

        assertTrue(numbers.get(0).isPartNumber("", row, ""));
        assertFalse(numbers.get(1).isPartNumber("", row, ""));
    }

    @Test
    void symbolBefore3() {
        String row = "......................764....*..432...........516$.133..633..................489$........396.255.............636..413*.811....337.......*682";
        List<Number> numbers = Day3.getNumbers(row);

        assertTrue(numbers.get(12).isPartNumber("", row, ""));
    }

    @Test
    void symbolUpperRow() {
        String upperRow = "...$.*....";
        String row = ".664.598..";
        List<Number> numbers = Day3.getNumbers(row);

        assertTrue(numbers.get(0).isPartNumber(upperRow, row, ""));
        assertTrue(numbers.get(1).isPartNumber(upperRow, row, ""));
    }

    @Test
    void symbolLowerRow() {
        String row = ".664.598..";
        String lowerRow = "...$.*....";
        List<Number> numbers = Day3.getNumbers(row);

        assertTrue(numbers.get(0).isPartNumber("", row, lowerRow));
        assertTrue(numbers.get(1).isPartNumber("", row, lowerRow));
    }

    @Test
    void symbolInUpperAndLowerRow() {
        String upperRow = "...*......";
        String row = "..35..633.";
        String lowerRow = "......#...";
        List<Number> numbers = Day3.getNumbers(row);

        assertTrue(numbers.get(0).isPartNumber(upperRow, row, lowerRow));
        assertTrue(numbers.get(1).isPartNumber(upperRow, row, lowerRow));
    }


    @Test
    void longRows() {
        String upperRow = "";
        String row = "................................................965..583........389.................307.................512......................395.....387";
        String lowerRow = "........................#....374...382....250...*..........737*....*896.395...........*....................$.........................#......";
        List<Number> numbers = Day3.getNumbers(row);

        int count = 0;
        for (Number number : numbers) {
            if (number.isPartNumber(upperRow, row, lowerRow)) {
                count++;
            }
        }
        assertEquals(4, count);
    }

    @Test
    void solveExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day3-example.txt"));
        int result = Day3.solve(input);
        assertEquals(4361, result);
    }

    @Test
    void solveFirstPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day3-puzzle.txt"));
        int result = Day3.solve(input);
        assertEquals(527024, result);
    }
}
