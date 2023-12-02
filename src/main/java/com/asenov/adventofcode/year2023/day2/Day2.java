package com.asenov.adventofcode.year2023.day2;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static Game getGameDetails(String input) {
        String[] game = input.split(":");
        int gameId = Integer.parseInt(game[0].substring(5));

        String[] sets = game[1].split(";");

        List<GameConfiguration> configurations = new ArrayList<>();
        for (String set : sets) {
            GameConfiguration configuration = new GameConfiguration();

            String[] colorsInSet = set.trim().split(",");
            for (String colorInfo : colorsInSet) {
                String[] colorDetail = colorInfo.trim().split(" ");
                configuration.increase(colorDetail[1], Integer.parseInt(colorDetail[0]));
            }

            configurations.add(configuration);
        }

        return Game.builder()
            .id(gameId)
            .configurations(configurations)
            .build();
    }

    public static int solve(List<String> input, GameConfiguration limits) {
        int sum = 0;
        for (String line : input) {
            Game game = getGameDetails(line);
            boolean fits = game.getConfigurations().stream().allMatch(
                configuration -> configuration.fits(limits)
            );

            if (fits)
                sum += game.getId();
        }
        return sum;
    }

    public static int solvePartTwo(List<String> input) {
        int sum = 0;
        for (String line : input) {
            Game game = getGameDetails(line);
            Integer power = game.getConfigurations().stream()
                    .reduce(new GameConfiguration(0, 0, 0),
                            GameConfiguration::max)
                    .power();

            sum += power;
        }
        return sum;
    }
}
