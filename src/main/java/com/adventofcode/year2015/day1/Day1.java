package com.adventofcode.year2015.day1;

import java.util.List;

public class Day1 {

    public static int solve(List<String> input, boolean shouldExitAtMinusOne) {
        int count = 0;
        int position = 0;

        for (String row : input) {
            char[] commands = row.toCharArray();
            for (int i = 0; i < commands.length; i++) {
                position++;

                char command = commands[i];
                if (command == '(')
                    count++;
                if (command == ')')
                    count--;

                if (shouldExitAtMinusOne && count == -1)
                    return position;
            }
        }

        return count;
    }
}
