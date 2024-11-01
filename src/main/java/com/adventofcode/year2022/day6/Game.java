package com.adventofcode.year2022.day6;

import java.util.List;


public class Game {
    
    private static boolean areDifferent(String prefix) {
        for (char c : prefix.toCharArray()) {
            if (prefix.chars().filter(ch -> ch == c).count() > 1) {
                return false;
            }
        }
        
        return true;
    }
    
    public static int solve(List<String> input) {
        String row = input.getFirst();
        
        int currentIndex = 0;
        while (currentIndex < row.length()) {
            String prefix = row.substring(currentIndex, currentIndex + 4);
            if (areDifferent(prefix)) {
                return currentIndex + prefix.length();
            }
            currentIndex++;
        }
        
        return -1;
    }
    
    public static int solve2(List<String> input) {
        String row = input.getFirst();
        
        int currentIndex = 0;
        while (currentIndex < row.length()) {
            String prefix = row.substring(currentIndex, currentIndex + 14);
            if (areDifferent(prefix)) {
                return currentIndex + prefix.length();
            }
            currentIndex++;
        }
        
        return -1;
    }
}
