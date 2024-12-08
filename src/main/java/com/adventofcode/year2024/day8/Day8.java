package com.adventofcode.year2024.day8;

import com.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8 {

    private static HashMap<Character, List<Position>> initSignals(List<String> input) {
        HashMap<Character, List<Position>> signals = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            var row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                var signal = row.charAt(j);
                if (signal == '.') {
                    continue;
                }

                List<Position> positions = signals.get(signal);
                if (positions == null) {
                    positions = new ArrayList<>();
                }
                positions.add(new Position(j, i));
                signals.put(signal, positions);
            }
        }

        return signals;
    }

    private static boolean inBoundaries(Position position, int boundaries) {
        return position.getX() >= 0
            && position.getY() >= 0
            && position.getX() < boundaries
            && position.getY() < boundaries;
    }

    private static int countAntiNodes(HashMap<Character, List<Position>> signals, int boundaries) {
        List<Position> antiNodes = new ArrayList<>();

        for (Character signal : signals.keySet()) {
            List<Position> antennas = signals.get(signal);

            for (int i = 0; i < antennas.size(); i++) {
                for (int j = 0; j < antennas.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    Position one = antennas.get(i);
                    Position two = antennas.get(j);

                    int diffX = two.getX() - one.getX();
                    int diffY = two.getY() - one.getY();

                    Position antiNode1 = Position.of(one.getX() - diffX, one.getY() - diffY);
                    Position antiNode2 = Position.of(two.getX() - diffX, two.getY() - diffY);

                    if (!antennas.contains(antiNode1) && inBoundaries(antiNode1, boundaries)) {
                        antiNodes.add(antiNode1);
                    }
                    if (!antennas.contains(antiNode2) && inBoundaries(antiNode2, boundaries)) {
                        antiNodes.add(antiNode2);
                    }
                }
            }
        }

        return (int) antiNodes.stream()
            .distinct()
            .count();
    }

    public static int solve(List<String> input) {
        HashMap<Character, List<Position>> signals = initSignals(input);
        return countAntiNodes(signals, input.size());
    }


    private static int countAntiNodes2(HashMap<Character, List<Position>> signals, int boundaries) {
        List<Position> antiNodes = new ArrayList<>();

        for (Character signal : signals.keySet()) {
            List<Position> antennas = signals.get(signal);

            for (int i = 0; i < antennas.size(); i++) {
                for (int j = 0; j < antennas.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    Position one = antennas.get(i);
                    Position two = antennas.get(j);

                    int diffX = two.getX() - one.getX();
                    int diffY = two.getY() - one.getY();

                    Position antiNode1 = Position.of(one.getX() - diffX, one.getY() - diffY);
                    while (inBoundaries(antiNode1, boundaries)) {
                        antiNodes.add(antiNode1);
                        antiNode1 = Position.of(antiNode1.getX() - diffX, antiNode1.getY() - diffY);
                    }

                    Position antiNode2 = Position.of(two.getX() - diffX, two.getY() - diffY);
                    while (inBoundaries(antiNode2, boundaries)) {
                        antiNodes.add(antiNode2);
                        antiNode2 = Position.of(antiNode2.getX() - diffX, antiNode2.getY() - diffY);
                    }

                    int a = 1;
                }
            }
        }

        return (int) antiNodes.stream()
            .distinct()
            .count();
    }

    public static int solve2(List<String> input) {
        HashMap<Character, List<Position>> signals = initSignals(input);
        return countAntiNodes2(signals, input.size());
    }
}
