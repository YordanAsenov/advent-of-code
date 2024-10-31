package com.adventofcode.year2022.day3;

import java.util.List;

import lombok.Data;

@Data
public class Game {
    
    private static int getPriority(char character) {
        if (Character.isUpperCase(character)) {
            return ((int) character - 38);
        } else {
            return ((int) character - 96);
        }
    }
    
    private static String getCommonItems(String line) {
        String firstHalf = line.substring(0, line.length() / 2);
        String secondHalf = line.substring(line.length() / 2);
        
        String commonItems = "";
        for (char item : firstHalf.toCharArray()) {
            if (secondHalf.contains("" + item) && !commonItems.contains("" + item)) {
                commonItems += item;
            }
        }
        
        return commonItems;
    }
    
    public static long solve(List<String> input) {
        long score = 0;
        
        for (String line : input) {
            for (char commonItem : getCommonItems(line).toCharArray()) {
                score += getPriority(commonItem);
            }
        }
    
        return score;
    }
    
    public static long solve2(List<String> input) {
        long score = 0;
        
        for (int i = 0; i < input.size(); i+=3) {
            String firstLineItems = input.get(i);
            String secondLineItems = input.get(i + 1);
            String thirdLineItems = input.get(i + 2);
            
            String commonItems = "";
            for (char item : firstLineItems.toCharArray()) {
                if (secondLineItems.contains("" + item) &&
                    thirdLineItems.contains("" + item) &&
                    !commonItems.contains("" + item)
                ) {
                    commonItems += item;
                }
            }
            
            for (char commonItem : commonItems.toCharArray()) {
                score += getPriority(commonItem);
            }
        }
        
        return score;
    }
}
