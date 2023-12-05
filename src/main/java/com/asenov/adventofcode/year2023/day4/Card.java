package com.asenov.adventofcode.year2023.day4;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Card {
    private Integer id;
    private List<Integer> winningNumbers;
    private List<Integer> numbers;

    private static Integer getCardId(String input) {
        return Integer.parseInt(input.substring(5).trim());
    }

    private static List<Integer> getNumbers(String input) {
        return Arrays.stream(input.split(" "))
            .filter(n -> !n.isEmpty() && !n.isBlank())
            .map(Integer::parseInt)
            .toList();
    }

    public Card(String input) {
        String[] card = input.split(":");
        this.id = getCardId(card[0]);
        String[] cardNumbers = card[1].split("\\|");
        this.winningNumbers = getNumbers(cardNumbers[0]);
        this.numbers = getNumbers(cardNumbers[1]);
    }

    private static int getPoints(int cardWinningNumbers) {
        if (cardWinningNumbers == 0)
            return 0;
        int points = 1;
        while(cardWinningNumbers > 1) {
            points *= 2;
            cardWinningNumbers--;
        }
        return points;
    }

    public int getPoints() {
        int cardWinningNumbers = (int) this.numbers.stream()
            .filter(n -> this.winningNumbers.contains(n))
            .count();

        return getPoints(cardWinningNumbers);
    }
}
