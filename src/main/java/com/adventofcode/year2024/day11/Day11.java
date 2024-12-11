package com.adventofcode.year2024.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {

    private static List<Long> blink(List<Long> stones) {
        List<Long> newStones = new ArrayList<>();
        for (Long stone : stones) {
            if (stone == 0) {
                newStones.add(1L);
            } else if (stone.toString().length() % 2 == 0) {
                Long firstPart = Long.parseLong(stone.toString().substring(0, stone.toString().length() / 2));
                newStones.add(firstPart);
                Long secondPart = Long.parseLong(stone.toString().substring(stone.toString().length() / 2));
                newStones.add(secondPart);
            } else {
                newStones.add(stone * 2024L);
            }
        }

        return newStones;
    }

    private static List<Long> blink(List<Long> stones, int times) {
        int count = 0;

        while (count < times) {
            stones = blink(stones);
            count++;
        }

        return stones;
    }

    public static long solve(String input, int times) {
        List<Long> stones = Arrays.stream(input.split(" "))
            .map(Long::parseLong)
            .toList();

        return blink(stones, times).size();
    }

    public static long solve2(String input) {
        return 0;
    }
}
