package com.adventofcode.year2022.day9;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Position head;
    private Position tail;
    private final List<Action> actions = new ArrayList<>();
    private final Map<Position, Integer> visitedPositions = new HashMap<>();

    public Game(List<String> input) {
        for (String command : input) {
            this.actions.add(new Action(command));
        }

        this.head = new Position(0, 0);
        this.tail = new Position(0, 0);
    }

    public int getVisitedPositions() {
        return this.visitedPositions.size();
    }

    public void updateVisitedPositions(Position position) {
        Integer visits = this.visitedPositions.get(position);
        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }
        this.visitedPositions.put(position, visits);
    }

    private Position nextTailPosition() {
        boolean onSameColumn = Position.onSameColumn(this.head, this.tail);
        boolean onSameRow = Position.onSameRow(this.head, this.tail);
        int distance = Position.getDistance(this.head, this.tail);

        if ((onSameColumn || onSameRow) && distance <= 1) {
            return this.tail; // don't move
        }
        if ((!onSameColumn && !onSameRow) && distance <= 2) {
            return this.tail; // don't move
        }

        List<Direction> directions = Position.getDirections(this.head, this.tail);

        Position position = this.tail;
        if (!onSameColumn && !onSameRow) {
            for (Direction direction : directions) {
                position = Position.getNextPosition(position, direction);
            }
            return position;
        }

        return Position.getNextPosition(position, directions.getFirst());
    }

    public void move() {
        for (Action action : this.actions) {
            int count = action.getCount();
            while (count > 0) {
                this.head = Position.getNextPosition(this.head, action.getDirection());
                this.tail = nextTailPosition();
                updateVisitedPositions(this.tail);
                count--;
            }
        }
    }
}
