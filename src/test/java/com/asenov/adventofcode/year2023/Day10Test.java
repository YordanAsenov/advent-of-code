package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day10.Labyrinth;
import com.asenov.adventofcode.year2023.day10.Pipe;
import com.asenov.adventofcode.year2023.day10.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example1.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        Pipe northEastPipe = labyrinth.getPipe(3, 1); // L
        Pipe northWestPipe = labyrinth.getPipe(3, 3); // J
        Pipe sourthEastPipe = labyrinth.getPipe(1, 1); // F
        Pipe sourthWestPipe = labyrinth.getPipe(1, 3); // 7

        assertEquals(8, labyrinth.getPipes().size());

        assertEquals(new Position(3,2), northEastPipe.getPreviousPipe());
        assertEquals(new Position(2,1), northEastPipe.getNextPipe());

        assertEquals(new Position(3,2), northWestPipe.getPreviousPipe());
        assertEquals(new Position(2,3), northWestPipe.getNextPipe());

        assertEquals(new Position(2,1), sourthEastPipe.getPreviousPipe());
        assertEquals(new Position(1,2), sourthEastPipe.getNextPipe());

        assertEquals(new Position(1,2), sourthWestPipe.getPreviousPipe());
        assertEquals(new Position(2,3), sourthWestPipe.getNextPipe());

        assertEquals(17, labyrinth.getGroundTiles().size());
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example2.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(new Position(1,1), labyrinth.locateStartingPoint().get());

        assertEquals(4, labyrinth.farthestPointFromStartingPoint());

        assertEquals(17, labyrinth.getGroundTiles().size());
    }

    @Test
    void solveFirstPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example3.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(new Position(2,0), labyrinth.locateStartingPoint().get());

        assertEquals(8, labyrinth.farthestPointFromStartingPoint());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-puzzle.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(6951, labyrinth.farthestPointFromStartingPoint());
    }

    @Test
    void solveSecondPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example2.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(1L, labyrinth.solve());
    }

    @Test
    void solveSecondPartExample4() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example4.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(4L, labyrinth.solve());
    }

    @Test
    void solveSecondPartExample5() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example5.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(37L, labyrinth.solve());
    }

    @Test
    void solveSecondPartExample6() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example6.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(4L, labyrinth.solve());
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-puzzle.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(1L, labyrinth.solve());
    }
}
