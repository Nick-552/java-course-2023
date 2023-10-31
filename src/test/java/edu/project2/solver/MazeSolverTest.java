package edu.project2.solver;

import edu.project2.generator.MazeOldosBroderGenerator;
import edu.project2.generator.MazeRecursiveBacktrackerGenerator;
import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.UnicodeDefaultMazeRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MazeSolverTest {

    private static final Random RANDOM = new Random();


    private static Stream<Arguments> wrongCoordinateSource() {
        return Stream.of(
            Arguments.of(new Coordinate(0, 0), new Coordinate(0, 0)),
            Arguments.of(new Coordinate(2, 2), new Coordinate(2, 2)),
            Arguments.of(new Coordinate(-1, -1), new Coordinate(-1, -1))
        );
    }

    private static Stream<Arguments> noPathSource() {
        return Stream.of(
            Arguments.of (
                5, 5,
                new Cell[][]{
                    new Cell[] {
                        new Cell(new Coordinate(0, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 3), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(1, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(1, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(1, 3), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(2, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 3), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(3, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(3, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(3, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(3, 3), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(3, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(4, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(4, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(4, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(4, 3), Cell.Type.WALL),
                        new Cell(new Coordinate(4, 4), Cell.Type.WALL)
                    }
                },
                new Coordinate(1, 1),
                new Coordinate(3, 3)
            )
        );
    }



    @ParameterizedTest
    @DisplayName("Wrong coordinate")
    @MethodSource("wrongCoordinateSource")
    void solve_shouldThrowIllegalArgumentException_whenWrongCoordinate(Coordinate start, Coordinate end) {
        Maze maze = new MazeOldosBroderGenerator().generate(3, 3);
        assertThatIllegalArgumentException().isThrownBy(() -> new BfsSolver().solve(maze, start, end));
        assertThatIllegalArgumentException().isThrownBy(() -> new DfsSolver().solve(maze, start, end));
    }

    @ParameterizedTest
    @DisplayName("No path")
    @MethodSource("noPathSource")
    void solve_shouldReturnNull_whenNoPath(int height, int width, Cell[][] grid, Coordinate start, Coordinate end) {
        Maze maze = new Maze(height, width, grid);
        assertThat(new BfsSolver().solve(maze, start, end)).isNull();
        assertThat(new DfsSolver().solve(maze, start, end)).isNull();
    }


    @Test
    @DisplayName("basic test")
    void solve_shouldSolve_whenGeneratedByMe() {
        for (int i = 0; i < 1000; i++) {
            int height = RANDOM.nextInt(1, 25) * 2 + 1;
            int width = RANDOM.nextInt(1, 25) * 2 + 1;
            int endRow = RANDOM.nextInt(height - 2) / 2 * 2 + 1;
            int endCol = RANDOM.nextInt(width - 2) / 2 * 2 + 1;
            Maze maze1 = new MazeRecursiveBacktrackerGenerator().generate(height, width);
            Maze maze2 = new MazeOldosBroderGenerator().generate(height, width);

            List<Coordinate> path1 = null;
            List<Coordinate> path2 = null;
            List<Coordinate> path3 = null;
            List<Coordinate> path4 = null;
            try {
                path1 = new DfsSolver()
                        .solve(maze1, new Coordinate(1, 1), new Coordinate(endRow, endCol));

                path2 = new DfsSolver()
                        .solve(maze2, new Coordinate(1, 1), new Coordinate(endRow, endCol));

                path3 = new BfsSolver()
                        .solve(maze1, new Coordinate(1, 1), new Coordinate(endRow, endCol));

                path4 = new BfsSolver()
                        .solve(maze2, new Coordinate(1, 1), new Coordinate(endRow, endCol));
            } catch (Error error) {
                System.out.println(error.getMessage());
                System.out.println(endRow + " " + endCol);
                System.out.println(new UnicodeDefaultMazeRenderer().render(maze1));
                System.out.println(new UnicodeDefaultMazeRenderer().render(maze2));
            } finally {
                assertThat(path1).isNotNull();
                assertThat(path2).isNotNull();
                assertThat(path3).isNotNull();
                assertThat(path4).isNotNull();
            }
        }
    }

}
