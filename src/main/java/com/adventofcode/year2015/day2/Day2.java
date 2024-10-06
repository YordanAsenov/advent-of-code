package com.adventofcode.year2015.day2;

import java.util.List;

public class Day2 {

    public static int solve(List<String> input) {
        int totalArea = 0;

        for (String row : input) {
            int firstXPosition = row.indexOf('x');
            int secondXPosition = row.indexOf('x', firstXPosition + 1);

            int length = Integer.parseInt(row.substring(0, firstXPosition));
            int width = Integer.parseInt(row.substring(firstXPosition + 1, secondXPosition));
            int height = Integer.parseInt(row.substring(secondXPosition + 1));

            Box box = new Box(length, width, height);
            int boxArea = box.getArea() + box.getMinimumSideSurfaceArea();
            totalArea += boxArea;
        }

        return totalArea;
    }

    public static int solve2(List<String> input) {
        int total = 0;

        for (String row : input) {
            int firstXPosition = row.indexOf('x');
            int secondXPosition = row.indexOf('x', firstXPosition + 1);

            int length = Integer.parseInt(row.substring(0, firstXPosition));
            int width = Integer.parseInt(row.substring(firstXPosition + 1, secondXPosition));
            int height = Integer.parseInt(row.substring(secondXPosition + 1));

            Box box = new Box(length, width, height);
            total += (box.getMinimumSidePerimeter() + box.getVolume());
        }

        return total;
    }
}
