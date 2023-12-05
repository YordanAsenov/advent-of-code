package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day4.Card;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void card1() {
        String input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        Card card = new Card(input);

        assertEquals(1, card.getId());
        assertEquals(5, card.getWinningNumbers().size());
        assertEquals(8, card.getNumbers().size());
        assertEquals(8, card.getPoints());
    }

    @Test
    void card2() {
        String input = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19";

        Card card = new Card(input);

        assertEquals(2, card.getId());
        assertEquals(5, card.getWinningNumbers().size());
        assertEquals(8, card.getNumbers().size());
        assertEquals(2, card.getPoints());
    }

    @Test
    void card4() {
        String input = "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83";

        Card card = new Card(input);

        assertEquals(4, card.getId());
        assertEquals(5, card.getWinningNumbers().size());
        assertEquals(8, card.getNumbers().size());
        assertEquals(1, card.getPoints());
    }

    @Test
    void solveExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day4-example.txt"));

        Integer result = input.stream()
            .map(Card::new)
            .map(Card::getPoints)
            .reduce(0, Integer::sum);

        assertEquals(13, result);
    }

    @Test
    void solvePuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day4-puzzle.txt"));

        Integer result = input.stream()
            .map(Card::new)
            .map(Card::getPoints)
            .reduce(0, Integer::sum);

        assertEquals(21959, result);
    }
}
