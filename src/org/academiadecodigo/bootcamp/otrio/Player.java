package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.HashMap;

public class Player{
    private int name;
    private int score;
    private Color color;
    private Rectangle pointer;

    private boolean canMove;

    private int x = GameField.getX();
    private int y = GameField.getY();
    private int CELL_SIZE = GameField.getCellSize();


    private int[][] circles; //3 sizes of circles

    private int smallCircleCount = 0;
    private int mediumCircleCount = 0;
    private int bigCircleCount = 0;

    public Player(int name, int score, Color color) {

        this.name = name;
        this.score = score;
        this.color = color;

        circles = new int[3][3];
        for(int i = 0; i < 3; i++) {
            circles[0][i] = 3; //small
            circles[1][i] = 3; //small
            circles[2][i] = 3; //small
        }

        pointer = new Rectangle(x - CELL_SIZE, y - CELL_SIZE, CELL_SIZE, CELL_SIZE);
        pointer.setColor(color);
        //pointer.draw();

    }

    public boolean hasWon() {
        //Check for a win in each row
        for(int i = 0; i < 3; i++) {
            if (circles[0][i] == 0 && circles[1][i] == 0 && circles[2][i] == 0) {
                return  true;
            }
        }

        //Check for a win in each column
        for(int i = 0; i < 3; i++) {
            if (circles[0][i] == 0 && circles[1][i] == 0 && circles[2][i] == 0) {
                return  true;
            }
        }

        //Check diagonal
        if (circles[0][0] == 0 && circles[1][1] == 0 && circles[2][2] == 0) {
            return true;
        }

        //Check anti-diagonal
        if (circles[0][2] == 0 && circles[1][1] == 0 && circles[2][0] == 0) {
            return true;
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

    public int getSmallCircleCount() {
        return smallCircleCount;
    }

    public void setSmallCircleCount(int smallCircleCount) {
        this.smallCircleCount = smallCircleCount;
    }

    public int getMediumCircleCount() {
        return mediumCircleCount;
    }

    public void setMediumCircleCount(int mediumCircleCount) {
        this.mediumCircleCount = mediumCircleCount;
    }

    public int getBigCircleCount() {
        return bigCircleCount;
    }

    public void setBigCircleCount(int bigCircleCount) {
        this.bigCircleCount = bigCircleCount;
    }
}
