package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day1.Day1;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void firstNumber() {
        String input = "5blgzg";
        Integer result = Day1.firstNumber(input);
        assertEquals(5, result);
    }

    @Test
    void lastNumber() {
        String input = "5blgzg";
        Integer result = Day1.lastNumber(input);
        assertEquals(5, result);
    }

    @Test
    void combinationOfFirstAndLastNumber() {
        String input = "5blgzg";
        Integer result = Day1.combineFirstAndLastNumber(input);
        assertEquals(55, result);
    }

    @Test
    void sumCalibrationValues() throws IOException {
        List<String> inputs = Files.readAllLines(Path.of("src/test/resources/input/day1-second-puzzle.txt"));
        Integer result = Day1.sumCalibrationValues(inputs);
        assertEquals(281, result);
    }
}
