package com.asenov.adventofcode.year2023.day6;

import lombok.Data;

import java.util.List;

@Data
public class Simulator {
    private Race race;

    public Simulator(List<String> input) {
        String duration = input.get(0)
            .substring(5).trim()
            .replace(" ", "");

        String distance = input.get(1)
            .substring(9).trim()
            .replace(" ", "");

        this.race = new Race(duration, distance);
    }

    public Long solve() {
        return this.race.countWaysToBeatTheRecord();
    }
}
