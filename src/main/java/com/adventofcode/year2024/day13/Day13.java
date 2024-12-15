package com.adventofcode.year2024.day13;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public record Vector(long x, long y) {}
    public record Play(Vector a, Vector b, Vector p) {}

    public static List<Play> initPlay(List<String> input, boolean addOffset) {
        List<Play> plays = new ArrayList<>();

        for (int i = 0; i < input.size(); i += 4) {
            String buttonAInput = input.get(i);
            int buttonAx = Integer.parseInt(buttonAInput.substring(buttonAInput.indexOf("X+") + 1, buttonAInput.indexOf(",")));
            int buttonAy = Integer.parseInt(buttonAInput.substring(buttonAInput.indexOf("Y+") + 1));
            Vector vectorA = new Vector(buttonAx, buttonAy);

            String buttonBInput = input.get(i + 1);
            int buttonBx = Integer.parseInt(buttonBInput.substring(buttonBInput.indexOf("X+") + 1, buttonBInput.indexOf(",")));
            int buttonBy = Integer.parseInt(buttonBInput.substring(buttonBInput.indexOf("Y+") + 1));
            Vector vectorB = new Vector(buttonBx, buttonBy);

            String prizeInput = input.get(i + 2);
            int prizeX = Integer.parseInt(prizeInput.substring(prizeInput.indexOf("X=") + 2, prizeInput.indexOf(",")));
            int prizeY = Integer.parseInt(prizeInput.substring(prizeInput.indexOf("Y=") + 2));
            Vector vectorC = new Vector(
                addOffset ? prizeX + 10000000000000L : prizeX,
                addOffset ? prizeY + 10000000000000L : prizeY
            );

            plays.add(new Play(vectorA, vectorB, vectorC));
        }

        return plays;
    }

    public static long compute(Vector a, Vector b) {
        return a.x * b.y - a.y * b.x;
    }

    public static long getScore(Play play) {
        long det = compute(play.a, play.b);
        long detX = compute(play.p, play.b);
        long detY = compute(play.a, play.p);

        if (det == 0) {
            return 0;
        }

        long x = detX / det;
        long y = detY / det;

        boolean checkX = (x * play.a.x + y * play.b.x == play.p.x);
        boolean checkY = (x * play.a.y + y * play.b.y == play.p.y);

        if (checkX && checkY) {
            return (x * 3) + y;
        } else {
            return 0;
        }
    }

    public static long getTotalScore(List<Play> plays) {
        long totalScore = 0;

        for (Play play : plays) {
            totalScore += getScore(play);
        }

        return totalScore;
    }

    public static long solve(List<String> input) {
        List<Play> plays = initPlay(input, false);
        return getTotalScore(plays);
    }

    public static long solve2(List<String> input) {
        List<Play> plays = initPlay(input, true);
        return getTotalScore(plays);
    }
}
