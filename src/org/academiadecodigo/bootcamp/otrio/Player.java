package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.HashMap;

public class Player{
    private int name;
    private int score;
    private Color color;
    private Rectangle pointer;
    private Picture newPointer;
    private String[] pointersURL = new String[] {"resources/Player1.png", "resources/Player2.png", "resources/Player3.png", "resources/Player4.png"};

    private boolean canMove;

    private int x = GameField.getX();
    private int y = GameField.getY();
    private int CELL_SIZE = GameField.getCellSize();


    private boolean[][] checkSmallCircles;
    private boolean[][] checkMediumCircleCount;
    private boolean[][] checkBigCircleCount;

    public Player(int name, int score, Color color) {

        this.name = name;
        this.score = score;
        this.color = color;

        checkSmallCircles = new boolean[3][3];
        for(int i = 0; i < 3; i++) {
            checkSmallCircles[0][i] = false;
            checkSmallCircles[1][i] = false;
            checkSmallCircles[2][i] = false;
        }

        checkMediumCircleCount = new boolean[3][3];
        for(int i = 0; i < 3; i++) {
            checkMediumCircleCount[0][i] = false;
            checkMediumCircleCount[1][i] = false;
            checkMediumCircleCount[2][i] = false;
        }

        checkBigCircleCount = new boolean[3][3];
        for(int i = 0; i < 3; i++) {
            checkBigCircleCount[0][i] = false;
            checkBigCircleCount[1][i] = false;
            checkBigCircleCount[2][i] = false;
        }

        pointer = new Rectangle(x - CELL_SIZE, y - CELL_SIZE, CELL_SIZE, CELL_SIZE);
        pointer.setColor(color);

        newPointer = new Picture(x - CELL_SIZE, y - CELL_SIZE, pointersURL[name]);

    }

    public boolean hasWon() {
        //Check for a win in each col
        for(int i = 0; i < 3; i++) {
            if ((checkSmallCircles[0][i] && checkSmallCircles[1][i] && checkSmallCircles[2][i]) ||
                    (checkMediumCircleCount[0][i] && checkMediumCircleCount[1][i] && checkMediumCircleCount[2][i]) ||
                    (checkBigCircleCount[0][i] && checkBigCircleCount[1][i] && checkBigCircleCount[2][i]) ||
                    (checkSmallCircles[0][i] && checkMediumCircleCount[1][i] && checkBigCircleCount[2][i]) ||
                    (checkBigCircleCount[0][i] && checkMediumCircleCount[1][i] && checkSmallCircles[2][i])) {
                return  true;
            }
        }

        //Check for a win in each row
        for(int i = 0; i < 3; i++) {
            if ((checkSmallCircles[i][0] && checkSmallCircles[i][1] && checkSmallCircles[i][2]) ||
                    (checkMediumCircleCount[i][0] && checkMediumCircleCount[i][1] && checkMediumCircleCount[i][2]) ||
                    (checkBigCircleCount[i][0] && checkBigCircleCount[i][1] && checkBigCircleCount[i][2]) ||
                    (checkSmallCircles[i][0] && checkMediumCircleCount[i][1] && checkBigCircleCount[i][2]) ||
                    (checkBigCircleCount[i][0] && checkMediumCircleCount[i][1] && checkSmallCircles[i][2])) {
                return  true;
            }
        }

        //Check diagonal
        if ((checkSmallCircles[0][0] && checkSmallCircles[1][1] && checkSmallCircles[2][2]) ||
                (checkMediumCircleCount[0][0] && checkMediumCircleCount[1][1] && checkMediumCircleCount[2][2]) ||
                (checkBigCircleCount[0][0] && checkBigCircleCount[1][1] && checkBigCircleCount[2][2]) ||
                (checkSmallCircles[0][0] && checkMediumCircleCount[1][1] && checkBigCircleCount[2][2]) ||
                (checkBigCircleCount[0][0] && checkMediumCircleCount[1][1] && checkSmallCircles[2][2])) {
            return true;
        }

        //Check anti-diagonal
        if ((checkSmallCircles[0][2] && checkSmallCircles[1][1] && checkSmallCircles[2][0]) ||
                (checkMediumCircleCount[0][2] && checkMediumCircleCount[1][1] && checkMediumCircleCount[2][0]) ||
                (checkBigCircleCount[0][2] && checkBigCircleCount[1][1] && checkBigCircleCount[2][0]) ||
                (checkSmallCircles[0][2] && checkMediumCircleCount[1][1] && checkBigCircleCount[2][0]) ||
                (checkBigCircleCount[0][2] && checkMediumCircleCount[1][1] && checkSmallCircles[2][0])) {
            return true;
        }

        //check same cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (checkSmallCircles[i][j] && checkMediumCircleCount[i][j] && checkBigCircleCount[i][j]) {
                    return  true;
                }

            }

        }




        //if no win was found
        return false;
    }

    public int getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rectangle getPointer() {
        return pointer;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


    public void setCheckSmallCircles(boolean checkSmallCircles, int row, int col) {
        this.checkSmallCircles[row][col] = checkSmallCircles;
    }

    public void setCheckMediumCircle(boolean checkMediumCircles, int row, int col) {
        this.checkMediumCircleCount[row][col] = checkMediumCircles;
    }

    public void setCheckBigCircle(boolean checkBigCircles, int row, int col) {
        this.checkBigCircleCount[row][col] = checkBigCircles;
    }

    public void resetBooleans() {
        for(int i = 0; i < 3; i++) {
            checkSmallCircles[0][i] = false;
            checkSmallCircles[1][i] = false;
            checkSmallCircles[2][i] = false;

            checkMediumCircleCount[0][i] = false;
            checkMediumCircleCount[1][i] = false;
            checkMediumCircleCount[2][i] = false;

            checkBigCircleCount[0][i] = false;
            checkBigCircleCount[1][i] = false;
            checkBigCircleCount[2][i] = false;
        }
    }

    public Picture getNewPointer() {
        return newPointer;
    }
}
