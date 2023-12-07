package com.asenov.adventofcode.year2023.day6;

import lombok.Data;

@Data
public class Race {
    private Integer duration;
    private Integer maxDistance;

    public Race(String duration, String maxDistance) {
        this.duration = Integer.parseInt(duration);
        this.maxDistance = Integer.parseInt(maxDistance);
    }

    public Integer countWaysToBeatTheRecord() {
        Integer count = 0;

        Integer chargingTime = 0;
        while (chargingTime < duration) {
            if (((duration - chargingTime) * chargingTime) > this.maxDistance) {
                count++;
            }

            chargingTime++;
        }

        return count;
    }
}
