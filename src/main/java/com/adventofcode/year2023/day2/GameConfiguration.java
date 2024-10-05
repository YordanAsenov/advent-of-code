package com.adventofcode.year2023.day2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameConfiguration {
    private int red;
    private int green;
    private int blue;

    public void increase(String color, int amount) {
        switch (color) {
            case "red":
                red += amount;
                break;
            case "blue":
                blue += amount;
                break;
            case "green":
                green += amount;
                break;
            default:
                break;
        }
    }

    public boolean fits(GameConfiguration configuration) {
        return (this.red <= configuration.red &&
                this.green <= configuration.green &&
                this.blue <= configuration.blue);
    }

    public GameConfiguration max(GameConfiguration other) {
        return new GameConfiguration(
            Math.max(this.red, other.red),
            Math.max(this.green, other.green),
            Math.max(this.blue, other.blue)
        );
    }

    public Integer power() {
        return red * blue * green;
    }
}
