package com.adventofcode.year2024.day15;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day15 {

    public static Map<Position, Character> initMap(List<String> input) {
        HashMap<Position, Character> map = new HashMap<>();

        int actionsRowIndex = input.indexOf("");
        for (int i = 0; i < actionsRowIndex; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                map.put(Position.of(j, i), row.charAt(j));
            }
        }
        return map;
    }

    public static Position nextEmptySpaceAlongDirection(
        Position initial,
        Direction direction,
        Map<Position, Character> map
    ) {
        Position current = Position.getNextPosition(initial, direction);
        Character currentSpace = map.get(current);

        if (currentSpace == '.') {
            return current;
        }

        while (currentSpace != '#') {
            current = Position.getNextPosition(current, direction);
            currentSpace = map.get(current);

            if (currentSpace == '.') {
                return current;
            }
        }

        return null;
    }

    public static void printMap(int step, Direction direction, int width, int height, Map<Position, Character> map) {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Step: " + step + " , Direction: " + direction.name());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var current = Position.of(j, i);
                System.out.print(map.get(current));
            }
            System.out.println();
        }
    }

    public record Robot(Position position, Queue<Direction> actions) {
        public Map<Position, Character> doWork(Grid grid, Map<Position, Character> map) {
            Queue<Direction> todo = new LinkedList<>(actions);
            Position currentPosition = position;

            int step = 0;
            while (!todo.isEmpty()) {
                Direction currentAction = todo.poll();

                printMap(step, currentAction, grid.width, grid.height, map);
                step++;

                Position nextPosition = Position.getNextPosition(currentPosition, currentAction);

                if (grid.walls.contains(nextPosition)) {
                    continue;
                }

                if (map.get(nextPosition) == '.') {
                    map.put(currentPosition, '.');
                    map.put(nextPosition, '@');
                    currentPosition = nextPosition;
                    continue;
                }

                if (map.get(nextPosition) == 'O') {
                    Position nextEmpty = nextEmptySpaceAlongDirection(nextPosition, currentAction, map);
                    if (nextEmpty == null) {
                        continue;
                    }

                    Direction opposite = Direction.getOpposite(currentAction);
                    Position prevLastEmpty = Position.getNextPosition(nextEmpty, opposite);

                    while (!nextEmpty.equals(currentPosition)) {
                        Character previousSpace = map.get(prevLastEmpty);

                        map.put(nextEmpty, previousSpace);
                        map.put(prevLastEmpty, '.');

                        nextEmpty = prevLastEmpty;
                        prevLastEmpty = Position.getNextPosition(nextEmpty, opposite);
                    }

                    map.put(currentPosition, '.');
                    map.put(nextPosition, '@');
                    currentPosition = nextPosition;
                }
            }

            return map;
        }
    }

    public static Position getRobotStartingPosition(List<String> input, int lastRowIndex) {
        for (int i = 0; i < lastRowIndex; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char character = row.charAt(j);
                if (character == '@') {
                    return new Position(j, i);
                }
            }
        }
        return null;
    }

    public static Queue<Direction> getRobotActions(List<String> input, int lastRowIndex) {
        Queue<Direction> actions = new LinkedList<>();
        for (int i = lastRowIndex + 1; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char character = row.charAt(j);
                if (character == '>') {
                    actions.add(Direction.EAST);
                }
                if (character == '<') {
                    actions.add(Direction.WEST);
                }
                if (character == '^') {
                    actions.add(Direction.NORTH);
                }
                if (character == 'v') {
                    actions.add(Direction.SOUTH);
                }
            }
        }
        return actions;
    }

    public static Robot initRobot(List<String> input) {
        int actionsRowIndex = input.indexOf("");
        Position startingPosition = getRobotStartingPosition(input, actionsRowIndex);
        final Queue<Direction> actions = getRobotActions(input, actionsRowIndex);
        return new Robot(startingPosition, actions);
    }

    public record Grid(int width, int height, List<Position> walls) {}

    public static Grid initGrid(List<String> input) {
        int actionsRowIndex = input.indexOf("");
        final List<Position> walls = new ArrayList<>();

        int width = input.getFirst().length();
        int height = actionsRowIndex;

        for (int i = 0; i < actionsRowIndex; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char character = row.charAt(j);
                if (character == '#') {
                    walls.add(new Position(j, i));
                }
            }
        }
        return new Grid(width, height, walls);
    }

    public static long computeScore(Grid grid, Map<Position, Character> map) {
        long score = 0;
        for (int i = 0; i < grid.height; i++) {
            for (int j = 0; j < grid.width; j++) {
                Character character = map.get(Position.of(j, i));
                if (character == 'O') {
                    score += ((100L * i) + j);
                }
            }
        }
        return score;
    }

    public static long solve(List<String> input) {
        var map = initMap(input);
        var grid = initGrid(input);
        var robot = initRobot(input);
        var warehouseStatus = robot.doWork(grid, map);
        return computeScore(grid, warehouseStatus);
    }


    public static long solve2(List<String> input) {
        return 0;
    }
}
