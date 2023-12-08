package com.asenov.adventofcode.year2023;

import com.asenov.adventofcode.year2023.day8.Direction;
import com.asenov.adventofcode.year2023.day8.Navigator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    @Test
    void solveFirstPartExample1() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day8-example1.txt"));

        Navigator navigator = new Navigator(input);

        assertEquals(2, navigator.getDirections().size());
        assertEquals(Direction.RIGHT, navigator.getDirections().get(0));
        assertEquals(Direction.LEFT, navigator.getDirections().get(1));

        assertEquals(7, navigator.getNavigationMap().size());
        assertEquals("BBB", navigator.getNavigationMap().get("AAA").getLeft());
        assertEquals("CCC", navigator.getNavigationMap().get("AAA").getRight());

        assertEquals("ZZZ", navigator.getNavigationMap().get("CCC").getLeft());
        assertEquals("GGG", navigator.getNavigationMap().get("CCC").getRight());

        Long result1 = navigator.solve(List.of("AAA"),List.of("AAA"));
        assertEquals(0, result1);

        Long result2 = navigator.solve(List.of("AAA"),List.of("ZZZ"));
        assertEquals(2, result2);
    }

    @Test
    void solveFirstPartExample2() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day8-example2.txt"));
        Navigator navigator = new Navigator(input);

        Long result = navigator.solve(List.of("AAA"),List.of("ZZZ"));

        assertEquals(6, result);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day8-puzzle.txt"));
        Navigator navigator = new Navigator(input);

        Long result = navigator.solve(List.of("AAA"),List.of("ZZZ"));

        assertEquals(19631, result);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day8-example3.txt"));

        Navigator navigator = new Navigator(input);

        List<String> aNodes = navigator.getNodesEndingWith("A");
        assertEquals(2, aNodes.size());

        List<String> zNodes = navigator.getNodesEndingWith("Z");
        assertEquals(2, zNodes.size());

        Long result = navigator.solve(aNodes,zNodes);
        assertEquals(6, result);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day8-puzzle.txt"));

        Navigator navigator = new Navigator(input);
        List<String> aNodes = navigator.getNodesEndingWith("A");
        assertEquals(6, aNodes.size());

        List<String> zNodes = navigator.getNodesEndingWith("Z");
        assertEquals(6, zNodes.size());

        Long steps = navigator.solve(aNodes, zNodes);
        assertEquals(21003205388413L, steps);
    }
}
