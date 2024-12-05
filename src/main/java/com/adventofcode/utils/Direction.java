package com.adventofcode.utils;

public enum Direction {
    NORTH,
    NORTH_EAST,
    NORTH_WEST,
    EAST,
    WEST,
    SOUTH,
    SOUTH_EAST,
    SOUTH_WEST;

    public static Direction parseInput(String input) {
        return switch (input) {
            case "U" -> Direction.NORTH;
            case "D" -> Direction.SOUTH;
            case "L" -> Direction.WEST;
            case "R" -> Direction.EAST;
            default -> null;
        };
    }

    public static Direction getOpposite(Direction direction) {
        return switch (direction) {
            case NORTH -> Direction.SOUTH;
            case NORTH_EAST -> Direction.SOUTH_WEST;
            case NORTH_WEST -> Direction.SOUTH_EAST;
            case EAST -> Direction.WEST;
            case WEST -> Direction.EAST;
            case SOUTH -> Direction.NORTH;
            case SOUTH_EAST -> Direction.NORTH_WEST;
            case SOUTH_WEST -> Direction.NORTH_EAST;
        };
    }
}
