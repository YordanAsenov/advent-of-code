package com.adventofcode.year2022.day9;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Action> actions = new ArrayList<>();
    private List<Knot> knots = new ArrayList<>();

    public Game(List<String> input, int knots) {
        for (String command : input) {
            this.actions.add(new Action(command));
        }

        for (int i = 0; i < knots; i++) {
            this.knots.add(new Knot(
                i != 0 ? this.knots.getLast(): null,
                new Position(0, 0)
            ));
        }
    }

    public int getVisitedPositions(int index) {
        return this.knots.get(index).getVisitedPositions().size();
    }

    private Position nextTailPosition(Knot currentKnot, Knot previousKnot) {
        boolean onSameColumn = Position.onSameColumn(previousKnot.getPosition(), currentKnot.getPosition());
        boolean onSameRow = Position.onSameRow(previousKnot.getPosition(), currentKnot.getPosition());
        int distance = Position.getDistance(previousKnot.getPosition(), currentKnot.getPosition());

        if ((onSameColumn || onSameRow) && distance <= 1) {
            return currentKnot.getPosition(); // don't move
        }
        if ((!onSameColumn && !onSameRow) && distance <= 2) {
            return currentKnot.getPosition(); // don't move
        }

        List<Direction> directions = Position.getDirections(previousKnot.getPosition(), currentKnot.getPosition());

        Position position = currentKnot.getPosition();
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
                Knot head = knots.getFirst();
                head.setPosition(Position.getNextPosition(head.getPosition(), action.getDirection()));

                for (int i = 1; i < this.knots.size(); i++) {
                    Knot knot = this.knots.get(i);
                    knot.setPosition(nextTailPosition(knot, knot.getPrevious()));
                    knot.updateVisitedPositions(knot.getPosition());
                }

                count--;
            }
        }
    }
}
