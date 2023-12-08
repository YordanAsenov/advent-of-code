package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day7.Hand;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {
    @Test
    @Disabled
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
    void compareHands4() {
        //Hand hand1 = new Hand("55J22 15");
        //Hand hand2 = new Hand("33J44 20");
        //Hand hand3 = new Hand("333J4 20");
        Hand hand4 = new Hand("JJ278 30");

        //Character max1 = hand1.getCardWithHighestOccurrence(hand1.getCards());
        //Character max2 = hand2.getCardWithHighestOccurrence(hand2.getCards());
        //Character max3 = hand3.getCardWithHighestOccurrence(hand3.getCards());
        Character max4 = hand4.getCardWithHighestOccurrence(hand4.getCards());

        //assertEquals('5', max1);
        //assertEquals('4', max2);
        //assertEquals('3', max3);
        assertEquals('8', max4);
    }

    @Test
    @Disabled
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
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-example.txt"));

        List<Hand> hands = input.stream()
            .map(Hand::new)
            .sorted()
            .toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(5905L, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day7-puzzle.txt"));

        List<Hand> hands = input.stream()
            .map(Hand::new)
            .sorted()
            .toList();

        Long result = LongStream.range(0, hands.size())
            .mapToObj(index -> (index + 1L) * hands.get((int) index).getBid())
            .reduce(0L, Long::sum);

        assertEquals(253499763L, result);
    }
}
