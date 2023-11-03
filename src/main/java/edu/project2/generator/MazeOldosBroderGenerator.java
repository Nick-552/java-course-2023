package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MazeOldosBroderGenerator extends AbstractMazeGenerator {

    public MazeOldosBroderGenerator(int height, int width) {
        super(height, width);
    }

    @Override
    public Maze generate() {
        setEmptyGrid();
        fillAllWalls();
        int amountOfVisited = 1;
        int amountOfTrueCells = numberOfTrueCells();
        Coordinate currentCoordinate = new Coordinate(1, 1);
        visited[1][1] = true;
        while (amountOfVisited < amountOfTrueCells) {
            Coordinate finalCurrentCoordinate = currentCoordinate;
            var possibleOffsets = new ArrayList<>(Arrays.stream(Offset.values())
                .filter((offset -> hasOffset(finalCurrentCoordinate, offset)))
                .toList());
            Collections.shuffle(possibleOffsets);
            var chosenOffset = possibleOffsets.get(0);
            var newCoordinate = currentCoordinate.computeCellOffset(chosenOffset);
            if (!visited[newCoordinate.row()][newCoordinate.col()]) {
                var newPassageCoordinate = currentCoordinate.computeWallOffset(chosenOffset);
                grid[newPassageCoordinate.row()][newPassageCoordinate.col()] =
                    new Cell(newPassageCoordinate, Cell.Type.PASSAGE);
                amountOfVisited++;
                visited[newCoordinate.row()][newCoordinate.col()] = true;
            }
            currentCoordinate = newCoordinate;
        }
        return new Maze(height, width, grid);
    }
}
