package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.maze.Offset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MazeRecursiveBacktrackerGenerator extends AbstractMazeGenerator {
    @Override
    public Maze generate(int height, int width) {
        initMaze(height, width);
        fillAllWalls();
        int cellsVisited = 0;
        Cell currentCell = grid[1][1];
        visited[1][1] = true;
        Stack<Cell> cellStack = new Stack<>();
        cellStack.push(currentCell);
        cellsVisited++;
        int amountOfTrueCells = (this.height - 1) / 2 * (this.width - 1) / 2;
        while (cellsVisited < amountOfTrueCells) {
            List<Offset> unvisitedOffsets = getUnvisitedOffsets(currentCell);
            if (!unvisitedOffsets.isEmpty()) {
                Collections.shuffle(unvisitedOffsets);
                Offset chosenOffset = unvisitedOffsets.get(0);
                Coordinate passageCoordinate = currentCell.coordinate().computeWallOffset(chosenOffset);
                Coordinate neighbourCoordinate = currentCell.coordinate().computeCellOffset(chosenOffset);
                grid[passageCoordinate.row()][passageCoordinate.col()] = new Cell(passageCoordinate, Cell.Type.PASSAGE);
                visited[neighbourCoordinate.row()][neighbourCoordinate.col()] = true;
                cellsVisited++;
                currentCell = grid[neighbourCoordinate.row()][neighbourCoordinate.col()];
                cellStack.push(currentCell);
            } else {
                if (cellStack.isEmpty()) {
                    throw new RuntimeException("Stack empty but generation not finished");
                }
                currentCell = cellStack.pop();
            }
        }
        return new Maze(height, width, grid);
    }

    protected void fillAllWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.WALL);
                }
            }
        }
    }

    protected List<Offset> getUnvisitedOffsets(Cell cell) {
        List<Offset> possibleOffsets = new ArrayList<>();
        for (var offset : Offset.values()) {
            Coordinate coordinate = cell.coordinate().computeCellOffset(offset);
            if (hasOffset(cell.coordinate(), offset)
                && !visited[coordinate.row()][coordinate.col()]) {
                possibleOffsets.add(offset);
            }
        }
        return possibleOffsets;
    }
}
