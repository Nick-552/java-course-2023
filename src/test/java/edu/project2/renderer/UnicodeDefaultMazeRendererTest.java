package edu.project2.renderer;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class UnicodeDefaultMazeRendererTest {
    private static Stream<Arguments> gridSourceWithoutPath() {
        return Stream.of(
            Arguments.of(
                3, 3, new Cell[][]{
                    new Cell[] {
                        new Cell(new Coordinate(0, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 2), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(1, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(1, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(1, 2), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(2, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 2), Cell.Type.WALL)
                    }
                }, """
                 ⬜⬜⬜
                 ⬜⬜⬜
                 ⬜⬜⬜
                 """
            ),
            Arguments.of(
                3, 3, new Cell[][]{
                    new Cell[] {
                        new Cell(new Coordinate(0, 0), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(0, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(0, 2), Cell.Type.PASSAGE)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(1, 0), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 2), Cell.Type.PASSAGE)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(2, 0), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(2, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(2, 2), Cell.Type.PASSAGE)
                    }
                }, """
                 ⬛⬛⬛
                 ⬛⬛⬛
                 ⬛⬛⬛
                 """
            ),
            Arguments.of(
                3, 3, new Cell[][]{
                    new Cell[] {
                        new Cell(new Coordinate(0, 0), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(0, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(0, 2), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(1, 0), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(1, 2), Cell.Type.PASSAGE)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(2, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 1), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 2), Cell.Type.PASSAGE)
                    }
                }, """
                 ⬛⬜⬜
                 ⬛⬜⬛
                 ⬜⬜⬛
                 """
            )
        );
    }


    private static Stream<Arguments> gridSourceWithPath() {
        return Stream.of(
            Arguments.of(
                5, 5, new Cell[][]{
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
                        new Cell(new Coordinate(1, 2), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 3), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(1, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(2, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(2, 2), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 3), Cell.Type.WALL),
                        new Cell(new Coordinate(2, 4), Cell.Type.WALL)
                    },
                    new Cell[] {
                        new Cell(new Coordinate(3, 0), Cell.Type.WALL),
                        new Cell(new Coordinate(3, 1), Cell.Type.PASSAGE),
                        new Cell(new Coordinate(3, 2), Cell.Type.PASSAGE),
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
                List.of(
                    new Coordinate(1, 1),
                    new Coordinate(2, 1),
                    new Coordinate(3, 1),
                    new Coordinate(3, 2),
                    new Coordinate(3, 3)
                    ),
                """
                 ⬜⬜⬜⬜⬜
                 ⬜🔻⬛⬛⬜
                 ⬜🟢⬜⬜⬜
                 ⬜🟢🟢🚩⬜
                 ⬜⬜⬜⬜⬜
                 """
            )
        );
    }

    @ParameterizedTest
    @DisplayName("grids")
    @MethodSource("gridSourceWithoutPath")
    void renderMaze_shouldWorkCorrectly_whenNoPath(int height,  int width, Cell[][] grid, String expected) {

        assertThat(new UnicodeDefaultMazeRenderer()
            .render(new Maze(height, width, grid)))
            .isEqualTo(expected);
    }


    @ParameterizedTest
    @DisplayName("grids with path")
    @MethodSource("gridSourceWithPath")
    void renderMaze_shouldWorkCorrectly_whenPath(int height,  int width, Cell[][] grid, List<Coordinate> path, String expected) {

        assertThat(new UnicodeDefaultMazeRenderer()
            .render(new Maze(height, width, grid), path))
            .isEqualTo(expected);
    }

}
