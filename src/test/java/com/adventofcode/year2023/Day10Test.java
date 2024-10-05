package com.adventofcode.year2023;

import com.adventofcode.year2023.day10.Labyrinth;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example2.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(4, labyrinth.getFarthestPointFromStartingPoint().get());
    }

    @Test
    void solveFirstPartExample3() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example3.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(8, labyrinth.getFarthestPointFromStartingPoint().get());
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-puzzle.txt"));

        Labyrinth labyrinth = new Labyrinth(input);

        assertEquals(6951, labyrinth.getFarthestPointFromStartingPoint().get());
    }

    @Test
    void solveSecondPartExample4() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example4.txt"));

        Labyrinth labyrinth = new Labyrinth(input);
        labyrinth.print();

        assertEquals(4, labyrinth.getInsideTiles().size() / 3);
    }

    @Test
    void solveSecondPartExample5() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example5.txt"));

        Labyrinth labyrinth = new Labyrinth(input);
        labyrinth.print();

        assertEquals(37, labyrinth.getInsideTiles().size() / 3);
    }

    @Test
    void solveSecondPartExample6() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example6.txt"));

        Labyrinth labyrinth = new Labyrinth(input);
        labyrinth.print();

        assertEquals(8, labyrinth.getInsideTiles().size() / 3);
    }

    @Test
    void solveSecondPartExample7() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-example7.txt"));

        Labyrinth labyrinth = new Labyrinth(input);
        labyrinth.print();

        assertEquals(10, labyrinth.getInsideTiles().size() / 3);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day10-puzzle.txt"));

        Labyrinth labyrinth = new Labyrinth(input);
        labyrinth.print();

        assertEquals(563, labyrinth.getInsideTiles().size() / 3);
    }
}
