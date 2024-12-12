package com.adventofcode.year2024.day11;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Day11 {

    private static final Map<Turn, BigInteger> firstLevelCache = new ConcurrentHashMap<>();

    private static final Map<BigInteger, List<BigInteger>> secondLevelCache = new ConcurrentHashMap<>();

    private static final Map<BigInteger, BigInteger> thirdLevelCache = new ConcurrentHashMap<>();

    private static List<BigInteger> compute(BigInteger stone) {
        if (BigInteger.ZERO.equals(stone)) {
            return List.of(BigInteger.ONE);
        } else if (stone.toString().length() % 2 == 0) {
            String firstPart = stone.toString().substring(0, stone.toString().length() / 2);
            String secondPart = stone.toString().substring(stone.toString().length() / 2);
            return List.of(new BigInteger(firstPart), new BigInteger(secondPart));
        } else {
            return List.of(thirdLevelCache.computeIfAbsent(stone, s -> s.multiply(BigInteger.valueOf(2024L))));
        }
    }

    private static BigInteger compute(BigInteger stone, int times) {
        BigInteger sum = BigInteger.ZERO;
        List<BigInteger> newStones = secondLevelCache.computeIfAbsent(stone, Day11::compute);
        for (BigInteger newStone : newStones.reversed()) {
            sum = sum.add(blink(newStone, times));
        }
        return sum;
    }

    private record Turn(BigInteger stone, int times) {}

    private static BigInteger blink(BigInteger stone, int times) {
        if (times == 0) {
            return BigInteger.ONE;
        }

        var turn = new Turn(stone, times - 1);
        BigInteger result = firstLevelCache.get(turn);
        if (result == null) {
            result = compute(turn.stone, turn.times);
            firstLevelCache.put(turn, result);
        }

        return result;
    }

    public static BigInteger solve(String input, int times) {
        List<BigInteger> stones = Arrays.stream(input.split(" "))
            .map(BigInteger::new)
            .toList();

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger stone : stones.reversed()) {
            sum = sum.add(blink(stone, times));
        }
        return sum;
    }
}
