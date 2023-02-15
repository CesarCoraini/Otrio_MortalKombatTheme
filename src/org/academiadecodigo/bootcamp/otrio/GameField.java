package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GameField {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static  int CELL_SIZE = 100;
    private static int X = CELL_SIZE + 250;
    private  static int Y = CELL_SIZE + 150;

    private Rectangle[][] cellsBoard = new Rectangle[ROWS][COLS];

    public GameField() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle cellBoard = new Rectangle(col * CELL_SIZE + 250, row * CELL_SIZE + 150, CELL_SIZE, CELL_SIZE);
                cellBoard.draw();
                cellsBoard[row][col] = cellBoard;
            }

        }
    }

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    public static int getCellSize() {
        return CELL_SIZE;
    }


}
