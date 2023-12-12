package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day11.Observatory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day11Test {

    private static Stream<Arguments> pairsInput() {
        return Stream.of(
            Arguments.of(1, 0),
            Arguments.of(2, 1),
            Arguments.of(3, 3),
            Arguments.of(4, 6),
            Arguments.of(5, 10),
            Arguments.of(6, 15),
            Arguments.of(7, 21),
            Arguments.of(8, 28),
            Arguments.of(9, 36)
        );
    }

    @ParameterizedTest
    @MethodSource("pairsInput")
    void testPairs(int size, int expectedResult) {
        Observatory observatory = new Observatory();
        List<String> pairs = observatory.getPairs(size);

        assertEquals(expectedResult, pairs.size());
    }

    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 2);

        assertEquals(9, observatory.getGalaxies().size());
        assertEquals(374L, observatory.solve());
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 2);

        List<String> expectedResult = Files.readAllLines(Path.of("src/test/resources/input/day11-example2.txt"));
        assertEquals(expectedResult, observatory.getExpandedSpaces());
    }

    @Test
    void solveFirstPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 3);

        List<String> expectedResult = Files.readAllLines(Path.of("src/test/resources/input/day11-example3.txt"));
        assertEquals(expectedResult, observatory.getExpandedSpaces());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-puzzle.txt"));

        Observatory observatory = new Observatory(input, 2);

        assertEquals(9521550L, observatory.solve());
    }

    @Test
    void solveSecondPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 10);

        assertEquals(1030L, observatory.solve());
    }

    @Test
    void solveSecondPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 100);

        assertEquals(8410L, observatory.solve());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day11-example1.txt"));

        Observatory observatory = new Observatory(input, 1000000);

        assertEquals(8410L, observatory.solve());
    }
}
