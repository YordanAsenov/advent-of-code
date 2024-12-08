package com.adventofcode.year2024.day6;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    private static List<Position> initObstacles(List<String> input) {
        List<Position> obstacles = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '#') {
                    obstacles.add(new Position(j, i));
                }
            }
        }
        return obstacles;
    }

    private static Position getInitialPosition(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '^') {
                    return new Position(j, i);
                }
            }
        }
        return null;
    }

    private static boolean inBoundaries(Position position, int boundaries) {
        return position.getX() >= 0
            && position.getY() >= 0
            && position.getX() < boundaries
            && position.getY() < boundaries;
    }

    private static List<VisitedPosition> getVisitedPositions(
        List<String> input,
        final List<Position> obstacles,
        final Position startPosition
    ) {
        List<VisitedPosition> visited = new ArrayList<>();

        Position position = startPosition;
        Direction direction = Direction.NORTH;

        while (inBoundaries(position, input.size())) {
            visited.add(new VisitedPosition(position, direction));

            Position nextPosition = Position.getNextPosition(position, direction);
            while (obstacles.contains(nextPosition)) {
                direction = Direction.rotate(direction, true);
                nextPosition = Position.getNextPosition(position, direction);
            }

            position = nextPosition;

            final VisitedPosition temp = new VisitedPosition(position, direction);
            if (visited.size() > 1 && visited.contains(temp)) {
                return List.of();
            }
        }

        return visited;
    }

    public static int solve(List<String> input) {
        List<Position> obstacles = initObstacles(input);
        final Position startPosition = getInitialPosition(input);
        List<VisitedPosition> visitedPositions = getVisitedPositions(input, obstacles, startPosition);

        return (int) visitedPositions.stream()
            .map(VisitedPosition::position)
            .distinct()
            .count();
    }

    private record VisitedPosition(Position position, Direction direction) {}

    private static int countPossibleObstructions(
        List<String> input,
        final List<Position> obstacles,
        Position startPosition
    ) {
        int count = 0;

        List<Position> defaultPath = getVisitedPositions(input, obstacles, startPosition).stream()
            .map(p -> p.position)
            .toList();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '#') {
                    continue;
                }
                var currentPosition = Position.of(j,i);
                if (startPosition.equals(currentPosition)) {
                    continue;
                }
                if (!defaultPath.contains(currentPosition)) {
                    continue;
                }

                obstacles.add(currentPosition);

                List<VisitedPosition> visitedPositions = getVisitedPositions(input, obstacles, startPosition);
                if (visitedPositions.isEmpty()) {
                    count++;
                }

                obstacles.remove(currentPosition);
            }
        }

        return count;
    }

    public static int solve2(List<String> input) {
        List<Position> obstacles = initObstacles(input);
        Position startPosition = getInitialPosition(input);
        return countPossibleObstructions(input, obstacles, startPosition);
    }
}
