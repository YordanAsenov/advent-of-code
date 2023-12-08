package com.asenov.adventofcode.year2023.day7;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Hand implements Comparable<Hand> {
    private Integer bid;
    private String hand;
    private String shadowHand;
    private Map<Character, Long> cards;
    private Map<Character, Long> shadowCards;

    public Hand(String input) {
        String[] row = input.split(" ");
        this.bid = Integer.parseInt(row[1]);
        this.hand = row[0];
        this.cards = getCards(row[0]);
        this.shadowHand = getShadowHand(hand, cards);
        this.shadowCards = getCards(this.shadowHand);
    }

    private static Map<Character, Long> getCards(String input) {
        return input.chars()
            .mapToObj((int value) -> (char) value)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Character getCardWithHighestOccurrence(Map<Character, Long> cards) {
        Map.Entry<Character, Long> maxEntry = null;
        for (Map.Entry<Character, Long> entry : cards.entrySet()) {
            if (maxEntry == null ||
                (entry.getKey() != 'J' &&
                    entry.getValue().compareTo(maxEntry.getValue()) > 0 ||
                    (entry.getValue().compareTo(maxEntry.getValue()) == 0 &&
                        highCard(entry.getKey(), maxEntry.getKey()) > 0))
            ) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    private String getShadowHand(String hand, Map<Character, Long> cards) {
        if (!hand.contains("J")) {
            return hand;
        }

        Character cardWithHighestOccurrence = getCardWithHighestOccurrence(cards);
        return hand.replace('J', cardWithHighestOccurrence);
    }

    private static boolean fiveOfKind(List<Long> values) {
        return values.stream().anyMatch(v -> v == 5L);
    }

    private static boolean fourOfKind(List<Long> values) {
        return values.stream().anyMatch(v -> v == 4L);
    }

    private static boolean fullHouse(List<Long> values) {
        return values.size() == 2 &&
            ((values.get(0) == 2 && values.get(1) == 3) ||
                (values.get(0) == 3 && values.get(1) == 2));
    }

    private static boolean threeOfKind(List<Long> values) {
        return values.stream().anyMatch(v -> v == 3L);
    }

    private static boolean twoPairs(List<Long> values) {
        return values.size() == 3 &&
            ((values.get(0) == 2 && values.get(1) == 2 && values.get(2) == 1) ||
                (values.get(0) == 2 && values.get(1) == 1 && values.get(2) == 2) ||
                (values.get(0) == 1 && values.get(1) == 2 && values.get(2) == 2));
    }

    private static boolean onePair(List<Long> values) {
        return values.size() >= 4 && (
            values.get(0) == 2 ||
            values.get(1) == 2 ||
            values.get(2) == 2 ||
            values.get(3) == 2
        );
    }

    private static Map<Character, Integer> cardWeights() {
        Map<Character, Integer> weights = new HashMap<>();
        weights.put('J', 1);
        weights.put('2', 2);
        weights.put('3', 3);
        weights.put('4', 4);
        weights.put('5', 5);
        weights.put('6', 6);
        weights.put('7', 7);
        weights.put('8', 8);
        weights.put('9', 9);
        weights.put('T', 10);
        weights.put('Q', 11);
        weights.put('K', 12);
        weights.put('A', 13);
        return weights;
    }

    private static Integer highCard(Character card, Character otherCard) {
        Integer value = cardWeights().get(card);
        Integer otherValue = cardWeights().get(otherCard);
        return Integer.compare(value, otherValue);
    }

    @Override
    public int compareTo(Hand other) {
        List<Long> values = this.shadowCards.values().stream().toList();
        List<Long> otherValues = other.shadowCards.values().stream().toList();

        int fiveOfKind = Boolean.compare(fiveOfKind(values), fiveOfKind(otherValues));
        if (fiveOfKind != 0) {
            return fiveOfKind;
        }

        int fourOfKind = Boolean.compare(fourOfKind(values), fourOfKind(otherValues));
        if (fourOfKind != 0) {
            return fourOfKind;
        }

        int fullHouse = Boolean.compare(fullHouse(values), fullHouse(otherValues));
        if (fullHouse != 0) {
            return fullHouse;
        }

        int threeOfKind = Boolean.compare(threeOfKind(values), threeOfKind(otherValues));
        if (threeOfKind != 0) {
            return threeOfKind;
        }

        int twoPairs = Boolean.compare(twoPairs(values), twoPairs(otherValues));
        if (twoPairs != 0) {
            return twoPairs;
        }

        int onePair = Boolean.compare(onePair(values), onePair(otherValues));
        if (onePair != 0) {
            return onePair;
        }

        char[] handShadowCards = this.hand.toCharArray();
        char[] otherHandShadowCards = other.hand.toCharArray();

        int index = 0;
        while (index < 5) {
            int highCard = highCard(handShadowCards[index], otherHandShadowCards[index]);
            if (highCard != 0) {
                return highCard;
            }

            index++;
        }

        return 0;
    }
}
