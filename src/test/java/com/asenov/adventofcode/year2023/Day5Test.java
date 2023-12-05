package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day5.Almanac;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day5-example.txt"));

        Almanac almanac = new Almanac(input);
        Long lowestLocation = almanac.getLowestLocation();

        assertEquals(2, almanac.getHumidityToLocation().size());
        assertFalse(almanac.getHumidityToLocation().get(0).contains(1L));
        assertTrue(almanac.getHumidityToLocation().get(0).contains(60L));
        assertEquals(-4, almanac.getHumidityToLocation().get(0).offset());
        assertFalse(almanac.getHumidityToLocation().get(1).contains(60L));
        assertEquals(37, almanac.getHumidityToLocation().get(1).offset());
        assertEquals(35, lowestLocation);
    }

    @Test
    void getDestinationValue() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day5-example.txt"));

        Almanac almanac = new Almanac(input);

        Long destinationValue1 = almanac.getDestinationValue(1L, almanac.getSeedToSoil());
        Long destinationValue2 = almanac.getDestinationValue(50L, almanac.getSeedToSoil());
        Long destinationValue3 = almanac.getDestinationValue(52L, almanac.getSeedToSoil());

        assertEquals(1, destinationValue1);
        assertEquals(98, destinationValue2);
        assertEquals(50, destinationValue3);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day5-puzzle.txt"));

        Almanac almanac = new Almanac(input);
        Long lowestLocation = almanac.getLowestLocation();

        assertEquals(177942185, lowestLocation);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day5-example.txt"));

        Almanac almanac = new Almanac(input);
        Long lowestLocation = almanac.getLowestLocation();

        assertEquals(46L, lowestLocation);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day5-puzzle.txt"));

        Almanac almanac = new Almanac(input);
        Long lowestLocation = almanac.getLowestLocation();

        assertEquals(46L, lowestLocation);
    }
}
