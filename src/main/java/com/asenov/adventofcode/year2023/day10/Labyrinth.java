package com.asenov.adventofcode.year2023.day10;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class Labyrinth {
    private int rows;
    private int columns;

    private Map<Position, Pipe> pipes;
    private List<Position> groundTiles;

    private Map<Position, Pipe> getPipes(List<String> input) {
        Map<Position, Pipe> pipesMap = new HashMap<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Position position = new Position(i, j);
                Pipe pipe = new Pipe(input.get(i).charAt(j), position);
                if (pipe.isPipe()) {
                    pipesMap.put(position, pipe);
                }
            }
        }

        return pipesMap;
    }

    private List<Position> getGroundTiles(List<String> input) {
        List<Position> tiles = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (input.get(i).charAt(j) == '.') {
                    Position position = new Position(i, j);
                    tiles.add(position);
                }
            }
        }

        return tiles;
    }

    public Labyrinth(List<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).length();
        this.pipes = getPipes(input);
        this.groundTiles = getGroundTiles(input);
    }

    public Pipe getPipe(Position position) {
        return this.pipes.get(position);
    }

    public Pipe getPipe(int row, int column) {
        return this.pipes.get(new Position(row, column));
    }

    public Optional<Position> locateStartingPoint() {
        return this.pipes.values().stream()
            .filter(Pipe::isStartingPoint)
            .map(Pipe::getPosition)
            .findFirst();
    }

    public List<Pipe> getConnectedPipes() {
        Position startingPosition = locateStartingPoint().get();

        List<Pipe> pipesFromEast = getPipesFromEast(startingPosition);
        List<Pipe> pipesFromWest = getPipesFromWest(startingPosition);
        List<Pipe> pipesFromNorth = getPipesFromNorth(startingPosition);
        List<Pipe> pipesFromSouth = getPipesFromSouth(startingPosition);

        List<Pipe> connectedPipes = List.of(pipesFromNorth, pipesFromSouth, pipesFromEast, pipesFromWest).stream()
            .flatMap(List::stream)
            .distinct()
            .toList();

        return connectedPipes;
    }

    public Integer farthestPointFromStartingPoint() {
        Position startingPosition = locateStartingPoint().get();

        List<Pipe> pipesFromEast = getPipesFromEast(startingPosition);
        List<Pipe> pipesFromWest = getPipesFromWest(startingPosition);
        List<Pipe> pipesFromNorth = getPipesFromNorth(startingPosition);
        List<Pipe> pipesFromSouth = getPipesFromSouth(startingPosition);

        List<List<Pipe>> allPipes = List.of(pipesFromNorth, pipesFromSouth, pipesFromEast, pipesFromWest);

        List<Pipe> connectedPipes = allPipes.stream()
            .max(Comparator.comparing(List::size))
            .get();
        return (connectedPipes.size()) / 2;
    }

    public List<Pipe> getPipesFromEast(Position position) {
        if (position.getColumn() >= this.columns - 1) {
            return List.of();
        }
        return countStepsFrom(position, new Position(position.getRow(), position.getColumn() + 1));
    }

    public List<Pipe> getPipesFromWest(Position position) {
        if (position.getColumn() <= 0) {
            return List.of();
        }
        return countStepsFrom(position, new Position(position.getRow(), position.getColumn() - 1));
    }

    public List<Pipe> getPipesFromNorth(Position position) {
        if (position.getRow() <= 0) {
            return List.of();
        }
        return countStepsFrom(position, new Position(position.getRow() - 1, position.getColumn()));
    }

    public List<Pipe> getPipesFromSouth(Position position) {
        if (position.getRow() >= this.rows - 1) {
            return List.of();
        }
        return countStepsFrom(position, new Position(position.getRow() + 1, position.getColumn()));
    }

    public List<Pipe> countStepsFrom(Position previousPosition, Position position) {
        List<Pipe> result = new ArrayList<>();

        Position currentPosition = position;

        while (currentPosition != null) {
            Pipe pipe = getPipe(currentPosition);
            if (pipe == null) {
                return List.of();
            }

            result.add(pipe);

            Pipe nextPipe = getPipe(pipe.getNext(previousPosition));
            if (nextPipe == null) {
                return List.of();
            }

            if (nextPipe.isStartingPoint()) {
                result.add(nextPipe);
                return result;
            }

            previousPosition = currentPosition;
            currentPosition = nextPipe.getPosition();
        }

        return result;
    }

    private Optional<Integer> firstConnectedPipeColumn(int row, List<Position> connectedPipes) {
        return connectedPipes.stream()
            .filter(position -> position.getRow() == row)
            .map(Position::getColumn)
            .min(Integer::compareTo);
    }

    private Optional<Integer> lastConnectedPipeColumn(int row, List<Position> connectedPipes) {
        return connectedPipes.stream()
            .filter(position -> position.getRow() == row)
            .map(Position::getColumn)
            .max(Integer::compareTo);
    }

    private Optional<Integer> firstConnectedPipeRow(int column, List<Position> connectedPipes) {
        return connectedPipes.stream()
            .filter(position -> position.getColumn() == column)
            .map(Position::getRow)
            .min(Integer::compareTo);
    }

    private Optional<Integer> lastConnectedPipeRow(int column, List<Position> connectedPipes) {
        return connectedPipes.stream()
            .filter(position -> position.getColumn() == column)
            .map(Position::getRow)
            .max(Integer::compareTo);
    }

    private boolean isInside(
        Position groundTile,
        List<Position> connectedPipes,
        List<Position> otherTiles,
        Optional<Integer> firstConnectedPipeRow,
        Optional<Integer> firstConnectedPipeColumn,
        Optional<Integer> lastConnectedPipeRow,
        Optional<Integer> lastConnectedPipeColumn
    ) {
        if (connectedPipes.contains(groundTile))
            return false;

        if (firstConnectedPipeRow.isEmpty() || groundTile.getRow() < firstConnectedPipeRow.get()) {
            return false;
        }
        if (firstConnectedPipeColumn.isEmpty() || groundTile.getColumn() < firstConnectedPipeColumn.get()) {
            return false;
        }
        if (firstConnectedPipeRow.isPresent() && groundTile.getRow() < firstConnectedPipeRow.get()) {
            return false;
        }
        if (lastConnectedPipeRow.isPresent() && groundTile.getRow() > lastConnectedPipeRow.get()) {
            return false;
        }

        if (firstConnectedPipeColumn.isPresent() && groundTile.getColumn() < firstConnectedPipeColumn.get()) {
            return false;
        }
        if (lastConnectedPipeColumn.isPresent() && groundTile.getColumn() > lastConnectedPipeColumn.get()) {
            return false;
        }

        Position leftTile = new Position(groundTile.getRow(), groundTile.getColumn() - 1);
        Position upperTile = new Position(groundTile.getRow() - 1, groundTile.getColumn());
        if (otherTiles.contains(leftTile) || otherTiles.contains(upperTile)) {
            return false;
        }

        return true;
    }

    private void print(
        List<Position> connectedPipes,
        List<Position> outsideTiles,
        List<Position> insideTiles,
        List<Position> otherTiles
    ) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                Position tile = new Position(i, j);
                if (connectedPipes.contains(tile)) {
                    System.out.print("#");
                }

                if (outsideTiles.contains(tile)) {
                    System.out.print("O");
                }

                if (insideTiles.contains(tile)) {
                    System.out.print("I");
                }

                if (otherTiles.contains(tile)) {
                    System.out.print("X");
                }
            }
            System.out.println("");
        }
    }

    public int solve() {
        List<Position> connectedPipes = getConnectedPipes().stream()
            .map(Pipe::getPosition)
            .toList();

        List<Position> outsideTiles = new ArrayList<>();
        List<Position> insideTiles = new ArrayList<>();
        List<Position> otherTiles = new ArrayList<>();

        for (int i = 0; i < this.rows; i++) {
            Optional<Integer> firstConnectedPipeColumn = firstConnectedPipeColumn(i, connectedPipes);
            Optional<Integer> lastConnectedPipeColumn = lastConnectedPipeColumn(i, connectedPipes);

            for (int j = 0; j < this.columns; j++) {
                Position groundTile = new Position(i, j);
                if (connectedPipes.contains(groundTile)) {
                    continue;
                }

                if (!groundTiles.contains(groundTile) && !connectedPipes.contains(groundTile)) {
                    otherTiles.add(groundTile);
                    continue;
                }

                Optional<Integer> firstConnectedPipeRow = firstConnectedPipeRow(j, connectedPipes);
                Optional<Integer> lastConnectedPipeRow = lastConnectedPipeRow(j, connectedPipes);

                if (!isInside(
                    groundTile,
                    connectedPipes,
                    otherTiles,
                    firstConnectedPipeRow,
                    firstConnectedPipeColumn,
                    lastConnectedPipeRow,
                    lastConnectedPipeColumn)
                ) {
                    outsideTiles.add(groundTile);
                } else {
                    insideTiles.add(groundTile);
                }
            }
        }

        print(connectedPipes, outsideTiles, insideTiles, otherTiles);

        int total = this.rows * this.columns;
        int outside = outsideTiles.size();
        int inside = insideTiles.size();
        int other = otherTiles.size();

        return inside;
    }
}
