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
        if (!hasCorrectSize()) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть нечетными и не менее 3");
        }
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
        return height > 2 && width > 2 && height % 2 == 1 && width % 2 == 1;
    }

    protected void fillAllWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.WALL);
                }
            }
        }
    }

    protected int numberOfTrueCells() {
        return (this.height - 1) / 2 * (this.width - 1) / 2;
    }
}
