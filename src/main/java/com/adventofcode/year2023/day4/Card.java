package com.adventofcode.year2023.day4;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public Card(Integer id) {
        this.id = id;
    }

    public Card(String input) {
        String[] card = input.split(":");
        this.id = getCardId(card[0]);
        String[] cardNumbers = card[1].split("\\|");
        this.winningNumbers = getNumbers(cardNumbers[0]);
        this.numbers = getNumbers(cardNumbers[1]);
    }

    private static int getScratchcards(int cardWinningNumbers) {
        if (cardWinningNumbers == 0)
            return 0;
        int points = 1;
        while(cardWinningNumbers > 1) {
            points *= 2;
            cardWinningNumbers--;
        }
        return points;
    }

    public int getScratchcards() {
        int cardWinningNumbers = (int) this.numbers.stream()
            .filter(n -> this.winningNumbers.contains(n))
            .count();

        return getScratchcards(cardWinningNumbers);
    }

    private static List<Integer> getScratchcardIds(int cardId, int cardWinningNumbers) {
        List<Integer> scratchcardIds = new ArrayList<>();

        while(cardWinningNumbers > 0) {
            scratchcardIds.add(cardId + cardWinningNumbers);
            cardWinningNumbers--;
        }
        return scratchcardIds;
    }

    public List<Integer> getScratchcardIds() {
        int cardWinningNumbers = (int) this.numbers.stream()
            .filter(n -> this.winningNumbers.contains(n))
            .count();

        return getScratchcardIds(this.id, cardWinningNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
