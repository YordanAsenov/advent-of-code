package com.adventofcode.year2023.day10;

import com.adventofcode.utils.Position;
import lombok.Data;

import java.util.List;

@Data
public class Tile {
    private final Character value;
    private Position position = null;

    public Tile(Character value) {
        this.value = value;
    }

    public Tile(Character value, Position position) {
        this.value = value;
        this.position = position;
    }

    public boolean isPipe() {
        return this.value != '.' && this.value != ' ';
    }

    public boolean isGroundTile() {
        return this.value == '.';
    }

    public boolean isEmpty() {
        return this.value == ' ';
    }

    public boolean isStartingPoint() {
        return this.value == 'S';
    }

    public boolean isVertex() {
        return List.of('S', 'F', 'L', '7', 'J').contains(this.value);
    }

    public List<List<String>> expand() {
        if (this.value == 'S') {
            return List.of(
                List.of(" | "),
                List.of("-S-"),
                List.of(" | ")
            );
        }

        if (this.value == 'F') {
            return List.of(
                List.of("   "),
                List.of(" F-"),
                List.of(" | ")
            );
        }

        if (this.value == '7') {
            return List.of(
                List.of("   "),
                List.of("-7 "),
                List.of(" | ")
            );
        }

        if (this.value == 'L') {
            return List.of(
                List.of(" | "),
                List.of(" L-"),
                List.of("   ")
            );
        }

        if (this.value == 'J') {
            return List.of(
                List.of(" | "),
                List.of("-J "),
                List.of("   ")
            );
        }

        if (this.value == '-') {
            return List.of(
                List.of("   "),
                List.of("---"),
                List.of("   ")
            );
        }

        if (this.value == '|') {
            return List.of(
                List.of(" | "),
                List.of(" | "),
                List.of(" | ")
            );
        }

        if (this.value == '.') {
            return List.of(
                List.of("   "),
                List.of(" . "),
                List.of("   ")
            );
        }

        return List.of();
    }

    public Position getPreviousPipe() {
        if (this.value == '|') {
            return new Position(position.getX() - 1, position.getY());
        }
        if (this.value == 'F') {
            return new Position(position.getX() + 1, position.getY());
        }
        if (this.value == 'L') {
            return new Position(position.getX(), position.getY() + 1);
        }
        if (this.value == '-' || this.value == 'J' || this.value == '7') {
            return new Position(position.getX(), position.getY() - 1);
        }

        return null;
    }

    public Position getNextPipe() {
        if (this.value == 'J' || this.value == 'L') {
            return new Position(position.getX() - 1, position.getY());
        }
        if (this.value == '|' || this.value == '7') {
            return new Position(position.getX() + 1, position.getY());
        }
        if (this.value == '-' || this.value == 'F') {
            return new Position(position.getX(), position.getY() + 1);
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
