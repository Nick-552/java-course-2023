package edu.project2.solver;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public interface MazeSolver {

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    default boolean ifWrongCoordinate(Coordinate coordinate) {
        return coordinate.row() <= 0 || coordinate.col() <= 0
            || coordinate.row() % 2 != 1 || coordinate.col() % 2 != 1;
    }
}
