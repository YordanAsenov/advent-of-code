package com.asenov.adventofcode.year2023.day11;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Data
@NoArgsConstructor
public class Observatory {
    private Integer originalRows;
    private Integer originalColumns;
    private List<String> spaces;
    private Map<Integer, Position> galaxies;

    public Observatory(List<String> spaces) {
        this.spaces = spaces;
        this.originalRows = spaces.size();
        this.originalColumns = spaces.isEmpty() ? 0 : spaces.get(0).length();
        this.galaxies = findGalaxies(expandTheUniverse(this.spaces, this.originalRows, this.originalColumns));
    }

    private Map<Integer, Position> findGalaxies(List<String> spaces) {
        Map<Integer, Position> result = new HashMap<>();

        int rows = spaces.size();
        int columns = spaces.isEmpty() ? 0 : spaces.get(0).length();

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (spaces.get(i).charAt(j) == '#') {
                    count++;
                    result.put(count, new Position(i, j));
                }
            }
        }

        return result;
    }

    public List<String> getPairs(int size) {
        List<Integer> items = IntStream.range(1, size + 1)
            .boxed()
            .toList();

        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                pairs.add(items.get(i) + "-" + items.get(j));
            }
        }

        return pairs;
    }

    public List<String> expandTheUniverse(List<String> spaces, int originalRows, int originalColumns) {
        List<String> universe = new ArrayList<>(spaces);

        List<Integer> rowsToExpand = new ArrayList<>();

        for (int i = 0; i < this.originalRows; i++) {
            String row = this.spaces.get(i);
            if (!row.contains("#")) {
                rowsToExpand.add(i);
            }
        }

        for (int i = 0; i < rowsToExpand.size(); i++) {
            universe.add(rowsToExpand.get(i) + i, ".".repeat(originalColumns));
        }

        List<Integer> columnsToExpand = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < originalColumns; i++) {
            for (int j = 0; j < originalRows; j++) {
                sb.append(spaces.get(j).charAt(i));
            }
            String column = sb.toString();
            if (!column.contains("#")) {
                columnsToExpand.add(i);
            }
            sb = new StringBuilder();
        }

        for (int i = 0; i < columnsToExpand.size(); i++) {
            for (int j = 0; j < originalRows + rowsToExpand.size(); j++) {
                String row = universe.get(j);
                String newRow = row.substring(0, columnsToExpand.get(i) + i) + "." +
                    row.substring(columnsToExpand.get(i) + i);
                universe.set(j, newRow);
            }
        }

        return universe;
    }

    public Long solve() {
        int size = this.galaxies.size();
        List<String> pairs = getPairs(size);

        long sum = 0L;

        for (String string : pairs) {
            String[] pair = string.split("-");
            int firstGalaxy = Integer.parseInt(pair[0]);
            int secondGalaxy = Integer.parseInt(pair[1]);

            Position position1 = galaxies.get(firstGalaxy);
            Position position2 = galaxies.get(secondGalaxy);

            int rowDiff = Math.max(position1.getRow(), position2.getRow()) -
                Math.min(position1.getRow(), position2.getRow());

            int colsDiff = Math.max(position1.getColumn(), position2.getColumn()) -
                Math.min(position1.getColumn(), position2.getColumn());

            sum += (rowDiff + colsDiff);
        }

        return sum;
    }
}
