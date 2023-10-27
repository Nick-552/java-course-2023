package edu.project2.solver;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import java.util.List;
import java.util.Stack;

public class DfsSolver implements MazeSolver {
    private final Stack<Coordinate> stack = new Stack<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        stack.push(start);
        return null;
    }
}

