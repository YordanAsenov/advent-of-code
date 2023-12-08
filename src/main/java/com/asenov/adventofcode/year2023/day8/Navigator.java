package com.asenov.adventofcode.year2023.day8;

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

    public Long solve(String departure, String destination) {
        Long steps = 0L;

        String currentPosition = departure;

        while (!currentPosition.equals(destination)) {
            for (Direction direction : this.directions) {
                steps++;

                Crossroad crossroad = navigationMap.get(currentPosition);
                if (Direction.LEFT == direction) {
                    currentPosition = crossroad.getLeft();
                } else {
                    currentPosition = crossroad.getRight();
                }

                if (currentPosition.equals(destination)) {
                    return steps;
                }
            }

        }

        return steps;
    }
}
