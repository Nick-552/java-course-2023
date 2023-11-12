package edu.project2.maze;

import lombok.Getter;

@Getter
public enum Offset {
    UP(0, -2),
    DOWN(0, 2),
    LEFT(-2, 0),
    RIGHT(2, 0);

    private final int row;
    private final int column;

    Offset(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
