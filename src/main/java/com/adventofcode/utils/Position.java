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
public class Position {
    private int x;
    private int y;

    public Position get(Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(this.x - 1, this.y);
            case EAST -> new Position(this.x, this.y + 1);
            case WEST -> new Position(this.x, this.y - 1);
            case SOUTH -> new Position(this.x + 1, this.y);
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
            case NORTH -> new Position(position.getX(), position.getY() + 1);
            case EAST -> new Position(position.getX() + 1, position.getY());
            case WEST -> new Position(position.getX() - 1, position.getY());
            case SOUTH -> new Position(position.getX(), position.getY() - 1);
        };
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
}
