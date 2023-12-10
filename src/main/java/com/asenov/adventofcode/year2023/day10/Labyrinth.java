package com.asenov.adventofcode.year2023.day10;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Long solve() {
        Position startingPosition = locateStartingPoint().get();

        Long stepsFromEast = countStepsBeginningFromEast(startingPosition);
        Long stepsFromWest = countStepsBeginningFromWest(startingPosition);
        Long stepsFromNorth = countStepsBeginningFromNorth(startingPosition);
        Long stepsFromSouth = countStepsBeginningFromSouth(startingPosition);
        List<Long> steps = List.of(stepsFromNorth, stepsFromSouth, stepsFromEast, stepsFromWest);

        return steps.stream()
            .max(Long::compare)
            .map(max -> max / 2)
            .get();
    }

    public Long countStepsBeginningFromEast(Position position) {
        if (position.getColumn() >= this.columns - 1) {
            return 0L;
        }
        return countStepsFrom(new Position(position.getRow(), position.getColumn() + 1));
    }

    public Long countStepsBeginningFromWest(Position position) {
        if (position.getColumn() <= 0) {
            return 0L;
        }
        return countStepsFrom(new Position(position.getRow(), position.getColumn() - 1));
    }

    public Long countStepsBeginningFromNorth(Position position) {
        if (position.getRow() <= 0) {
            return 0L;
        }
        return countStepsFrom(new Position(position.getRow() - 1, position.getColumn()));
    }

    public Long countStepsBeginningFromSouth(Position position) {
        if (position.getRow() >= this.rows - 1) {
            return 0L;
        }
        return countStepsFrom(new Position(position.getRow() + 1, position.getColumn()));
    }

    public Long countStepsFrom(Position position) {
        Position currentPosition = position;
        Position previousPosition = currentPosition;

        Long steps = 0L;
        while (currentPosition != null) {
            Pipe pipe = getPipe(currentPosition);
            if (pipe == null) {
                return 0L;
            }

            steps++;

            Pipe nextPipe = getPipe(pipe.getNext(previousPosition));
            if (nextPipe == null) {
                return 0L;
            }

            if (nextPipe.isStartingPoint()) {
                return steps + 1;
            }

            previousPosition = currentPosition;
            currentPosition = nextPipe.getPosition();
        }

        return steps;
    }
}
