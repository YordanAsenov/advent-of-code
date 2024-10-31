package com.adventofcode.year2022.day4;

import java.util.List;

public class Game {
    
    public record Range(int min, int max) {
        boolean contains(Range other) {
            return min <= other.min && max >= other.max;
        }
        
        boolean overlap(Range other) {
            return (other.min >= min && other.min <= max) ||
                    (other.max >= min && other.max <= max);
        }
    }
    
    private static boolean fullyContained(Range a, Range b) {
        return a.contains(b) || b.contains(a);
    }
    
    private static boolean overlaps(Range a, Range b) {
        return a.overlap(b) || b.overlap(a);
    }
    
    public static long solve(List<String> input) {
        long count = 0;
        
        for (String line : input) {
            String[] sections = line.split(",");
            String[] firstSection = sections[0].split("-");
            String[] secondSection = sections[1].split("-");
            
            var firstRange = new Range(Integer.parseInt(firstSection[0]), Integer.parseInt(firstSection[1]));
            var secondRange = new Range(Integer.parseInt(secondSection[0]), Integer.parseInt(secondSection[1]));
            
            if (fullyContained(firstRange, secondRange)) {
                count++;
            }
        }
        
        return count;
    }
    
    public static long solve2(List<String> input) {
        long count = 0;
        
        for (String line : input) {
            String[] sections = line.split(",");
            String[] firstSection = sections[0].split("-");
            String[] secondSection = sections[1].split("-");
            
            var firstRange = new Range(Integer.parseInt(firstSection[0]), Integer.parseInt(firstSection[1]));
            var secondRange = new Range(Integer.parseInt(secondSection[0]), Integer.parseInt(secondSection[1]));
            
            if (overlaps(firstRange, secondRange)) {
                count++;
            }
        }
        
        return count;
    }
}
