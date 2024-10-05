package com.adventofcode.year2023.day6;

import lombok.Data;

@Data
public class Race {
    private Long duration;
    private Long maxDistance;

    public Race(String duration, String maxDistance) {
        this.duration = Long.parseLong(duration);
        this.maxDistance = Long.parseLong(maxDistance);
    }

    public Long countWaysToBeatTheRecord() {
        Long count = 0L;

        Long chargingTime = 0L;
        while (chargingTime < duration) {
            if (((duration - chargingTime) * chargingTime) > this.maxDistance) {
                count++;
            }

            chargingTime++;
        }

        return count;
    }
}
