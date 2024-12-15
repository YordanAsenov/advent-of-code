package com.adventofcode.year2024;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;
import com.adventofcode.year2024.day15.Day15;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Day15Test {
    @Test
    void readStartingPosition() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-example.txt"));
        Position expectedStartingPosition = new Position(2, 2);

        Position robotStartingPosition = Day15.getRobotStartingPosition(input, input.indexOf(""));

        assertEquals(expectedStartingPosition, robotStartingPosition);
    }

    @Test
    void readRobotActions() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-example.txt"));
        var expectedActions = List.of(
            Direction.WEST,
            Direction.NORTH,
            Direction.NORTH,
            Direction.EAST,
            Direction.EAST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.SOUTH,
            Direction.WEST,
            Direction.SOUTH,
            Direction.EAST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST,
            Direction.WEST
        );

        Queue<Direction> robotActions = Day15.getRobotActions(input, input.indexOf(""));

        assertEquals(expectedActions, robotActions);
    }

    @Test
    void gridExists() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-example.txt"));

        Day15.Grid grid = Day15.initGrid(input);

        assertNotNull(grid);
        assertEquals(30, grid.walls().size());
    }

    @Test
    void robotExists() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-example.txt"));

        var position = Position.of(2, 2);
        var expectedActions = List.of(
            Direction.WEST,
            Direction.NORTH,
            Direction.NORTH,
            Direction.EAST,
            Direction.EAST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.SOUTH,
            Direction.WEST,
            Direction.SOUTH,
            Direction.EAST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST,
            Direction.WEST
        );

        Day15.Robot robot = Day15.initRobot(input);

        assertEquals(position, robot.position());
        assertEquals(expectedActions.size(), robot.actions().size());
    }

    @Test
    void solveFirstPartFirstExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-example.txt"));
        long result = Day15.solve(input);
        assertEquals(2028, result);
    }

    @Test
    void solveFirstPartSecondExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/second-example.txt"));
        long result = Day15.solve(input);
        assertEquals(10092, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/first-puzzle.txt"));
        long result = Day15.solve(input);
        assertEquals(1471826, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/2024/day15/second-puzzle.txt"));
        long result = Day15.solve2(input);
        assertEquals(0, result);
    }
}
