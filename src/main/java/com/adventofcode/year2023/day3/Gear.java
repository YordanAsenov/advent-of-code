package com.adventofcode.year2023.day3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Gear {
    private int row;
    private int column;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gear gear = (Gear) o;
        return row == gear.row && column == gear.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
