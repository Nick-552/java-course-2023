package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;

public abstract class AbstractMazeGenerator implements MazeGenerator {

    protected Cell[][] grid;
    protected int height;
    protected int width;
    protected boolean[][] visited;

    protected void initMaze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        visited = new boolean[height][width];
    }

    public abstract Maze generate(int height, int width);

    public boolean hasOffset(Coordinate coordinate, Offset offset) {
        return coordinate.row() + offset.getRow() > 0
            && coordinate.col() + offset.getColumn() > 0
            && coordinate.row() + offset.getRow() < height - 1
            && coordinate.col() + offset.getColumn() < width - 1;
    }

    boolean hasCorrectSize() {
        return height % 2 == 1 && width % 2 == 1;
    }
}
