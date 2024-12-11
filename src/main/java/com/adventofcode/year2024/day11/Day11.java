package com.adventofcode.year2024.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day11 {

    private static final HashMap<BigInteger, BigInteger> partialResult = new HashMap<>();

    private static final HashMap<BigInteger, List<BigInteger>> inventory = new HashMap<>();

    private static List<BigInteger> compute(BigInteger stone) {
        if (BigInteger.ZERO.equals(stone)) {
            return List.of(BigInteger.ONE);
        } else if (stone.toString().length() % 2 == 0) {
            String firstPart = stone.toString().substring(0, stone.toString().length() / 2);
            String secondPart = stone.toString().substring(stone.toString().length() / 2);
            return List.of(new BigInteger(firstPart), new BigInteger(secondPart));
        } else {
            return List.of(partialResult.computeIfAbsent(stone, s -> s.multiply(BigInteger.valueOf(2024L))));
        }
    }

    private static List<BigInteger> blink(List<BigInteger> stones) {
        List<BigInteger> newStones = new ArrayList<>();
        for (BigInteger stone : stones) {
            newStones.addAll(inventory.computeIfAbsent(stone, Day11::compute));
        }
        return newStones;
    }

    private static List<BigInteger> blink(List<BigInteger> stones, int times) {
        int count = 0;
        while (count < times) {
            System.out.println("Blink: " + count);
            stones = blink(stones);
            count++;
        }

        return stones;
    }

    public static long solve(String input, int times) {
        List<BigInteger> stones = Arrays.stream(input.split(" "))
            .map(BigInteger::new)
            .toList();

        return blink(stones, times).size();
    }
}
