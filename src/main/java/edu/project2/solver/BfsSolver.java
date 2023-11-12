package edu.project2.solver;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsSolver implements MazeSolver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (ifWrongCoordinate(start) || ifWrongCoordinate(end)) {
            throw new IllegalArgumentException("Coordinate should have only odd numbers greater than 0");
        }
        Queue<List<Coordinate>> queue = new LinkedList<>();
        final boolean[][] visited = new boolean[maze.height()][maze.width()];
        visited[start.row()][start.col()] = true;
        List<Coordinate> startPath = new LinkedList<>();
        startPath.add(start);
        queue.offer(startPath);
        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate lastCoordinate = currentPath.getLast();
            if (lastCoordinate.equals(end)) {
                return currentPath;
            }
            for (Offset offset : Arrays.stream(Offset.values())
                .filter((offset) -> maze.hasOffset(lastCoordinate, offset))
                .toList()) {
                Coordinate nextCoordinate = lastCoordinate.computeWallOffset(offset);
                if (visited[nextCoordinate.row()][nextCoordinate.col()]
                    || maze.grid()[nextCoordinate.row()][nextCoordinate.col()].type() != Cell.Type.PASSAGE) {
                    continue;
                }
                List<Coordinate> newPath = new LinkedList<>(List.copyOf(currentPath));
                newPath.add(nextCoordinate);
                queue.offer(newPath);
                visited[nextCoordinate.row()][nextCoordinate.col()] = true;
            }
        }
        return null; // no path
    }
}
