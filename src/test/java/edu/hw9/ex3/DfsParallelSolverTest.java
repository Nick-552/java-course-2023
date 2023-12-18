package edu.hw9.ex3;

import edu.project2.generator.MazeGenerator;
import edu.project2.generator.MazeOldosBroderGenerator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.UnicodeDefaultMazeRenderer;
import edu.project2.solver.MazeSolver;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class DfsParallelSolverTest {

    @Test
    void solve() {
        MazeGenerator generator = new MazeOldosBroderGenerator(15, 15);
        Maze maze = generator.generate();
        MazeSolver solver = new DfsParallelSolver();
        MazeRenderer renderer = new UnicodeDefaultMazeRenderer();
        var path = solver.solve(maze, new Coordinate(1, 1), new Coordinate(13, 13));
        assertThat(path).isNotNull();
        log.info("\n" + renderer.render(maze, path));
    }
}
