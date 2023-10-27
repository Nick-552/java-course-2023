package edu.project2;

import edu.project2.generator.MazeRecursiveBacktrackerGenerator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.UnicodeDefaultMazeRenderer;
import edu.project2.solver.BfsSolver;
import edu.project2.solver.MazeSolver;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        Maze maze = new MazeRecursiveBacktrackerGenerator().generate(31, 61);
        MazeRenderer mazeRenderer = new UnicodeDefaultMazeRenderer();
        MazeSolver mazeSolver = new BfsSolver();

        System.out.println(mazeRenderer.render(maze));
        System.out.println(
            mazeRenderer.render(
                maze,
                mazeSolver.solve(
                    maze,
                    new Coordinate(1, 1),
                    new Coordinate(29, 59)
                )
            )
        );
    }
}
