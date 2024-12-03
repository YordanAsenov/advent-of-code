package com.adventofcode.year2024.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {

    private static boolean isSafe(List<Integer> levels) {
        final int MIN_DIFF = 1;
        final int MAX_DIFF = 3;

        int steps = levels.size() - 1;

        int countIncreasing = 0;
        int countDecreasing = 0;
        int allowedDiffs = 0;

        for (int i = 0; i < steps; i++) {
            int currentLevel = levels.get(i);
            int nextLevel = levels.get(i + 1);

            boolean isGrowing = currentLevel < nextLevel;
            if (isGrowing) {
                countIncreasing++;
            }
            if (!isGrowing) {
                countDecreasing++;
            }

            int diff = isGrowing ? nextLevel - currentLevel : currentLevel - nextLevel;
            if (diff >= MIN_DIFF && diff <= MAX_DIFF) {
                allowedDiffs++;
            }
        }

        if (countIncreasing != steps && countDecreasing != steps) {
            return false;
        }

        return allowedDiffs == steps;
    }

    private static int countSafe(List<String> input, int toleration) {
        int result = 0;

        for (String report : input) {
            List<Integer> levels = Arrays.stream(report.split(" "))
                .map(Integer::parseInt)
                .toList();

            boolean isReportSafe = isSafe(levels);
            if (isReportSafe) {
                result++;
                System.out.println("Report is safe: " + report);
            } else {
                if (toleration > 0) {
                    for (int i = 0; i < levels.size(); i++) {
                        ArrayList<Integer> temp = new ArrayList<>(levels);
                        temp.remove(i);

                        if (isSafe(temp)) {
                            System.out.println("Report is safe: " + report);
                            result++;
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static int solve(List<String> input) {
        return countSafe(input, 0);
    }

    public static int solve2(List<String> input) {
        return countSafe(input, 1);
    }
}
