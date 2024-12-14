package com.adventofcode.year2024;

import com.adventofcode.utils.Position;
import com.adventofcode.year2024.day14.Day14;
import com.adventofcode.year2024.day14.Day14.Grid;
import com.adventofcode.year2024.day14.Day14.Quadrant;
import com.adventofcode.year2024.day14.Day14.Robot;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    private static final List<String> sampleInput = """
         p=0,4 v=3,-3
         p=6,3 v=-1,-3
         p=10,3 v=-1,2
         p=2,0 v=2,-1
         p=0,0 v=1,3
         p=3,0 v=-2,-2
         p=7,6 v=-1,-3
         p=3,0 v=-1,-2
         p=9,3 v=2,3
         p=7,3 v=-1,2
         p=2,4 v=2,-3
         p=9,5 v=-3,-3""".lines().toList();

    @Test
    void robotExist() {
        var position = Position.of(0, 0);
        var velocity = Position.of(5, 6);
        var robot = new Robot(position, velocity);

        assertEquals(position, robot.position());
        assertEquals(velocity, robot.velocity());
    }

    @Test
    void parseInput() {
        var input = "p=6,3 v=-1,-3";
        var expectedRobot = new Robot(Position.of(6, 3), Position.of(-1, -3));

        List<Robot> robots = Day14.buildRobots(List.of(input));

        assertEquals(expectedRobot, robots.getFirst());
    }

    @Test
    void testRobotMoves() {
        List<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(Position.of(6,0));
        expectedPositions.add(Position.of(6,0));
        expectedPositions.add(Position.of(9,0));
        expectedPositions.add(Position.of(0,2));
        expectedPositions.add(Position.of(1,3));
        expectedPositions.add(Position.of(2,3));
        expectedPositions.add(Position.of(5,4));
        expectedPositions.add(Position.of(3,5));
        expectedPositions.add(Position.of(4,5));
        expectedPositions.add(Position.of(4,5));
        expectedPositions.add(Position.of(1,6));
        expectedPositions.add(Position.of(6,6));

        Grid grid = new Grid(11, 7);
        List<Position> robotsPositions = Day14.moveRobotsInGrid(sampleInput, 100, grid);

        assertEquals(List.of(), expectedPositions.stream().filter(p -> !robotsPositions.contains(p)).toList());
        assertEquals(List.of(), robotsPositions.stream().filter(p -> !expectedPositions.contains(p)).toList());
    }

    @Test
    void robotMovesLikePacman() {
        var grid = new Grid(10, 10);
        var robot = new Robot(Position.of(6, 3), Position.of(-7, 10));
        var expectedRobot = new Robot(Position.of(9, 3), Position.of(-7, 10));

        var newRobot = robot.moveInside(grid);

        assertEquals(expectedRobot, newRobot);
    }

    @Test
    void gridProvidesQuadrants() {
        var grid = new Grid(11, 7);
        var expectedQuadrants = List.of(
            new Quadrant(Position.of(0,0), Position.of(4,2)),
            new Quadrant(Position.of(6,0), Position.of(10,2)),
            new Quadrant(Position.of(0,4), Position.of(4,6)),
            new Quadrant(Position.of(6,4), Position.of(10,6))
        );

        var quadrants = grid.getQuadrants();

        assertEquals(expectedQuadrants, quadrants);
    }

    @Test
    void countRobotsInQuadrant() {
        var robotPositions = List.of(
            Position.of(6, 0),
            Position.of(6, 0),
            Position.of(9, 0),
            Position.of(0, 2),
            Position.of(1, 3),
            Position.of(2, 3),
            Position.of(5, 4),
            Position.of(3, 5),
            Position.of(4, 5),
            Position.of(4, 5),
            Position.of(1, 6),
            Position.of(6, 6)
        );
        var quadrant = new Quadrant(Position.of(0,0), Position.of(4,2));

        long count = Day14.countRobotsInQuadrant(robotPositions, quadrant);

        assertEquals(1, count);
    }

    @Test
    void computeSafetyFactor() {
        var grid = new Grid(11, 7);
        List<Position> robotPositions = Day14.moveRobotsInGrid(sampleInput, 100, grid);

        long secureFactor = Day14.getSecureFactor(robotPositions, grid);

        assertEquals(12, secureFactor);
    }

    @Test
    void solveFirstPartExample() throws IOException {
        long result = Day14.solve(sampleInput, 11, 7);
        assertEquals(12, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day14/first-puzzle.txt"));
        long result = Day14.solve(input, 101, 103);
        assertEquals(236628054, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day14/second-puzzle.txt"));
        int result = Day14.solve2(input);
        assertEquals(0, result);
    }
}
