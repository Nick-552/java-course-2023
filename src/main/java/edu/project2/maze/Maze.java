package edu.project2.maze;

import lombok.Getter;

@Getter
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public boolean hasOffset(Coordinate coordinate, Offset offset) {
        return coordinate.row() + offset.getRow() >= 0
            && coordinate.col() + offset.getColumn() >= 0
            && coordinate.row() + offset.getRow() < height
            && coordinate.col() + offset.getColumn() < width;
    }
}

