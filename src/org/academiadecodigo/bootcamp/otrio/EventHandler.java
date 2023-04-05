package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.HashMap;
import java.util.Objects;

public class EventHandler implements KeyboardHandler {

    //Propriety's for the circles array's
    private boolean[][] checkSmallPosition = new boolean[3][3];
    private boolean[][] checkMediumPosition = new boolean[3][3];
    private boolean[][] checkBigPosition = new boolean[3][3];

    private Picture[] picturesRepo = new Picture[27];

    private String[] smallUrl = new String[] {"resources/1 - Small.png", "resources/2 - Small.png", "resources/3 - Small.png", "resources/4 - Small.png"};
    private String[] mediumUrl = new String[] {"resources/1 - Medium.png", "resources/2 - Medium.png", "resources/3 - Medium.png", "resources/4 - Medium.png"};
    private String[] bigUrl = new String[] {"resources/1 - Big.png", "resources/2 - Big.png", "resources/3 - Big.png", "resources/4 - Big.png"};

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

        KeyboardEvent cleanBoard = new KeyboardEvent();
        cleanBoard.setKey(KeyboardEvent.KEY_SPACE);
        cleanBoard.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(cleanBoard);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        Color playerColor = players[currentPlayerIndex].getColor();

        //Check player Index
        System.out.println("player key pressed " + currentPlayerIndex);

        if(players[0].hasWon() || players[1].hasWon() || players[2].hasWon() || players[3].hasWon() || counter == 27) {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    cleanBoard();
                    System.out.println("enter space");
                    for (int i = 0; i < Game.getWinnigPicture().length; i++) {
                        Game.getWinnigPicture()[i].delete();
                        System.out.println("delete image" + i);
                    }
                    Game.nextTurn();
                    counter = 0;
                    break;
            }
        }

        if(!players[currentPlayerIndex].isCanMove()) {
            System.out.println("isCanMove activated");
            return;
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                if (players[currentPlayerIndex].getPointer().getY() == y - 100) {
                    System.out.println("You can't go up!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(0, -CELL_SIZE);
                players[currentPlayerIndex].getNewPointer().translate(0, -CELL_SIZE);
                break;
            case KeyboardEvent.KEY_A:
                if (players[currentPlayerIndex].getPointer().getX() == x - 100) {
                    System.out.println("You can't go left!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(-CELL_SIZE, 0);
                players[currentPlayerIndex].getNewPointer().translate(-CELL_SIZE, 0);
                break;
            case KeyboardEvent.KEY_S:
                if (players[currentPlayerIndex].getPointer().getY() == y + 100) {
                    System.out.println("You can't go down!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(0, CELL_SIZE);
                players[currentPlayerIndex].getNewPointer().translate(0, CELL_SIZE);
                break;
            case KeyboardEvent.KEY_D:
                if (players[currentPlayerIndex].getPointer().getX() == x + 100) {
                    System.out.println("You can't go right!");
                    break;
                }
                players[currentPlayerIndex].getPointer().translate(CELL_SIZE, 0);
                players[currentPlayerIndex].getNewPointer().translate(CELL_SIZE, 0);
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
                //smallCircle.draw();

                Picture smallRing = new Picture(xSmall - 37.5, ySmall - 37.5, smallUrl[currentPlayerIndex]);
                smallRing.draw();
                picturesRepo[counter] = smallRing;
                counter++;

                //set array in row and col position to true
                checkSmallPosition[rowSmall][colSmall] = true;

                //set true player choice
                players[currentPlayerIndex].setCheckSmallCircles(true, rowSmall, colSmall);

                //Don't remember what this do xD
                /*if(counter < 8) {
                    counter++;
                }*/

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);
                players[currentPlayerIndex].getPointer().delete();
                players[currentPlayerIndex].getNewPointer().delete();

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
                //mediumCircle.draw();

                Picture mediumRing = new Picture(xMedium - 25, yMedium - 25, mediumUrl[currentPlayerIndex]);
                mediumRing.draw();
                picturesRepo[counter] = mediumRing;
                counter++;

                //set array in row and col position to true
                checkMediumPosition[rowMedium][colMedium] = true;

                //set true player choice
                players[currentPlayerIndex].setCheckMediumCircle(true, rowMedium, colMedium);

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);
                players[currentPlayerIndex].getPointer().delete();
                players[currentPlayerIndex].getNewPointer().delete();

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

                if (checkBigPosition[rowBig][colBig]){

                    System.out.println("You can't draw BIG CIRCLES in the same place!");
                    break;
                }

                Ellipse bigCircle = new Ellipse(xBig, yBig, widthBig, heightBig);
                bigCircle.setColor(players[currentPlayerIndex].getColor());
                //bigCircle.draw();

                Picture bigRing = new Picture(xBig - 12.5, yBig - 12.5, bigUrl[currentPlayerIndex]);
                bigRing.draw();
                picturesRepo[counter] = bigRing;
                counter++;

                //set array in row and col position to true
                checkBigPosition[rowBig][colBig] = true;

                //set true player choice
                players[currentPlayerIndex].setCheckBigCircle(true, rowBig, colBig);

                //set's propriety's for the next turn
                players[currentPlayerIndex].setCanMove(false);
                players[currentPlayerIndex].getPointer().delete();
                players[currentPlayerIndex].getNewPointer().delete();

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

    public void cleanBoard() {
        for (Picture picture : picturesRepo) {
            if(picture != null) {
                picture.delete();
                picture = null;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                checkSmallPosition[i][j] = false;
                checkMediumPosition[i][j] = false;
                checkBigPosition[i][j] = false;
            }
        }

        for (int i = 0; i < 4; i++) {
            players[i].resetBooleans();
        }
    }

}
