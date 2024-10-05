package com.adventofcode.year2023.day2;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Game {
    private Integer id;
    private List<GameConfiguration> configurations;
}
