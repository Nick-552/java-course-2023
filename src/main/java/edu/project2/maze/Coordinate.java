package edu.project2.maze;

public record Coordinate(int row, int col) {
    public Coordinate computeCellOffset(Offset offset) {
        int newRow = this.row + offset.getRow();
        int newCol = this.col + offset.getColumn();
        return new Coordinate(newRow, newCol);
    }

    public Coordinate computeWallOffset(Offset offset) {
        int newRow = this.row + offset.getRow() / 2;
        int newCol = this.col + offset.getColumn() / 2;
        return new Coordinate(newRow, newCol);
    }
}
