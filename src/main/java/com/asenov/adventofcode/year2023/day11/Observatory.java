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
    private Integer expansionFactor;
    private Map<Integer, Position> galaxies;

    public Observatory(List<String> spaces, int expansionFactor) {
        this.spaces = spaces;
        this.originalRows = spaces.size();
        this.originalColumns = spaces.isEmpty() ? 0 : spaces.get(0).length();
        this.expansionFactor = expansionFactor;
        this.galaxies = findGalaxies(this.spaces);
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

    public List<Integer> expandedRows(List<String> spaces, int originalRows) {
        List<Integer> rowsToExpand = new ArrayList<>();
        for (int i = 0; i < originalRows; i++) {
            String row = spaces.get(i);
            if (!row.contains("#")) {
                rowsToExpand.add(i);
            }
        }
        return rowsToExpand;
    }

    public List<Integer> expandedColumns(List<String> spaces, int originalRows, int originalColumns) {
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
        return columnsToExpand;
    }

    public Long solve() {
        int size = this.galaxies.size();
        List<String> pairs = getPairs(size);

        List<Integer> expandedRows = expandedRows(this.spaces, this.originalRows);
        List<Integer> expandedColumns = expandedColumns(this.spaces, this.originalRows, this.originalColumns);

        long sum = 0L;

        for (String string : pairs) {
            String[] pair = string.split("-");
            int firstGalaxy = Integer.parseInt(pair[0]);
            int secondGalaxy = Integer.parseInt(pair[1]);

            Position position1 = galaxies.get(firstGalaxy);
            Position position2 = galaxies.get(secondGalaxy);

            int rowDiff = Math.max(position1.getRow(), position2.getRow()) -
                Math.min(position1.getRow(), position2.getRow());

            Long jumps = 0L;

            long rowJumps = IntStream.range(
                    Math.min(position1.getRow(), position2.getRow()),
                    Math.max(position1.getRow(), position2.getRow())
                ).boxed()
                .filter(expandedRows::contains)
                .count();

            long columnJumps = IntStream.range(
                    Math.min(position1.getColumn(), position2.getColumn()),
                    Math.max(position1.getColumn(), position2.getColumn())
                ).boxed()
                .filter(expandedColumns::contains)
                .count();

            jumps += (rowJumps * this.expansionFactor);
            jumps += (columnJumps * this.expansionFactor);

            int colsDiff = Math.max(position1.getColumn(), position2.getColumn()) -
                Math.min(position1.getColumn(), position2.getColumn());

            sum += ((rowDiff - rowJumps) + (colsDiff - columnJumps) + jumps);
        }

        return sum;
    }
}
