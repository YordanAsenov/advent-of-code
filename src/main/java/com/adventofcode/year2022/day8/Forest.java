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

    private Tree findBiggestTreeAlongDirection(Tree originTree, Direction direction) {
        Position position = getNextPosition(originTree.getPosition(), direction);
        Tree tree = getTree(position);

        Tree farthestTree = null;
        while (tree != null) {
            if (tree.getHeight() >= originTree.getHeight()) {
                return tree;
            }

            farthestTree = tree;
            position = getNextPosition(position, direction);
            tree = getTree(position);
        }

        return farthestTree;
    }

    private boolean isTreeVisibleFrom(Tree originTree, Direction direction) {
        Tree biggestTreeAlongDirection = findBiggestTreeAlongDirection(originTree, direction);
        return biggestTreeAlongDirection == null || originTree.getHeight() > biggestTreeAlongDirection.getHeight();
    }

    private boolean isTreeVisible(Tree originTree) {
        return isTreeVisibleFrom(originTree, Direction.NORTH) ||
            isTreeVisibleFrom(originTree, Direction.SOUTH) ||
            isTreeVisibleFrom(originTree, Direction.WEST) ||
            isTreeVisibleFrom(originTree, Direction.EAST);
    }

    private static int getDistance(Position a, Position b) {
        int xAxisDistance = (a.getX() > b.getX()) ? (a.getX() - b.getX()) : (b.getX() - a.getX());
        int yAxisDistance = (a.getY() > b.getY()) ? (a.getY() - b.getY()) : (b.getY() - a.getY());
        return xAxisDistance + yAxisDistance;
    }

    private int getTreeScenicScore(Tree tree) {
        Tree northBiggestTree = findBiggestTreeAlongDirection(tree, Direction.NORTH);
        Tree southBiggestTree = findBiggestTreeAlongDirection(tree, Direction.SOUTH);
        Tree westBiggestTree = findBiggestTreeAlongDirection(tree, Direction.WEST);
        Tree eastBiggestTree = findBiggestTreeAlongDirection(tree, Direction.EAST);

        int distanceNorth = northBiggestTree != null ?
            getDistance(tree.getPosition(), northBiggestTree.getPosition()): 0;
        int distanceSouth = southBiggestTree != null ?
            getDistance(tree.getPosition(), southBiggestTree.getPosition()) : 0;
        int distanceWest = westBiggestTree != null ?
            getDistance(tree.getPosition(), westBiggestTree.getPosition()) : 0;
        int distanceEast = eastBiggestTree != null ?
            getDistance(tree.getPosition(), eastBiggestTree.getPosition()) : 0;

        return distanceNorth * distanceSouth * distanceWest * distanceEast;
    }

    public int countVisibleTrees() {
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position position = new Position(j, i);
                Tree originTree = getTree(position);
                if (isTreeVisible(originTree)) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getMaxScenicScore() {
        int maxScenicScore = 0;

        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                Position position = new Position(j, i);
                Tree tree = getTree(position);

                int scenicScore = getTreeScenicScore(tree);
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }

        return maxScenicScore;
    }
}
