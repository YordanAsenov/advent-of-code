package com.adventofcode.year2024.day14;

import com.adventofcode.utils.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day14 {

    public record Robot(Position position, Position velocity) {
        private Robot move() {
            int x = position.getX() + velocity.getX();
            int y = position.getY() + velocity.getY();
            return new Robot(Position.of(x, y), velocity);
        }

        public Robot moveInside(Grid grid) {
            var newRobot = move();
            return new Robot(grid.constrain(newRobot.position), newRobot.velocity);
        }
    }

    public record Grid(int width, int height) {
        public Position constrain(Position position) {
            int x = (position.getX() % width + width) % width;
            int y = (position.getY() % height + height) % height;
            return Position.of(x, y);
        }

        public List<Quadrant> getQuadrants() {
            int xMiddle = width / 2;
            int yMiddle = height / 2;
            List<Integer> xPositions = List.of(0, xMiddle - 1, xMiddle + 1, width - 1);
            List<Integer> yPositions = List.of(0, yMiddle - 1, yMiddle + 1, height - 1);

            List<Quadrant> quadrants = new ArrayList<>();
            quadrants.add(new Quadrant(
                Position.of(xPositions.getFirst(), yPositions.getFirst()),
                Position.of(xPositions.get(1), yPositions.get(1))
            ));
            quadrants.add(new Quadrant(
                Position.of(xPositions.get(2), yPositions.getFirst()),
                Position.of(xPositions.getLast(), yPositions.get(1))
            ));
            quadrants.add(new Quadrant(
                Position.of(xPositions.getFirst(), yPositions.get(2)),
                Position.of(xPositions.get(1), yPositions.getLast())
            ));
            quadrants.add(new Quadrant(
                Position.of(xPositions.get(2), yPositions.get(2)),
                Position.of(xPositions.getLast(), yPositions.getLast())
            ));
            return quadrants;
        }
    }

    public record Quadrant(Position min, Position max) {}

    private static Position init(String input) {
        String[] parts = input.substring(2).split(",");
        return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public static List<Robot> buildRobots(List<String> input) {
        List<Robot> robots = new ArrayList<>();
        for (String row : input) {
            String[] parts = row.split(" ");
            Position position = init(parts[0]);
            Position velocity = init(parts[1]);
            robots.add(new Robot(position, velocity));
        }
        return robots;
    }

    public static long countRobotsInQuadrant(List<Position> positions, Quadrant quadrant) {
        return positions.stream()
            .filter(r ->
                (r.getX() >= quadrant.min.getX() &&
                    r.getX() <= quadrant.max.getX()) &&
                (r.getY() >= quadrant.min.getY() &&
                    r.getY() <= quadrant.max.getY())
            )
            .count();
    }

    public static long getSecureFactor(List<Position> robotPositions, Grid grid) {
        List<Quadrant> quadrants = grid.getQuadrants();

        long robotsInFirstQuadrant = countRobotsInQuadrant(robotPositions, quadrants.get(0));
        long robotsInSecondQuadrant = countRobotsInQuadrant(robotPositions, quadrants.get(1));
        long robotsInThirdQuadrant = countRobotsInQuadrant(robotPositions, quadrants.get(2));
        long robotsInFourthQuadrant = countRobotsInQuadrant(robotPositions, quadrants.get(3));

        return robotsInFirstQuadrant * robotsInSecondQuadrant * robotsInThirdQuadrant * robotsInFourthQuadrant;
    }

    public static List<Position> moveRobotsInGrid(List<String> input, int times, Grid grid) {
        List<Robot> robots = buildRobots(input);

        for (int i = 0; i < times; i++) {
            List<Robot> robotsAfterMove = new ArrayList<>();
            for (Robot robot : robots) {
                robot = robot.moveInside(grid);
                robotsAfterMove.add(robot);
            }
            robots = robotsAfterMove;
        }

        return robots.stream()
            .map(r -> r.position)
            .toList();
    }

    public static List<Dispersion> getDispersions(List<String> input, int times, Grid grid) {
        List<Dispersion> dispersions = new ArrayList<>();

        List<Robot> robots = buildRobots(input);

        for (int i = 0; i < times; i++) {
            List<Robot> robotsAfterMove = new ArrayList<>();
            for (Robot robot : robots) {
                robot = robot.moveInside(grid);
                robotsAfterMove.add(robot);
            }
            robots = robotsAfterMove;

            List<Position> robotPositions = robots.stream()
                .map(r -> r.position)
                .toList();
            Dispersion dispersion = new Dispersion(i, dispersion(robotPositions));
            dispersions.add(dispersion);
        }

        return dispersions;
    }

    public static long solve(List<String> input, int width, int height) {
        Grid grid = new Grid(width, height);
        List<Position> robotPositions = moveRobotsInGrid(input, 100, grid);
        return getSecureFactor(robotPositions, grid);
    }

    private static void printGridWithRobots(List<Position> robotPositions, Grid grid) {
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                var current = Position.of(j, i);
                if (robotPositions.contains(current)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public record Dispersion(int time, double percentage) implements Comparable<Dispersion> {
        @Override
        public int compareTo(Dispersion o) {
            return Double.compare(percentage, o.percentage);
        }
    }

    private static double dispersion(List<Position> robotPositions) {
        var totalDispersion = robotPositions.stream()
            .map(p -> (int) p.getPositionsAround().stream()
                .filter(robotPositions::contains)
                .count())
            .reduce(0, Integer::sum);

        return (double) totalDispersion / (4 * robotPositions.size());
    }

    public static int solve2(List<String> input, int width, int height) {
        Grid grid = new Grid(width, height);

        int totalPossibleCombinations = grid.width * grid.height;
        List<Dispersion> dispersions = getDispersions(input, totalPossibleCombinations, grid);
        Collections.sort(dispersions);

        int appearTime = dispersions.getLast().time + 1;

        // debug
        List<Position> robotPositions = moveRobotsInGrid(input, appearTime, grid);
        printGridWithRobots(robotPositions, grid);
        // end debug

        return appearTime;
    }
}
