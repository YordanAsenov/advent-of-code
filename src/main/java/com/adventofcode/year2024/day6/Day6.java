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

    private static List<Position> move(
        List<String> input,
        List<Position> obstacles,
        Position startPosition
    ) {
        List<Position> visited = new ArrayList<>();
        visited.add(startPosition);

        Position position = startPosition;
        Direction direction = Direction.NORTH;

        do {
            Position nextPosition = Position.getNextPosition(position, direction);
            if (obstacles.contains(nextPosition)) {
                direction = Direction.rotate(direction, true);
                nextPosition = Position.getNextPosition(position, direction);
            }
            position = nextPosition;

            if (inBoundaries(position, input.size())) {
                visited.add(position);
            }
        } while (inBoundaries(position, input.size()));

        return visited;
    }

    private static void debug(
        List<String> input,
        List<Position> visitedPositions
    ) {
        visitedPositions = visitedPositions.stream()
            .distinct()
            .toList();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '#') {
                    System.out.print('#');
                } else if (visitedPositions.contains(Position.of(j,i))) {
                    System.out.print('X');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }

    }

    public static int solve(List<String> input) {
        List<Position> obstacles = initObstacles(input);
        Position startPosition = getInitialPosition(input);
        List<Position> visitedPositions = move(input, obstacles, startPosition);

        debug(input, visitedPositions);

        return (int) visitedPositions.stream()
            .distinct()
            .count();
    }

    public static int solve2(List<String> input) {
        return 0;
    }
}
