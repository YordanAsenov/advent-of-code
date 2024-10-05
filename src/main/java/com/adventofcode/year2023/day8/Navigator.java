package com.adventofcode.year2023.day8;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Data
public class Navigator {
    private List<Direction> directions;
    private Map<String, Crossroad> navigationMap;

    private List<Direction> getDirections(String input) {
        char[] directionsArray = input.toCharArray();

        return IntStream.range(0, directionsArray.length)
            .mapToObj(index -> directionsArray[index] == 'R' ? Direction.RIGHT : Direction.LEFT)
            .toList();
    }

    private Map<String, Crossroad> getNavigationMap(List<String> input) {
        Map<String, Crossroad> crossroadsMap = new HashMap<>();

        for (int index = 2; index < input.size(); index++) {
            String row = input.get(index);
            String position = row.split("=")[0].trim();
            String[] crossroad = row.substring(row.indexOf("(") + 1, row.indexOf(")")).split(",");
            crossroadsMap.put(position, new Crossroad(crossroad[0].trim(), crossroad[1].trim()));
        }

        return crossroadsMap;
    }

    public Navigator(List<String> input) {
        this.directions = getDirections(input.get(0));
        this.navigationMap = getNavigationMap(input);
    }

    public List<String> getNodesEndingWith(String suffix) {
        return this.navigationMap.keySet().stream()
            .filter(key -> key.endsWith(suffix))
            .toList();
    }

    public Long minSteps(String departurePosition, List<String> destinationPositions, Map<String, Crossroad> navigationMap) {
        Ghost ghost = new Ghost(departurePosition, navigationMap);

        Long steps = 0L;
        boolean destinationReached = destinationPositions.contains(ghost.getCurrentPosition());

        while (!destinationReached) {
            for (Direction direction : this.directions) {
                steps++;
                ghost.move(direction);

                destinationReached = destinationPositions.contains(ghost.getCurrentPosition());
                if (destinationReached) {
                    return steps;
                }
            }
        }

        return steps;
    }

    private Long greatestCommonDivisor(Long a, Long b) {
        if (a == 0)
            return b;

        return greatestCommonDivisor(b % a, a);
    }
    private Long leastCommonDenominator(Long a, Long b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }

    public Long solve(List<String> departurePositions, List<String> destinationPositions) {
        return departurePositions.stream()
            .map(position -> minSteps(position, destinationPositions, this.navigationMap))
            .reduce(1L, this::leastCommonDenominator);
    }
}
