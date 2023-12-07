package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day7.Hand;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day7Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-example.txt"));

        List<Hand> hands = input.stream()
            .map(Hand::new)
            .sorted()
            .toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(6440L, result);
    }

    @Test
    void compareHands1() {
        List<Hand> hands = Stream.of(
            new Hand("33332 10"),
            new Hand("2AAAA 15")
        ).sorted().toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(35, result);
    }

    @Test
    void compareHands2() {
        List<Hand> hands = Stream.of(
            new Hand("77888 15"),
            new Hand("77788 40")
        ).sorted().toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(70, result);
    }

    @Test
    void compareHands3() {
        List<Hand> hands = Stream.of(
            new Hand("23456 15"),
            new Hand("52354 40"),
            new Hand("76319 5")
        ).sorted().toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(145, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-puzzle.txt"));

        List<Hand> hands = input.stream()
            .map(Hand::new)
            .sorted()
            .toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(252656917L, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-example.txt"));
        assertTrue(true);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-puzzle.txt"));
        assertTrue(true);
    }
}
