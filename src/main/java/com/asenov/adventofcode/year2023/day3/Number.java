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

    @Override
    public String toString() {
        return value;
    }

    private boolean symbolBeforeIndex(String row, int numberIndex, List<Character> symbols) {
        if (row.isEmpty() || numberIndex == 0) {
            return false;
        }
        return symbols.contains(row.charAt(numberIndex - 1));
    }

    private int getSymbolIndexBeforeNumber(String row, int numberIndex, Character symbol) {
        if (row.isEmpty() || numberIndex == 0) {
            return -1;
        }

        return row.indexOf(
            symbol,
            numberIndex - 1,
            numberIndex
        );
    }

    private boolean symbolAfterIndex(String row, int numberIndex, List<Character> symbols) {
        if (row.isEmpty() || numberIndex >= (row.length() - this.value.length() - 1)) {
            return false;
        }
        return symbols.contains(row.charAt(numberIndex + this.value.length()));
    }

    private int getSymbolIndexAfterNumber(String row, int numberIndex, Character symbol) {
        if (row.isEmpty() || numberIndex >= (row.length() - this.value.length() - 1)) {
            return -1;
        }

        return row.indexOf(symbol,
            numberIndex + this.value.length(),
            numberIndex + this.value.length() + 1);
    }

    public boolean rowContainsSymbols(String row, int numberIndex, List<Character> symbols) {
        if (row.isEmpty()) {
            return false;
        }
        char[] rowChars = row
            .substring(numberIndex, numberIndex + this.value.length())
            .toCharArray();
        return IntStream.range(0, rowChars.length)
            .anyMatch(i -> symbols.contains(rowChars[i]));
    }

    public int getSymbolIndexInRow(String row, int numberIndex, Character symbol) {
        if (row.isEmpty()) {
            return -1;
        }
        return row.indexOf(
            symbol,
            numberIndex,
            numberIndex + this.value.length()
        );
    }

    private boolean hasAdjacentSymbol(String upperRow, String row, String lowerRow, List<Character> symbols) {
        boolean symbolBeforeNumber = symbolBeforeIndex(row, this.index, symbols);
        boolean symbolAfterNumber = symbolAfterIndex(row, this.index, symbols);
        boolean symbolInUpperRow = rowContainsSymbols(upperRow, this.index, symbols);
        boolean symbolInLowerRow = rowContainsSymbols(lowerRow, this.index, symbols);
        boolean symbolInUpperLeftDiagonal = symbolBeforeIndex(upperRow, this.index, symbols);
        boolean symbolInUpperRightDiagonal = symbolAfterIndex(upperRow, this.index, symbols);
        boolean symbolInLowerLeftDiagonal = symbolBeforeIndex(lowerRow, this.index, symbols);
        boolean symbolInLowerRightDiagonal = symbolAfterIndex(lowerRow, this.index, symbols);

        return symbolBeforeNumber ||
            symbolAfterNumber ||
            symbolInUpperRow ||
            symbolInLowerRow ||
            symbolInUpperLeftDiagonal ||
            symbolInUpperRightDiagonal ||
            symbolInLowerLeftDiagonal ||
            symbolInLowerRightDiagonal;
    }

    public boolean isPartNumber(String upperRow, String row, String lowerRow) {
        List<Character> symbols = List.of('*', '+', '-', '=', '/', '%', '$', '&', '#', '@');
        return hasAdjacentSymbol(upperRow, row, lowerRow, symbols);
    }

    public boolean hasGear(String upperRow, String row, String lowerRow) {
        List<Character> symbols = List.of('*');
        return hasAdjacentSymbol(upperRow, row, lowerRow, symbols);
    }

    public Gear getAdjacentGear(String upperRow, String row, String lowerRow, int rowNumber) {
        Character symbol = '*';

        int symbolIndexBeforeNumber = getSymbolIndexBeforeNumber(row, this.index, symbol);
        if (symbolIndexBeforeNumber != -1) {
            return new Gear(rowNumber, symbolIndexBeforeNumber);
        }

        int symbolIndexAfterNumber = getSymbolIndexAfterNumber(row, this.index, symbol);
        if (symbolIndexAfterNumber != -1) {
            return new Gear(rowNumber, symbolIndexAfterNumber);
        }

        int symbolIndexInUpperRow = getSymbolIndexInRow(upperRow, this.index, symbol);
        if (symbolIndexInUpperRow != -1) {
            return new Gear(rowNumber - 1, symbolIndexInUpperRow);
        }

        int symbolIndexInLowerRow = getSymbolIndexInRow(lowerRow, this.index, symbol);
        if (symbolIndexInLowerRow != -1) {
            return new Gear(rowNumber + 1, symbolIndexInLowerRow);
        }

        int symbolIndexInUpperLeftDiagonal = getSymbolIndexBeforeNumber(upperRow, this.index, symbol);
        if (symbolIndexInUpperLeftDiagonal != -1) {
            return new Gear(rowNumber - 1, symbolIndexInUpperLeftDiagonal);
        }

        int symbolIndexInUpperRightDiagonal = getSymbolIndexAfterNumber(upperRow, this.index, symbol);
        if (symbolIndexInUpperRightDiagonal != -1) {
            return new Gear(rowNumber - 1, symbolIndexInUpperRightDiagonal);
        }

        int symbolIndexInLowerLeftDiagonal = getSymbolIndexBeforeNumber(lowerRow, this.index, symbol);
        if (symbolIndexInLowerLeftDiagonal != -1) {
            return new Gear(rowNumber + 1, symbolIndexInLowerLeftDiagonal);
        }

        int symbolIndexInLowerRightDiagonal = getSymbolIndexAfterNumber(lowerRow, this.index, symbol);
        if (symbolIndexInLowerRightDiagonal != -1) {
            return new Gear(rowNumber + 1, symbolIndexInLowerRightDiagonal);
        }

        return null;
    }
}
