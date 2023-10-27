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
        Queue<List<Coordinate>> queue = new LinkedList<>();
        final boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        visited[start.row()][start.col()] = true;
        List<Coordinate> startPath = new LinkedList<>();
        startPath.add(start);
        queue.offer(startPath);
        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate lastCoordinate = currentPath.getLast();
            for (Offset offset : Arrays.stream(Offset.values())
                .filter((offset) -> maze.hasOffset(lastCoordinate, offset))
                .toList()) {
                Coordinate nextCoordinate = lastCoordinate.computeWallOffset(offset);
                if (visited[nextCoordinate.row()][nextCoordinate.col()]
                    || maze.getGrid()[nextCoordinate.row()][nextCoordinate.col()].type() != Cell.Type.PASSAGE) {
                    continue;
                }
                List<Coordinate> newPath = new LinkedList<>(List.copyOf(currentPath));
                newPath.add(nextCoordinate);
                if (nextCoordinate.equals(end)) {
                    return newPath;
                } else {
                    queue.offer(newPath);
                }
                visited[nextCoordinate.row()][nextCoordinate.col()] = true;
            }
        }
        throw new RuntimeException("No path");
    }
}
