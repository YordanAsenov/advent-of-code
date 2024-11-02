package com.adventofcode.year2022.day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forest {
    private List<String> input;
    private final Map<Position, Tree> forest = new HashMap<>();

    private void initForest(List<String> input) {
        this.input = input;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Tree tree = new Tree(Integer.parseInt("" + row.charAt(j)));
                this.forest.put(new Position(j, i), tree);
            }
        }
    }

    public Forest(List<String> input) {
        initForest(input);
    }

    private static Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(position.getX(), position.getY() - 1);
            case EAST -> new Position(position.getX() + 1, position.getY());
            case WEST -> new Position(position.getX() - 1, position.getY());
            case SOUTH -> new Position(position.getX(), position.getY() + 1);
        };
    }

    private static Tree getTree(Position position, Map<Position, Tree> forest) {
        return forest.get(position);
    }

    private static Integer getBiggestTreeHeightAlongTheDirection(
        Position position,
        Direction direction,
        Map<Position, Tree> forest
    ) {
        Integer biggestHeight = null;

        Tree tree;
        do {
            position = getNextPosition(position, direction);
            tree = getTree(position, forest);
            if (tree != null && (biggestHeight == null || tree.getHeight() > biggestHeight)) {
                biggestHeight = tree.getHeight();
            }
        } while (tree != null);

        return biggestHeight;
    }

    private static boolean isTreeVisible(Position position, Map<Position, Tree> forest) {
        Tree tree = forest.get(position);

        Integer northTreeHeight = getBiggestTreeHeightAlongTheDirection(position, Direction.NORTH, forest);
        if (northTreeHeight == null || tree.getHeight() > northTreeHeight) {
            return true;
        }

        Integer southTreeHeight = getBiggestTreeHeightAlongTheDirection(position, Direction.SOUTH, forest);
        if (southTreeHeight == null || tree.getHeight() > southTreeHeight) {
            return true;
        }

        Integer westTreeHeight = getBiggestTreeHeightAlongTheDirection(position, Direction.WEST, forest);
        if (westTreeHeight == null || tree.getHeight() > westTreeHeight) {
            return true;
        }

        Integer east = getBiggestTreeHeightAlongTheDirection(position, Direction.EAST, forest);
        if (east == null || tree.getHeight() > east) {
            return true;
        }

        // System.out.println("Tree at position(" + position.getX() + ", " + position.getY() + ") is not visible, height: " + tree.getHeight());

        return false;
    }

    public int countVisibleTrees() {
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position position = new Position(j, i);
                if (isTreeVisible(position, this.forest)) {
                    count++;
                }
            }
        }

        return count;
    }
}
