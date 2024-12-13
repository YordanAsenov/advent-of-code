package com.adventofcode.year2024.day12;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day12 {
    private static HashMap<Position, Character> initMap(List<String> input) {
        HashMap<Position, Character> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                map.put(Position.of(j, i), row.charAt(j));
            }
        }
        return map;
    }

    private static List<Position> getAdjacentPlots(
        Position current,
        Character plot,
        HashMap<Position, Character> map,
        int boundaries,
        List<Position> plotsInSameRegion,
        List<Position> visited
    ) {
        List<Position> adjacentPlots = new ArrayList<>();

        Position north = Position.getNextPosition(current, Direction.NORTH);
        if (!visited.contains(north) && north.inBoundaries(boundaries) && plot.equals(map.get(north)) && !plotsInSameRegion.contains(north)) {
            adjacentPlots.add(north);
        }

        Position east = Position.getNextPosition(current, Direction.EAST);
        if (!visited.contains(east) && east.inBoundaries(boundaries) && plot.equals(map.get(east)) && !plotsInSameRegion.contains(east)) {
            adjacentPlots.add(east);
        }

        Position south = Position.getNextPosition(current, Direction.SOUTH);
        if (!visited.contains(south) && south.inBoundaries(boundaries) && plot.equals(map.get(south)) && !plotsInSameRegion.contains(south)) {
            adjacentPlots.add(south);
        }

        Position west = Position.getNextPosition(current, Direction.WEST);
        if (!visited.contains(west) && west.inBoundaries(boundaries) && plot.equals(map.get(west)) && !plotsInSameRegion.contains(west)) {
            adjacentPlots.add(west);
        }

        return adjacentPlots;
    }

    private static List<Position> findPlotsInSameRegion(
        Position current,
        Character plot,
        HashMap<Position, Character> map,
        int boundaries,
        List<Position> plotsInSameRegion,
        List<Position> visited
    ) {
        visited.add(current);

        List<Position> adjacentPlots = getAdjacentPlots(current, plot, map, boundaries, plotsInSameRegion, visited);

        ArrayList<Position> allPlots = new ArrayList<>(plotsInSameRegion);
        allPlots.addAll(adjacentPlots);
        final var newPlots = new ArrayList<Position>(adjacentPlots);
        newPlots.add(current);

        for (Position position : adjacentPlots) {
            if (plotsInSameRegion.contains(position)) {
                continue;
            }

            Character adjacentPlot = map.get(position);
            List<Position> otherPlots = findPlotsInSameRegion(position, adjacentPlot, map, boundaries, allPlots, visited);
            if (!otherPlots.isEmpty()) {
                otherPlots.forEach(p -> {
                    if (!newPlots.contains(p)) {
                        newPlots.add(p);
                    }
                } );
            }
        }

        return newPlots;
    }

    private record Region(List<Position> positions) {}

    private static List<Region> initRegions(List<String> input, HashMap<Position, Character> map, int boundaries) {
        var regions = new ArrayList<Region>();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position current = Position.of(j, i);
                if (regions.stream().anyMatch(r -> r.positions.contains(current))) {
                    continue;
                }

                Character currentPlot = map.get(current);
                List<Position> plotsInSameRegion = findPlotsInSameRegion(
                    current,
                    currentPlot,
                    map,
                    boundaries,
                    new ArrayList<>(),
                    new ArrayList<>()
                );
                Collections.sort(plotsInSameRegion);
                regions.add(new Region(plotsInSameRegion));
            }
        }

        return regions.stream()
            .distinct()
            .toList();
    }

    private static boolean sameRegion(
        HashMap<Position, Character> map,
        Position current,
        Character currentPlot,
        Direction direction,
        int boundaries
    ) {
        Position other = Position.getNextPosition(current, direction);
        Character otherPlot = map.get(other);
        return other.inBoundaries(boundaries) && currentPlot.equals(otherPlot);
    }

    private static int computePerimeter(
        HashMap<Position, Character> map,
        Region region,
        int boundaries
    ) {
        int perimeter = 0;

        List<Position> plotsInRegion = region.positions;
        for (Position currentPosition : plotsInRegion) {
            Character currentPlot = map.get(currentPosition);

            if (!sameRegion(map, currentPosition, currentPlot, Direction.NORTH, boundaries)) {
                perimeter++;
            }
            if (!sameRegion(map, currentPosition, currentPlot, Direction.EAST, boundaries)) {
                perimeter++;
            }
            if (!sameRegion(map, currentPosition, currentPlot, Direction.SOUTH, boundaries)) {
                perimeter++;
            }
            if (!sameRegion(map, currentPosition, currentPlot, Direction.WEST, boundaries)) {
                perimeter++;
            }
        }

        return perimeter;
    }

    private static long getTotalPrice(
        HashMap<Position, Character> map,
        List<Region> regions,
        int boundaries
    ) {
        long totalPrice = 0L;

        for (Region region : regions) {
            int area = region.positions.size();
            int perimeter = computePerimeter(map, region, boundaries);
            System.out.println("Region, area: " + area + ", perimeter: " + perimeter + ", plot: " + map.get(region.positions.get(0)) + ", plots: " + Arrays.toString(region.positions.toArray()));
            totalPrice += (area * perimeter);
        }

        return totalPrice;
    }

    public static long solve(List<String> input) {
        var map = initMap(input);
        var regions = initRegions(input, map, input.size());
        return getTotalPrice(map, regions, input.size());
    }

    public static long solve2(List<String> input) {
        return 0L;
    }
}
