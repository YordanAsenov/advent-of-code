package com.adventofcode.year2023;

import com.adventofcode.utils.ListUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day14Test {
    @Test
    void solveFirstPartExample() throws IOException {
        List<String> rows = Files.readAllLines(Path.of("src/test/resources/input/day14-example.txt"));
        List<String> columns = ListUtils.transpose(rows);

        int count = 0;
        for (String column : columns) {
            char[] array = column.toCharArray();
            for (int j = 0; j < array.length; j++) {
                for (int k = 0; k < array.length - 1; k++) {
                    if (array[k] == '.' && array[k + 1] == 'O') {
                        array[k + 1] = '.';
                        array[k] = 'O';
                    }
                }
            }

            for (int j = 0; j < array.length; j++) {
                if (array[j] == 'O') {
                    count += (array.length - j);
                }
            }
        }

        assertEquals(136, count);
    }

    @Test
    void solveFirstPartPuzzle() throws IOException {
        List<String> rows = Files.readAllLines(Path.of("src/test/resources/input/day14-puzzle.txt"));
        List<String> columns = ListUtils.transpose(rows);

        int count = 0;
        for (String column : columns) {
            char[] array = column.toCharArray();
            for (int j = 0; j < array.length; j++) {
                for (int k = 0; k < array.length - 1; k++) {
                    if (array[k] == '.' && array[k + 1] == 'O') {
                        array[k + 1] = '.';
                        array[k] = 'O';
                    }
                }
            }

            for (int j = 0; j < array.length; j++) {
                if (array[j] == 'O') {
                    count += (array.length - j);
                }
            }
        }

        assertEquals(108144, count);
    }

    @Test
    void solveSecondPartExample() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day14-example.txt"));
        assertTrue(true);
    }

    @Test
    void solveSecondPartPuzzle() throws IOException {
        // List<String> input = Files.readAllLines(Path.of("src/test/resources/input/day14-puzzle.txt"));
        assertTrue(true);
    }
}
