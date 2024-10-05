package com.adventofcode.year2023.day10;

import lombok.Data;

import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Labyrinth {
    private int rows;
    private int columns;

    private List<String> expandedInput;
    private Map<Position, Tile> tiles;
    private List<Position> groundTiles;
    private Position startingPosition;
    private List<Tile> connectedPipes;
    private List<Position> insideTiles;
    private List<Position> visitedTiles;

    private static String cleanRow(String input) {
        return input.replace("[", "")
            .replace("]", "");
    }
    private static List<String> expandInput(List<String> input) {
        List<String> result = new ArrayList<>();

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        for (String row : input) {
            char[] characters = row.toCharArray();
            for (char character : characters) {
                Tile tile = new Tile(character);
                List<List<String>> expandedPipe = tile.expand();
                sb1.append(cleanRow(expandedPipe.get(0).toString()));
                sb2.append(cleanRow(expandedPipe.get(1).toString()));
                sb3.append(cleanRow(expandedPipe.get(2).toString()));
            }

            result.add(sb1.toString());
            result.add(sb2.toString());
            result.add(sb3.toString());

            sb1 = new StringBuilder();
            sb2 = new StringBuilder();
            sb3 = new StringBuilder();
        }

        return result;
    }

    private Map<Position, Tile> initializeTiles(List<String> input) {
        Map<Position, Tile> pipesMap = new HashMap<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Position position = new Position(i, j);
                pipesMap.put(position, new Tile(input.get(i).charAt(j), position));
            }
        }

        return pipesMap;
    }

    public Labyrinth(List<String> input) {
        this.expandedInput = expandInput(input);
        this.rows = expandedInput.size();
        this.columns = expandedInput.get(0).length();
        this.tiles = initializeTiles(expandedInput);
        this.startingPosition = startingPoint(tiles);
        this.connectedPipes = getConnectedPipes(startingPosition, rows, columns, tiles);
        this.visitedTiles = new ArrayList<>();
        this.insideTiles = getInsideTiles(tiles, connectedPipes);
    }

    public void print() {
        for (int i = 0; i < this.expandedInput.size(); i++) {
            String row = this.expandedInput.get(i);
            for (int j = 0; j < row.length(); j++) {
                Tile tile = new Tile(row.charAt(j), new Position(i, j));

                if (visitedTiles.contains(tile.getPosition()) &&
                    !connectedPipes.contains(tile)) {
                    System.out.print("\033[43m"); // set background
                }

                if (this.connectedPipes.contains(tile)) {
                    System.out.print("\u001B[31m" + "#"); // red
                } else if (this.insideTiles.contains(tile.getPosition())) {
                    System.out.print("\033[0;32m" + "I");
                } else if(tile.isPipe()) {
                    System.out.print("\u001B[34m" + row.charAt(j)); // blue
                } else {
                    //System.out.print("O");
                    System.out.print(row.charAt(j));
                }

                System.out.print("\u001B[0m"); // reset
            }

            System.out.println();
        }
    }

    private static Position startingPoint(Map<Position, Tile> pipes) {
        return pipes.values().stream()
            .filter(Tile::isStartingPoint)
            .map(Tile::getPosition)
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);
    }

    private static List<Tile> getConnectedPipes(Position startingPosition, int rows, int columns, Map<Position, Tile> pipes) {
        List<Tile> pipesFromEast = getPipesFrom(startingPosition, startingPosition.get(Direction.EAST), rows, columns, pipes);
        List<Tile> pipesFromWest = getPipesFrom(startingPosition, startingPosition.get(Direction.WEST), rows, columns, pipes);
        List<Tile> pipesFromNorth = getPipesFrom(startingPosition, startingPosition.get(Direction.NORTH), rows, columns, pipes);
        List<Tile> pipesFromSouth = getPipesFrom(startingPosition, startingPosition.get(Direction.SOUTH), rows, columns, pipes);

        return Stream.of(pipesFromNorth, pipesFromSouth, pipesFromEast, pipesFromWest)
            .flatMap(List::stream)
            .distinct()
            .toList();
    }

    public Optional<Integer> getFarthestPointFromStartingPoint() {
        List<Tile> pipesFromEast = getPipesFrom(startingPosition, startingPosition.get(Direction.EAST), rows, columns, tiles);
        List<Tile> pipesFromWest = getPipesFrom(startingPosition, startingPosition.get(Direction.WEST), rows, columns, tiles);
        List<Tile> pipesFromNorth = getPipesFrom(startingPosition, startingPosition.get(Direction.NORTH), rows, columns, tiles);
        List<Tile> pipesFromSouth = getPipesFrom(startingPosition, startingPosition.get(Direction.SOUTH), rows, columns, tiles);

        return Stream.of(pipesFromNorth, pipesFromSouth, pipesFromEast, pipesFromWest)
            .map(List::size)
            .max(Comparator.comparing(Integer::intValue))
            .map(m -> (m / 3) / 2);
    }

    public static List<Tile> getPipesFrom(
        Position from,
        Position to,
        int rows,
        int columns,
        Map<Position, Tile> pipes
    ) {
        if (from == null ||
            from.getX() <= 0 ||
            from.getX() >= rows - 1 ||
            from.getY() <= 0 ||
            from.getY() >= columns - 1
        ) {
            return List.of();
        }
        return getConnectedPipes(from, to, pipes);
    }

    private static Tile getPipe(Position position, Map<Position, Tile> pipes) {
        return pipes.get(position);
    }

    private static List<Tile> getConnectedPipes(Position from, Position to, Map<Position, Tile> connectedPipes) {
        List<Tile> result = new ArrayList<>();

        Position current = to;

        while (current != null) {
            Tile tile = getPipe(current, connectedPipes);
            if (tile == null) {
                return List.of();
            }

            result.add(tile);

            Tile nextTile = getPipe(tile.getNext(from), connectedPipes);
            if (nextTile == null) {
                return List.of();
            }

            if (nextTile.isStartingPoint()) {
                result.add(nextTile);
                return result;
            }

            from = current;
            current = nextTile.getPosition();
        }

        return result;
    }

    private static Position getFirstUpperLeftPipePosition(Map<Position, Tile> tiles, List<Tile> connectedPipes) {
        Integer firstRow = tiles.values().stream()
            .filter(connectedPipes::contains)
            .map(Tile::getPosition)
            .map(Position::getX)
            .min(Integer::compareTo).get();

        Integer firstColumn = tiles.values().stream()
            .filter(connectedPipes::contains)
            .map(Tile::getPosition)
            .filter(p -> p.getX() == firstRow)
            .map(Position::getY)
            .min(Integer::compareTo).get();

        return new Position(firstRow, firstColumn);
    }

    public List<Position> getInsideTiles(Map<Position, Tile> tiles, List<Tile> connectedPipes) {
        Position startPosition = getFirstUpperLeftPipePosition(tiles, connectedPipes).get(Direction.SOUTH).get(Direction.EAST);

        List<Position> tilesInside = new ArrayList<>();
        //List<Position> visited = new ArrayList<>();
        Queue<Position> toVisit = new ArrayDeque<>(List.of(startPosition));

        while (!toVisit.isEmpty()) {
            Position currentPosition = toVisit.poll();
            if (visitedTiles.contains(currentPosition)) {
                continue;
            }

            Tile tile = tiles.get(currentPosition);
            if (tile == null) {
                visitedTiles.add(currentPosition);
                continue;
            }

            if (connectedPipes.contains(tile)) {
                visitedTiles.add(currentPosition);
                continue;
            }

            if (tile.isGroundTile()) {
                tilesInside.add(currentPosition);
                tilesInside.add(currentPosition);
                tilesInside.add(currentPosition);
            }

            if (tile.isPipe()) {
                tilesInside.add(currentPosition);
            }

            List<Position> neighbors = currentPosition.getPositionsAround();
            for (Position neighbor : neighbors) {
                if (!visitedTiles.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            }

            visitedTiles.add(currentPosition);
        }

        return tilesInside;
    }
}
