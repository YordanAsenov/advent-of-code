package com.adventofcode.year2022.day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forest {
    private List<String> input;
    private final Map<Position, Tree> trees = new HashMap<>();

    private void initForest(List<String> input) {
        this.input = input;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Tree tree = new Tree(
                    new Position(j, i),
                    Integer.parseInt("" + row.charAt(j))
                );
                this.trees.put(new Position(j, i), tree);
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

    private Tree getTree(Position position) {
        return this.trees.get(position);
    }

    private Integer getBiggestTreeHeightAlongTheDirection(Position position, Direction direction) {
        Integer biggestHeight = null;

        Tree tree;
        do {
            position = getNextPosition(position, direction);
            tree = getTree(position);
            if (tree != null && (biggestHeight == null || tree.getHeight() > biggestHeight)) {
                biggestHeight = tree.getHeight();
            }
        } while (tree != null);

        return biggestHeight;
    }

    private boolean isTreeVisibleFrom(Tree tree, Direction direction) {
        Integer height = getBiggestTreeHeightAlongTheDirection(tree.getPosition(), direction);
        return height == null || tree.getHeight() > height;
    }

    private boolean isTreeVisible(Tree tree) {
        return isTreeVisibleFrom(tree, Direction.NORTH) ||
            isTreeVisibleFrom(tree, Direction.SOUTH) ||
            isTreeVisibleFrom(tree, Direction.WEST) ||
            isTreeVisibleFrom(tree, Direction.EAST);
    }

    public int countVisibleTrees() {
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position position = new Position(j, i);
                Tree tree = getTree(position);
                if (isTreeVisible(tree)) {
                    count++;
                }
            }
        }

        return count;
    }
}
