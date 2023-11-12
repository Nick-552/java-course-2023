package edu.project2.renderer;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public class UnicodeDefaultMazeRenderer implements MazeRenderer {
    private static final char PASSAGE_SYMBOL = '⬛';
    private static final char WALL_SYMBOL = '⬜';
    private static final String PATH_SYMBOL = "🟢";
    private static final String START_OF_PATH_SYMBOL = "🔻";
    private static final String END_OF_PATH_SYMBOL = "🚩";


    @Override
    public String render(Maze maze) {
        return render(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazeStringBuilder = new StringBuilder(
            maze.height() * (maze.width() + 1) + (path != null ? path.size() : 0)
        );
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                Coordinate currentCoordinate = new Coordinate(i, j);
                if (path != null && path.contains(currentCoordinate)) {
                    if (currentCoordinate.equals(path.getFirst())) {
                        mazeStringBuilder.append(START_OF_PATH_SYMBOL);
                    } else if (currentCoordinate.equals(path.getLast())) {
                        mazeStringBuilder.append(END_OF_PATH_SYMBOL);
                    } else {
                        mazeStringBuilder.append(PATH_SYMBOL);
                    }
                } else {
                    mazeStringBuilder.append(
                        switch (maze.grid()[i][j].type()) {
                            case WALL -> WALL_SYMBOL;
                            case PASSAGE -> PASSAGE_SYMBOL;
                        }
                    );
                }
            }
            mazeStringBuilder.append('\n');
        }
        return mazeStringBuilder.toString();
    }
}
