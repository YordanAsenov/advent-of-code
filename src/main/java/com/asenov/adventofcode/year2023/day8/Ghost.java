package com.asenov.adventofcode.year2023.day8;

import lombok.Data;

import java.util.Map;

@Data
public class Ghost {
    private String initialPosition;
    private String position;
    private Map<String, Crossroad> navigationMap;

    public Ghost(String position, Map<String, Crossroad> navigationMap) {
        this.initialPosition = position;
        this.position = position;
        this.navigationMap = navigationMap;
    }

    public void move(Direction direction) {
        Crossroad crossroad = navigationMap.get(this.position);
        if (Direction.LEFT == direction) {
            this.position = crossroad.getLeft();
        } else {
            this.position = crossroad.getRight();
        }
    }

    public String getCurrentPosition() {
        return this.position;
    }
}
