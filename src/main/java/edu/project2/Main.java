package edu.project2;

import edu.project2.generator.MazeRecursiveBacktrackerGenerator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.UnicodeDefaultMazeRenderer;
import edu.project2.solver.DfsSolver;
import edu.project2.solver.MazeSolver;

public final class Main {

    private Main() {}

    @SuppressWarnings({"checkstyle:RegexpSinglelineJava", "checkstyle:MagicNumber"})
    public static void main(String[] args) {
        Maze maze = new MazeRecursiveBacktrackerGenerator(31, 61).generate();
        MazeRenderer mazeRenderer = new UnicodeDefaultMazeRenderer();
        MazeSolver mazeSolver = new DfsSolver();

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
