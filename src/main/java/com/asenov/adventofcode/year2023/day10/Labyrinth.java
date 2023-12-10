package com.asenov.adventofcode.year2023.day10;

import lombok.Data;

import java.util.*;

@Data
public class Labyrinth {
    private int rows;
    private int columns;

    private Map<Position, Pipe> pipes;

    private Map<Position, Pipe> getPipes(List<String> input) {
        Map<Position, Pipe> pipesMap = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                Position position = new Position(i, j);
                Pipe pipe = new Pipe(input.get(i).charAt(j), position);
                if (pipe.isPipe()) {
                    pipesMap.put(position, pipe);
                }
            }
        }

        return pipesMap;
    }

    public Labyrinth(List<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).length();
        this.pipes = getPipes(input);
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

    public Integer solve() {
        Position startingPosition = locateStartingPoint().get();

        List<Pipe> pipesFromEast = getPipesFromEast(startingPosition);
        List<Pipe> pipesFromWest = getPipesFromWest(startingPosition);
        List<Pipe> pipesFromNorth = getPipesFromNorth(startingPosition);
        List<Pipe> pipesFromSouth = getPipesFromSouth(startingPosition);
        List<List<Pipe>> allPipes = List.of(pipesFromNorth, pipesFromSouth, pipesFromEast, pipesFromWest);

        return allPipes.stream()
            .filter(pipe -> pipe != null && !pipe.isEmpty())
            .map(List::size)
            .max(Long::compare)
            .map(max -> (max + 1) / 2)
            .get();
    }

    public List<Pipe> getPipesFromEast(Position position) {
        if (position.getColumn() >= this.columns - 1) {
            return List.of();
        }
        return countStepsFrom(new Position(position.getRow(), position.getColumn() + 1));
    }

    public List<Pipe> getPipesFromWest(Position position) {
        if (position.getColumn() <= 0) {
            return List.of();
        }
        return countStepsFrom(new Position(position.getRow(), position.getColumn() - 1));
    }

    public List<Pipe> getPipesFromNorth(Position position) {
        if (position.getRow() <= 0) {
            return List.of();
        }
        return countStepsFrom(new Position(position.getRow() - 1, position.getColumn()));
    }

    public List<Pipe> getPipesFromSouth(Position position) {
        if (position.getRow() >= this.rows - 1) {
            return List.of();
        }
        return countStepsFrom(new Position(position.getRow() + 1, position.getColumn()));
    }

    public List<Pipe> countStepsFrom(Position position) {
        List<Pipe> result = new ArrayList<>();

        Position currentPosition = position;
        Position previousPosition = currentPosition;

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
                return result;
            }

            previousPosition = currentPosition;
            currentPosition = nextPipe.getPosition();
        }

        return result;
    }
}
