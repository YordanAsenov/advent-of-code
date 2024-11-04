package com.adventofcode.utils;

public enum Direction {
    NORTH,
    EAST,
    WEST,
    SOUTH;

    public static Direction parseInput(String input) {
        return switch (input) {
            case "U" -> Direction.NORTH;
            case "D" -> Direction.SOUTH;
            case "L" -> Direction.WEST;
            case "R" -> Direction.EAST;
            default -> null;
        };
    }
}
