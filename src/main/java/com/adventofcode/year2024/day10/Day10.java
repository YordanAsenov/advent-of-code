package com.adventofcode.year2024.day10;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day10 {

    public record FinalPosition(Position start, Position end, int steps, String path, String pathValues) {}

    private static List<FinalPosition> findTrailheads(
        int boundaries,
        HashMap<Position, Integer> map,
        Position start,
        Position last,
        int steps,
        String path,
        String pathValues
    ) {
        List<FinalPosition> finalPositions = new ArrayList<>();

        Integer current = map.get(last);
        if (current == 9) {
            if (steps == 10) {
                System.out.println("Steps: " + steps);
                System.out.println(path);
                System.out.println(pathValues);
            }

            ArrayList<FinalPosition> result = new ArrayList<>();
            result.add(new FinalPosition(
                start,
                last,
                steps,
                path,
                pathValues
            ));
            return result;
        }

        Position north = Position.getNextPosition(last, Direction.NORTH);
        if (north.inBoundaries(boundaries) && map.get(north) == current + 1) {
            var newSteps = steps + 1;
            var newPath = path + ";" + north.toString();
            var newPathValues = pathValues + ";" + map.get(north);
            List<FinalPosition> endingPositions = findTrailheads(boundaries, map, start, north, newSteps, newPath, newPathValues);
            if (!endingPositions.isEmpty()) {
                finalPositions.addAll(endingPositions);
            }
        }

        Position east = Position.getNextPosition(last, Direction.EAST);
        if (east.inBoundaries(boundaries) && map.get(east) == current + 1) {
            var newSteps = steps + 1;
            var newPath = path + ";" + east.toString();
            var newPathValues = pathValues + ";" + map.get(east);
            List<FinalPosition> endingPositions = findTrailheads(boundaries, map, start, east, newSteps, newPath, newPathValues);
            if (!endingPositions.isEmpty()) {
                finalPositions.addAll(endingPositions);
            }
        }

        Position west = Position.getNextPosition(last, Direction.WEST);
        if (west.inBoundaries(boundaries) && map.get(west) == current + 1) {
            var newSteps = steps + 1;
            var newPath = path + ";" + west.toString();
            var newPathValues = pathValues + ";" + map.get(west);
            List<FinalPosition> endingPositions = findTrailheads(boundaries, map, start, west, newSteps, newPath, newPathValues);
            if (!endingPositions.isEmpty()) {
                finalPositions.addAll(endingPositions);
            }
        }

        Position south = Position.getNextPosition(last, Direction.SOUTH);
        if (south.inBoundaries(boundaries) && map.get(south) == current + 1) {
            var newSteps = steps + 1;
            var newPath = path + ";" + south.toString();
            var newPathValues = pathValues + ";" + map.get(south);
            List<FinalPosition> endingPositions = findTrailheads(boundaries, map, start, south, newSteps, newPath, newPathValues);
            if (!endingPositions.isEmpty()) {
                finalPositions.addAll(endingPositions);
            }
        }

        return finalPositions;
    }

    private static List<FinalPosition> findTrailheads(
        int boundaries,
        HashMap<Position, Integer> map,
        List<Position> startPositions
    ) {
        List<FinalPosition> positions = new ArrayList<>();

        for (Position startPosition : startPositions) {
            List<FinalPosition> trailheads = findTrailheads(
                boundaries,
                map,
                startPosition,
                startPosition,
                1,
                startPosition.toString(),
                map.get(startPosition).toString()
            );
            if (!trailheads.isEmpty()) {
                positions.addAll(trailheads);
            }
        }

        return positions;
    }

    private static long countTrailheads(List<FinalPosition> positions) {
        long sum = 0;

        var startPositions = positions.stream()
            .map(p -> p.start)
            .distinct()
            .toList();

        for (Position position : startPositions) {
            sum += positions.stream()
                .filter(p -> p.start.equals(position))
                .map(p -> p.end)
                .distinct()
                .count();
        }

        return sum;
    }

    public static long solve(List<String> input) {
        final List<Position> startingPositions = new ArrayList<>();
        final HashMap<Position, Integer> map = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position current = Position.of(j, i);
                if (row.charAt(j) == '.') {
                    map.put(current, 99);
                } else {
                    map.put(current, Integer.parseInt("" + row.charAt(j)));
                }

                if (row.charAt(j) == '0') {
                    startingPositions.add(current);
                }
            }
        }

        List<FinalPosition> trailheads = findTrailheads(input.size(), map, startingPositions);
        return countTrailheads(trailheads);
    }

    private static long countTrailheadsRating(List<FinalPosition> positions) {
        return positions.size();
    }

    public static long solve2(List<String> input) {
        final List<Position> startingPositions = new ArrayList<>();
        final HashMap<Position, Integer> map = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position current = Position.of(j, i);
                if (row.charAt(j) == '.') {
                    map.put(current, 99);
                } else {
                    map.put(current, Integer.parseInt("" + row.charAt(j)));
                }

                if (row.charAt(j) == '0') {
                    startingPositions.add(current);
                }
            }
        }

        List<FinalPosition> trailheads = findTrailheads(input.size(), map, startingPositions);
        return countTrailheadsRating(trailheads);
    }
}
