package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import java.util.HashMap;
import java.util.Objects;

public class EventHandler implements KeyboardHandler {

    //Propriety's for the circles array's
    boolean[][] checkSmallPosition = new boolean[3][3];
    boolean[][] checkMediumPosition = new boolean[3][3];
    boolean[][] checkBigPosition = new boolean[3][3];

    //Player's propriety's
    private Player[] players;
    private int currentPlayerIndex;
    private static int counter = 0;

    //Positions
    private int x = GameField.getX();
    private int y = GameField.getY();
    private int CELL_SIZE = GameField.getCellSize();

    //Constructor
    public EventHandler(Player[] players) {
        this.players = players;

        //Array boolean for small circles
        for(int i = 0; i < 3; i++) {
            checkSmallPosition[0][i] = false;
            checkSmallPosition[1][i] = false;
            checkSmallPosition[2][i] = false;
        }

        //Array boolean for medium circles
        for(int i = 0; i < 3; i++) {
            checkMediumPosition[0][i] = false;
            checkMediumPosition[1][i] = false;
            checkMediumPosition[2][i] = false;
        }

        //Array boolean for big circles
        for(int i = 0; i < 3; i++) {
            checkBigPosition[0][i] = false;
            checkBigPosition[1][i] = false;
            checkBigPosition[2][i] = false;
        }

    }

    public void init() {

        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent smallCircle = new KeyboardEvent();
        smallCircle.setKey(KeyboardEvent.KEY_J);
        smallCircle.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(smallCircle);

        KeyboardEvent mediumCircle = new KeyboardEvent();
        mediumCircle.setKey(KeyboardEvent.KEY_K);
        mediumCircle.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(mediumCircle);

        KeyboardEvent bigCircle = new KeyboardEvent();
        bigCircle.setKey(KeyboardEvent.KEY_L);
        bigCircle.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(bigCircle);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        Color playerColor = players[currentPlayerIndex].getColor();

        //Check player Index
        System.out.println(currentPlayerIndex);

        if(!players[currentPlayerIndex].isCanMove()) {
            return;
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                if (players[currentPlayerIndex].getPointer().getY() == y - 100) {
                    System.out.println("You can't go up!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(0, -CELL_SIZE);
                break;
            case KeyboardEvent.KEY_A:
                if (players[currentPlayerIndex].getPointer().getX() == x - 100) {
                    System.out.println("You can't go left!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(-CELL_SIZE, 0);
                break;
            case KeyboardEvent.KEY_S:
                if (players[currentPlayerIndex].getPointer().getY() == y + 100) {
                    System.out.println("You can't go down!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(0, CELL_SIZE);
                break;
            case KeyboardEvent.KEY_D:
                if (players[currentPlayerIndex].getPointer().getX() == x + 100) {
                    System.out.println("You can't go right!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(CELL_SIZE, 0);
                break;
            case KeyboardEvent.KEY_J:

                double xSmall = players[currentPlayerIndex].getPointer().getX() + 37.5;
                double ySmall = players[currentPlayerIndex].getPointer().getY() + 37.5;

                int rowSmall = (players[currentPlayerIndex].getPointer().getY() - 150) / CELL_SIZE;
                int colSmall = (players[currentPlayerIndex].getPointer().getX() - 250) / CELL_SIZE;

                double widthSmall = CELL_SIZE - 75;
                double heightSmall = CELL_SIZE - 75;

                System.out.println("row:" + rowSmall );
                System.out.println("col:" + colSmall);

                if (checkSmallPosition[rowSmall][colSmall]){

                    System.out.println("You can't draw SMALL CIRCLES in the same place!");
                    break;
                }

                Ellipse smallCircle = new Ellipse(xSmall, ySmall, widthSmall, heightSmall);
                smallCircle.setColor(playerColor);
                smallCircle.draw();

                //set array in row and col position to true
                checkSmallPosition[rowSmall][colSmall] = true;

                //Don't remember what this do xD
                if(counter < 8) {
                    counter++;
                }

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);

                if (Game.getTurn() == 3){
                    currentPlayerIndex = 0;
                    Game.setTurn(0);
                } else {
                    Game.setTurn(currentPlayerIndex += 1);
                    System.out.println(currentPlayerIndex);
                }
                Game.nextTurn();

                break;

            case KeyboardEvent.KEY_K:

                double xMedium = players[currentPlayerIndex].getPointer().getX() + 25;
                double yMedium = players[currentPlayerIndex].getPointer().getY() + 25;

                int rowMedium = (players[currentPlayerIndex].getPointer().getY() - 150) / CELL_SIZE;
                int colMedium = (players[currentPlayerIndex].getPointer().getX() - 250) / CELL_SIZE;

                double widthMedium = CELL_SIZE - 50;
                double heightMedium = CELL_SIZE - 50;

                System.out.println("row:" + rowMedium );
                System.out.println("col:" + colMedium);

                if (checkMediumPosition[rowMedium][colMedium]){

                    System.out.println("You can't draw MEDIUM CIRCLES in the same place!");
                    break;
                }

                Ellipse mediumCircle = new Ellipse(xMedium, yMedium, widthMedium, heightMedium);
                mediumCircle.setColor(players[currentPlayerIndex].getColor());
                mediumCircle.draw();

                //set array in row and col position to true
                checkMediumPosition[rowMedium][colMedium] = true;

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);

                if (Game.getTurn() == 3){
                    currentPlayerIndex = 0;
                    Game.setTurn(0);
                } else {
                    Game.setTurn(currentPlayerIndex += 1);
                    System.out.println(currentPlayerIndex);
                }
                Game.nextTurn();

                break;

            case KeyboardEvent.KEY_L:

                double xBig = players[currentPlayerIndex].getPointer().getX() + 12.5;
                double yBig = players[currentPlayerIndex].getPointer().getY() + 12.5;

                int rowBig = (players[currentPlayerIndex].getPointer().getY() - 150) / CELL_SIZE;
                int colBig = (players[currentPlayerIndex].getPointer().getX() - 250) / CELL_SIZE;

                double widthBig = CELL_SIZE - 25;
                double heightBig = CELL_SIZE - 25;

                System.out.println("row:" + rowBig );
                System.out.println("col:" + colBig);

                if (checkMediumPosition[rowBig][colBig]){

                    System.out.println("You can't draw BIG CIRCLES in the same place!");
                    break;
                }

                Ellipse bigCircle = new Ellipse(xBig, yBig, widthBig, heightBig);
                bigCircle.setColor(players[currentPlayerIndex].getColor());
                bigCircle.draw();

                //set array in row and col position to true
                checkMediumPosition[rowBig][colBig] = true;

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);

                if (Game.getTurn() == 3){
                    currentPlayerIndex = 0;
                    Game.setTurn(0);
                } else {
                    Game.setTurn(currentPlayerIndex += 1);
                    System.out.println(currentPlayerIndex);
                }
                Game.nextTurn();
                break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    public boolean[][] getCheckSmallPosition() {
        return checkSmallPosition;
    }


}
