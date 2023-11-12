package edu.project2.maze;


public record Maze(int height, int width, Cell[][] grid) {

    public boolean hasOffset(Coordinate coordinate, Offset offset) {
        return coordinate.row() + offset.getRow() >= 0
            && coordinate.col() + offset.getColumn() >= 0
            && coordinate.row() + offset.getRow() < height
            && coordinate.col() + offset.getColumn() < width;
    }
}

