package com.asenov.adventofcode.year2023.day3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Number {
    private int index;
    private String value;

    private static List<Character> symbols = List.of('*', '+', '-', '=', '/', '%', '$', '&', '#', '@');

    private boolean symbolBefore(String row, int numberIndex) {
        if (row.isEmpty() || numberIndex == 0) {
            return false;
        }
        return symbols.contains(row.charAt(numberIndex - 1));
    }

    private boolean symbolAfter(String row, int numberIndex) {
        if (row.isEmpty() || numberIndex >= (row.length() - this.value.length() - 1)) {
            return false;
        }
        return symbols.contains(row.charAt(numberIndex + this.value.length()));
    }

    public boolean rowContainsSymbols(String row, int numberIndex) {
        if (row.isEmpty()) {
            return false;
        }
        char[] rowChars = row
            .substring(numberIndex, numberIndex + this.value.length())
            .toCharArray();
        return IntStream.range(0, rowChars.length)
            .anyMatch(i -> symbols.contains(rowChars[i]));
    }

    public boolean isPartNumber(String upperRow, String row, String lowerRow) {
        boolean symbolBeforeNumber = symbolBefore(row, this.index);
        boolean symbolAfterNumber = symbolAfter(row, this.index);
        boolean symbolInUpperRow = rowContainsSymbols(upperRow, this.index);
        boolean symbolInLowerRow = rowContainsSymbols(lowerRow, this.index);
        boolean symbolInUpperLeftDiagonal = symbolBefore(upperRow, this.index);
        boolean symbolInUpperRightDiagonal = symbolAfter(upperRow, this.index);
        boolean symbolInLowerLeftDiagonal = symbolBefore(lowerRow, this.index);
        boolean symbolInLowerRightDiagonal = symbolAfter(lowerRow, this.index);

        return symbolBeforeNumber ||
            symbolAfterNumber ||
            symbolInUpperRow ||
            symbolInLowerRow ||
            symbolInUpperLeftDiagonal ||
            symbolInUpperRightDiagonal ||
            symbolInLowerLeftDiagonal ||
            symbolInLowerRightDiagonal;
    }
}
