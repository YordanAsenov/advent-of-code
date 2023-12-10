package com.asenov.adventofcode.year2023.day10;

import lombok.Data;

@Data
public class Pipe {
    private Character value;
    private Position position;

    public Pipe(
        Character value,
        Position position
    ) {
        this.value = value;
        this.position = position;
    }

    public boolean isPipe() {
        return this.value != '.';
    }

    public boolean isStartingPoint() {
        return this.value == 'S';
    }

    public Position getPreviousPipe() {
        if (this.value == '|') {
            return new Position(position.getRow() - 1, position.getColumn());
        }
        if (this.value == 'F') {
            return new Position(position.getRow() + 1, position.getColumn());
        }
        if (this.value == 'L') {
            return new Position(position.getRow(), position.getColumn() + 1);
        }
        if (this.value == '-' || this.value == 'J' || this.value == '7') {
            return new Position(position.getRow(), position.getColumn() - 1);
        }

        return null;
    }

    public Position getNextPipe() {
        if (this.value == 'J' || this.value == 'L') {
            return new Position(position.getRow() - 1, position.getColumn());
        }
        if (this.value == '|' || this.value == '7') {
            return new Position(position.getRow() + 1, position.getColumn());
        }
        if (this.value == '-' || this.value == 'F') {
            return new Position(position.getRow(), position.getColumn() + 1);
        }

        return null;
    }

    public Position getNext(Position previousPosition) {
        if (previousPosition.equals(getNextPipe())) {
            return getPreviousPipe();
        }
        return getNextPipe();
    }
}
