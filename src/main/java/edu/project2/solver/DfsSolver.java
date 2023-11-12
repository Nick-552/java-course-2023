package edu.project2.solver;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DfsSolver implements MazeSolver {
    private final Stack<Coordinate> stack = new Stack<>();
    private boolean[][] visited;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (ifWrongCoordinate(start) || ifWrongCoordinate(end)) {
            throw new IllegalArgumentException("Coordinate should have only odd numbers greater than 0");
        }
        if (start.equals(end)) {
            return List.of(start);
        }
        stack.push(start);
        visited = new boolean[maze.height()][maze.width()];
        visited[start.row()][start.col()] = true;
        return dfsRecursion(maze, end);
    }

    private List<Coordinate> dfsRecursion(Maze maze, Coordinate end) {
        Coordinate currentCoordinate = stack.peek();
        if (currentCoordinate.equals(end)) {
            return stack.stream().toList();
        }
        for (Offset offset : Arrays.stream(Offset.values())
            .filter((offset) -> maze.hasOffset(currentCoordinate, offset)).toList()) {
            Coordinate newCoordinate = currentCoordinate.computeWallOffset(offset);
            if (maze.grid()[newCoordinate.row()][newCoordinate.col()].type() == Cell.Type.PASSAGE
                && !visited[newCoordinate.row()][newCoordinate.col()]) {
                visited[newCoordinate.row()][newCoordinate.col()] = true;
                stack.push(newCoordinate);
                var ret = dfsRecursion(maze, end);
                if (ret != null) {
                    return ret.stream().toList();
                }
            }
        }
        stack.pop();
        return null;
    }
}

