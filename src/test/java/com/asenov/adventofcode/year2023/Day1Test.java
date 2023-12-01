package com.asenov.adventofcode.year2023;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void firstNumber() {
        String input = "1abc2";
        Integer result = Day1.firstNumber(input);
        assertEquals(1, result);
    }

    @Test
    void lastNumber() {
        String input = "1abc2";
        Integer result = Day1.firstNumber(Day1.reverseString(input));
        assertEquals(2, result);
    }

    @Test
    void combinationOfFirstAndLastNumber() {
        String input = "1abc2";
        Integer result = Day1.combineFirstAndLastNumber(input);
        assertEquals(12, result);
    }

    @Test
    void sumCalibrationValues() throws IOException {
        List<String> inputs = Files.readAllLines(Path.of("src/test/resources/input/day1-example.txt"));
        Integer result = Day1.sumCalibrationValues(inputs);
        assertEquals(142, result);
    }
}
