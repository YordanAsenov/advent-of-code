package com.adventofcode.year2024.day4;

import com.adventofcode.utils.Direction;
import com.adventofcode.utils.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 {

    public record Letter(Position position, Character character) {}

    @AllArgsConstructor
    @Data
    public static class Match {
        private Position from;
        private Position to;
        private Direction direction;
        private Position aLetter;
    }

    public static List<String> rotate(List<String> rows) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < rows.get(i).length(); j++) {
                sb.append(rows.get(rows.size() - 1 - j).charAt(i));
            }
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> linearize(List<String> rows) {
        List<String> result = new ArrayList<>();

        int rowsCount = rows.size();
        int columnsCount = rows.getFirst().length();

        for (int j = columnsCount - 1; j >= 0; j--) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rowsCount; i++) {
                int index = (j + i > rowsCount - 1) ? j + i - rowsCount : j + i;
                sb.append(rows.get(index).charAt(i));
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static int getIndex(int current, int maxSize) {
        if (current >= 0) {
            return current < maxSize ? current : 999;  //current - maxSize; // NO PACMAN!
        } else {
            return 999; // maxSize + current; // NO PACMAN!
        }
    }

    private static Map<Position, Character> initialize(List<String> input) {
        Map<Position, Character> letters = new HashMap<>();

        int maxSize = input.size();
        for (int i = 0; i < maxSize; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                letters.put(new Position(j, i), row.charAt(j));
            }
        }
        return letters;
    }

    private static String buildWord(
            Character c1,
            Character c2,
            Character c3
    ) {
        if (c1 == null || c2 == null || c3 == null) {
            return "";
        }
        return "" + c1 + c2 + c3;
    }

    private static String buildWord(
        Character c1,
        Character c2,
        Character c3,
        Character c4
    ) {
        if (c1 == null || c2 == null || c3 == null || c4 == null) {
            return "";
        }
        return "" + c1 + c2 + c3 + c4;
    }

    public static List<Match> solve(List<String> input) {
        Map<Position, Character> letters = initialize(input);

        List<Match> matches = new ArrayList<>();

        int maxSize = input.size();
        for (int i = 0; i < maxSize; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char character = row.charAt(j);
                if (character != 'X') {
                    continue;
                }

                var dx1 = letters.get(Position.of(getIndex(j + 1, maxSize), i));
                var dx2 = letters.get(Position.of(getIndex(j + 2, maxSize), i));
                var dx3 = letters.get(Position.of(getIndex(j + 3, maxSize), i));
                if ("XMAS".equals(buildWord(character, dx1, dx2, dx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j + 3, maxSize), i),
                        Direction.EAST,
                        Position.of(getIndex(j + 2, maxSize), i)
                    ));
                }

                var sx1 = letters.get(Position.of(getIndex(j - 1, maxSize), i));
                var sx2 = letters.get(Position.of(getIndex(j - 2, maxSize), i));
                var sx3 = letters.get(Position.of(getIndex(j - 3, maxSize), i));
                if ("XMAS".equals(buildWord(character, sx1, sx2, sx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j - 3, maxSize), i),
                        Direction.WEST,
                        Position.of(getIndex(j - 2, maxSize), i)
                    ));
                }

                var up1 = letters.get(Position.of(j, getIndex(i - 1, maxSize)));
                var up2 = letters.get(Position.of(j, getIndex(i - 2, maxSize)));
                var up3 = letters.get(Position.of(j, getIndex(i - 3, maxSize)));
                if ("XMAS".equals(buildWord(character, up1, up2, up3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(j, getIndex(i - 3, maxSize)),
                        Direction.NORTH,
                        Position.of(j, getIndex(i - 2, maxSize))
                    ));
                }

                var down1 = letters.get(Position.of(j, getIndex(i + 1, maxSize)));
                var down2 = letters.get(Position.of(j, getIndex(i + 2, maxSize)));
                var down3 = letters.get(Position.of(j, getIndex(i + 3, maxSize)));
                if ("XMAS".equals(buildWord(character, down1, down2, down3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(j, getIndex(i + 3, maxSize)),
                        Direction.SOUTH,
                        Position.of(j, getIndex(i + 2, maxSize))
                    ));
                }

                var updx1 = letters.get(Position.of(getIndex(j + 1, maxSize), getIndex(i - 1, maxSize)));
                var updx2 = letters.get(Position.of(getIndex(j + 2, maxSize), getIndex(i - 2, maxSize)));
                var updx3 = letters.get(Position.of(getIndex(j + 3, maxSize), getIndex(i - 3, maxSize)));
                if ("XMAS".equals(buildWord(character, updx1, updx2, updx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j + 3, maxSize), getIndex(i - 3, maxSize)),
                        Direction.NORTH_EAST,
                        Position.of(getIndex(j + 2, maxSize), getIndex(i - 2, maxSize))
                    ));
                }

                var upsx1 = letters.get(Position.of(getIndex(j - 1, maxSize), getIndex(i - 1, maxSize)));
                var upsx2 = letters.get(Position.of(getIndex(j - 2, maxSize), getIndex(i - 2, maxSize)));
                var upsx3 = letters.get(Position.of(getIndex(j - 3, maxSize), getIndex(i - 3, maxSize)));
                if ("XMAS".equals(buildWord(character, upsx1, upsx2, upsx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j - 3, maxSize), getIndex(i - 3, maxSize)),
                        Direction.NORTH_WEST,
                        Position.of(getIndex(j - 2, maxSize), getIndex(i - 2, maxSize))
                    ));
                }

                var downdx1 = letters.get(Position.of(getIndex(j + 1, maxSize), getIndex(i + 1, maxSize)));
                var downdx2 = letters.get(Position.of(getIndex(j + 2, maxSize), getIndex(i + 2, maxSize)));
                var downdx3 = letters.get(Position.of(getIndex(j + 3, maxSize), getIndex(i + 3, maxSize)));
                if ("XMAS".equals(buildWord(character, downdx1, downdx2, downdx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j + 3, maxSize), getIndex(i + 3, maxSize)),
                        Direction.SOUTH_EAST,
                        Position.of(getIndex(j + 2, maxSize), getIndex(i + 2, maxSize))
                    ));
                }

                var downsx1 = letters.get(Position.of(getIndex(j - 1, maxSize), getIndex(i + 1, maxSize)));
                var downsx2 = letters.get(Position.of(getIndex(j - 2, maxSize), getIndex(i + 2, maxSize)));
                var downsx3 = letters.get(Position.of(getIndex(j - 3, maxSize), getIndex(i + 3, maxSize)));
                if ("XMAS".equals(buildWord(character, downsx1, downsx2, downsx3))) {
                    matches.add(new Match(
                        new Position(j,i),
                        Position.of(getIndex(j - 3, maxSize), getIndex(i + 3, maxSize)),
                        Direction.SOUTH_WEST,
                        Position.of(getIndex(j - 2, maxSize), getIndex(i + 2, maxSize))
                    ));
                }
            }
        }

        return matches;
    }

    public static long solve2(List<String> input) {
        Map<Position, Character> letters = initialize(input);

        List<Match> matches = new ArrayList<>();

        int maxSize = input.size();
        for (int i = 0; i < maxSize; i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char character = row.charAt(j);
                if (character != 'M') {
                    continue;
                }

                var updx1 = letters.get(Position.of(getIndex(j + 1, maxSize), getIndex(i - 1, maxSize)));
                var updx2 = letters.get(Position.of(getIndex(j + 2, maxSize), getIndex(i - 2, maxSize)));
                if ("MAS".equals(buildWord(character, updx1, updx2))) {
                    matches.add(new Match(
                            new Position(j,i),
                            Position.of(getIndex(j + 2, maxSize), getIndex(i - 2, maxSize)),
                            Direction.NORTH_EAST,
                            Position.of(getIndex(j + 1, maxSize), getIndex(i - 1, maxSize))
                    ));
                }

                var upsx1 = letters.get(Position.of(getIndex(j - 1, maxSize), getIndex(i - 1, maxSize)));
                var upsx2 = letters.get(Position.of(getIndex(j - 2, maxSize), getIndex(i - 2, maxSize)));
                if ("MAS".equals(buildWord(character, upsx1, upsx2))) {
                    matches.add(new Match(
                            new Position(j,i),
                            Position.of(getIndex(j - 2, maxSize), getIndex(i - 2, maxSize)),
                            Direction.NORTH_WEST,
                            Position.of(getIndex(j - 1, maxSize), getIndex(i - 1, maxSize))
                    ));
                }

                var downdx1 = letters.get(Position.of(getIndex(j + 1, maxSize), getIndex(i + 1, maxSize)));
                var downdx2 = letters.get(Position.of(getIndex(j + 2, maxSize), getIndex(i + 2, maxSize)));
                if ("MAS".equals(buildWord(character, downdx1, downdx2))) {
                    matches.add(new Match(
                            new Position(j,i),
                            Position.of(getIndex(j + 2, maxSize), getIndex(i + 2, maxSize)),
                            Direction.SOUTH_EAST,
                            Position.of(getIndex(j + 1, maxSize), getIndex(i + 1, maxSize))
                    ));
                }

                var downsx1 = letters.get(Position.of(getIndex(j - 1, maxSize), getIndex(i + 1, maxSize)));
                var downsx2 = letters.get(Position.of(getIndex(j - 2, maxSize), getIndex(i + 2, maxSize)));
                if ("MAS".equals(buildWord(character, downsx1, downsx2))) {
                    matches.add(new Match(
                            new Position(j,i),
                            Position.of(getIndex(j - 2, maxSize), getIndex(i + 2, maxSize)),
                            Direction.SOUTH_WEST,
                            Position.of(getIndex(j - 1, maxSize), getIndex(i + 1, maxSize))
                    ));
                }
            }
        }

        return matches.stream()
            .collect(Collectors.groupingBy(m -> m.aLetter, Collectors.counting()))
            .values().stream()
            .filter(v -> v == 2)
            .count();
    }
}
