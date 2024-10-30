package com.adventofcode.year2022.day2;

import java.util.List;

import lombok.Data;

@Data
public class Game {
    private enum Move {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        final int points;
        
        Move(int points) {
            this.points = points;
        }
    }
    
    private static Move parseMove(char c) {
        if (c == 'A' || c == 'X')
            return Move.ROCK;
        if (c == 'B' || c == 'Y')
            return Move.PAPER;
        return Move.SCISSORS;
    }
    
    private static Move parseMove(Move opponentMove, char c) {
        if (c == 'X') { // lose
            if (Move.ROCK == opponentMove) {
                return Move.SCISSORS;
            }
            if (Move.PAPER == opponentMove) {
                return Move.ROCK;
            }
            if (Move.SCISSORS == opponentMove) {
                return Move.PAPER;
            }
        }
        
        if (c == 'Y') { // draw
            if (Move.ROCK == opponentMove) {
                return Move.ROCK;
            }
            if (Move.PAPER == opponentMove) {
                return Move.PAPER;
            }
            if (Move.SCISSORS == opponentMove) {
                return Move.SCISSORS;
            }
        }
        
        if (c == 'Z') { // win
            if (Move.ROCK == opponentMove) {
                return Move.PAPER;
            }
            if (Move.PAPER == opponentMove) {
                return Move.SCISSORS;
            }
            if (Move.SCISSORS == opponentMove) {
                return Move.ROCK;
            }
        }
        
        return null;
    }
    
    private static int checkRound(Move a, Move b) {
        if (a == b) {
            return 3;
        }
        
        if (Move.ROCK == a && Move.SCISSORS == b ||
            Move.PAPER == a && Move.ROCK == b ||
            Move.SCISSORS == a && Move.PAPER == b) {
            return 0;
        }
        
        return 6;
    }
    
    public static long solve(List<String> input) {
        long score = 0;
        
        for (String line : input) {
            Move opponentMove = parseMove(line.charAt(0));
            Move myMove = parseMove(line.charAt(2));
        
            score += (checkRound(opponentMove, myMove) + myMove.points);
        }
        
        return score;
    }
    
    public static long solve2(List<String> input) {
        long score = 0;
        
        for (String line : input) {
            Move opponentMove = parseMove(line.charAt(0));
            Move myMove = parseMove(opponentMove, line.charAt(2));
            
            score += (checkRound(opponentMove, myMove) + myMove.points);
        }
        
        return score;
    }
}
