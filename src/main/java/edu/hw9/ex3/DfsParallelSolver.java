package edu.hw9.ex3;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import edu.project2.solver.MazeSolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DfsParallelSolver implements MazeSolver {

    private final ReentrantLock lock = new ReentrantLock();

    private boolean[][] visited;

    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (ifWrongCoordinate(start) || ifWrongCoordinate(end)) {
            throw new IllegalArgumentException("Coordinate should have only odd numbers greater than 0");
        }
        visited = new boolean[maze.height()][maze.width()];
        visited[start.row()][start.col()] = true;
        return forkJoinPool.invoke(new Way(start, end, maze));
    }

    public class Way extends RecursiveTask<List<Coordinate>> {

        private final Coordinate currentCoordinate;

        private final Coordinate end;

        private final Maze maze;

        public Way(Coordinate currentCoordinate, Coordinate end, Maze maze) {
            this.currentCoordinate = currentCoordinate;
            this.end = end;
            this.maze = maze;
        }

        @Override
        protected List<Coordinate> compute() {
            if (currentCoordinate.equals(end)) {
                var path = new LinkedList<Coordinate>();
                path.add(end);
                return path;
            }
            List<Way> ways = new ArrayList<>();
            for (Offset offset : Arrays.stream(Offset.values())
                .filter((offset) -> maze.hasOffset(currentCoordinate, offset))
                .toList()) {
                Coordinate newCoordinate = currentCoordinate.computeWallOffset(offset);
                if (maze.grid()[newCoordinate.row()][newCoordinate.col()].type() == Cell.Type.PASSAGE
                    && !visited[newCoordinate.row()][newCoordinate.col()]) {
                    try {
                        if (lock.tryLock(1, TimeUnit.MINUTES)) {
                            visited[newCoordinate.row()][newCoordinate.col()] = true;
                        } else {
                            throw new RuntimeException("failed to lock visited");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                    var way = new Way(newCoordinate, end, maze);
                    way.fork();
                    ways.add(way);
                }
            }
            for (var way : ways) {
                var path = way.join();
                if (path != null) {
                    path.addFirst(currentCoordinate);
                    return path;
                }
            }
            return null;
        }
    }
}
