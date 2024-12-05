package com.adventofcode.year2024.day4;

import lombok.Getter;

import java.util.List;

@Getter
public enum XmasLetters {
    X(0),
    M(1),
    A(2),
    S(3);

    XmasLetters(int order) {
        this.order = order;
    }

    private final int order;

    public List<XmasLetters> getPrevious() {
        return switch (this) {
            case X -> List.of();
            case M -> List.of(X);
            case A -> List.of(X, M);
            case S -> List.of(X, M, A);
        };
    }

    public boolean isPrevious(XmasLetters other) {
        return this.order - other.order == 1;
    }

    public boolean isNext(XmasLetters other) {
        return other.order - this.order == 1;
    }

    public List<XmasLetters> getNext() {
        return switch (this) {
            case X -> List.of(M, A, S);
            case M -> List.of(A, S);
            case A -> List.of(S);
            case S -> List.of();
        };
    }
}
