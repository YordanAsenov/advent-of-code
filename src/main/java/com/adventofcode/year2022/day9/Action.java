package com.adventofcode.year2022.day9;

import com.adventofcode.utils.Direction;
import lombok.Data;

@Data
public class Action {
    private Direction direction;
    private int count;

    public Action(String input) {
        String[] command = input.split(" ");
        this.direction = Direction.parseInput(command[0]);
        this.count = Integer.parseInt(command[1]);
    }
}