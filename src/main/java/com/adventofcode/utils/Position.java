package com.adventofcode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public Position get(Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(this.x - 1, this.y);
            case EAST -> new Position(this.x, this.y + 1);
            case WEST -> new Position(this.x, this.y - 1);
            case SOUTH -> new Position(this.x + 1, this.y);
            default -> null;
        };
    }

    public List<Position> getPositionsAround() {
        return List.of(
            get(Direction.NORTH),
            get(Direction.EAST),
            get(Direction.WEST),
            get(Direction.SOUTH)
        );
    }

    public static int getXAxisDistance(@NonNull Position a, @NonNull Position b) {
        return (a.getX() > b.getX()) ? (a.getX() - b.getX()) : (b.getX() - a.getX());
    }

    public static int getYAxisDistance(@NonNull Position a, @NonNull Position b) {
        return (a.getY() > b.getY()) ? (a.getY() - b.getY()) : (b.getY() - a.getY());
    }

    public static int getDistance(@NonNull Position a, @NonNull Position b) {
        return getXAxisDistance(a, b) + getYAxisDistance(a, b);
    }

    public static boolean onSameColumn(@NonNull Position a, @NonNull Position b) {
        return a.getX() == b.getX();
    }

    public static boolean onSameRow(@NonNull Position a, @NonNull Position b) {
        return a.getY() == b.getY();
    }

    public static Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(position.getX(), position.getY() - 1);
            case NORTH_EAST -> new Position(position.getX() + 1, position.getY() - 1);
            case NORTH_WEST -> new Position(position.getX() - 1, position.getY() - 1);
            case EAST -> new Position(position.getX() + 1, position.getY());
            case WEST -> new Position(position.getX() - 1, position.getY());
            case SOUTH -> new Position(position.getX(), position.getY() + 1);
            case SOUTH_EAST -> new Position(position.getX() + 1, position.getY() + 1);
            case SOUTH_WEST -> new Position(position.getX() - 1, position.getY() + 1);
        };
    }

    // getNextPosition with PACMAN effect
    public static Position getNextPositionSafe(Position position, Direction direction, int maxX, int maxY) {
        Position next = getNextPosition(position, direction);
        if (next.getX() < 0) {
            next.setX(maxX + next.getX());
        }
        if (next.getY() < 0) {
            next.setY(maxY + next.getY());
        }
        return next;
    }

    /***
     * Assuming the distance between two points is maximum 2
     * @param Position a
     * @param Position b
     * @return the directions to follow to reach the point A from the point B
     */
    public static List<Direction> getDirections(@NonNull Position a, @NonNull Position b) {
        var directions = new ArrayList<Direction>();

        if (a.getY() != b.getY()) {
            if (a.getY() > b.getY()) {
                directions.add(Direction.NORTH);
            } else {
                directions.add(Direction.SOUTH);
            }
        }

        if (a.getX() != b.getX()) {
            if (a.getX() > b.getX()) {
                directions.add(Direction.EAST);
            } else {
                directions.add(Direction.WEST);
            }
        }

        return directions;
    }

    public boolean inBoundaries(int boundaries) {
        return this.getX() >= 0
            && this.getY() >= 0
            && this.getX() < boundaries
            && this.getY() < boundaries;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }

    @Override
    public int compareTo(Position o) {
        if (o == null)
            return 1;
        return (10000 * (this.x - o.getX())) + (this.y - o.getY());
    }
}
