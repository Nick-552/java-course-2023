package edu.hw1.ex8;

import java.util.Arrays;

public final class HorseOnBoard {
    private HorseOnBoard() {
    }

    private static final int SIZE = 8;
    private static final Pair[] OFFSETS = {
        new Pair(-2, -1), new Pair(-2, 1),
        new Pair(-1, -2), new Pair(-1, 2),
        new Pair(1, -2), new Pair(1, 2),
        new Pair(2, -1), new Pair(2, 1)
    };

    public static boolean knightBoardCapture(char[][] board) {
        if (board.length != SIZE
            || Arrays.stream(board).anyMatch(line -> line.length != SIZE)
        ) {
            throw new IllegalArgumentException("Wrong board size");
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 1) {
                    continue;
                }
                for (Pair offset : OFFSETS) {
                    int x = i + offset.x;
                    int y = j + offset.y;
                    if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
                        continue;
                    }
                    if (board[x][y] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
