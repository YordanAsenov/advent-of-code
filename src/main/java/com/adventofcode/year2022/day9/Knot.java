package com.adventofcode.year2022.day9;

import com.adventofcode.utils.Position;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Knot {
    private Knot previous;
    private Position position;
    private final Map<Position, Integer> visitedPositions = new HashMap<>();

    public Knot(Knot previous, Position position) {
        this.previous = previous;
        this.position = position;
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
}
