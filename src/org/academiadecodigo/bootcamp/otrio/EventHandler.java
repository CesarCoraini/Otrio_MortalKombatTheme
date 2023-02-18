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


    private HashMap<Integer, String> hasMap = new HashMap<>();
    boolean[][] checkSmallPosition = new boolean[3][3];
    private Player[] players;
    private static int counter = 0;

    private int currentPlayerIndex;

    private int x = GameField.getX();
    private int y = GameField.getY();
    private int CELL_SIZE = GameField.getCellSize();
    public EventHandler(Player[] players) {
        this.players = players;

    }

    public void init() {

        //this.currentPlaterIndex = index;


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

                double x = players[currentPlayerIndex].getPointer().getX() + 37.5;
                double y = players[currentPlayerIndex].getPointer().getY() + 37.5;

                double width = CELL_SIZE - 75;
                double height = CELL_SIZE - 75;

                Color playerColor = players[currentPlayerIndex].getColor();

                Integer key = counter;
                String value = "small";

                System.out.println(players[currentPlayerIndex].getPointer().getX() - 150);

                /*if (checkSmallPosition[][] == true){

                    System.out.println("You can't draw in the same place!");
                    break;
                }*/

                Ellipse smallCircle = new Ellipse(x, y, width, height);
                smallCircle.setColor(playerColor);
                smallCircle.draw();

                //checkSmallPosition[][] = true;

                hasMap.put(key, value);

                if(counter < 8) {
                    counter++;
                }

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

            /*case KeyboardEvent.KEY_K:

                if (Objects.equals(hasMap.get(players[currentPlayerIndex].getPointer().getX() + 25.0 + players[currentPlayerIndex].getPointer().getY() + 25.0), "medium")) {
                    System.out.println("You can't draw in the same place!");
                    break;
                }

                Ellipse mediumCircle = new Ellipse(players[currentPlayerIndex].getPointer().getX() + 25, players[currentPlayerIndex].getPointer().getY() + 25, CELL_SIZE - 50, CELL_SIZE - 50);
                mediumCircle.setColor(players[currentPlayerIndex].getColor());
                mediumCircle.draw();



                hasMap.put((players[currentPlayerIndex].getPointer().getX() + 25.0 + players[currentPlayerIndex].getPointer().getY() + 25.0), "medium");

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

                if (Objects.equals(hasMap.get(players[currentPlayerIndex].getPointer().getX() + 12.5 + players[currentPlayerIndex].getPointer().getY() + 12.5), "big")) {
                    System.out.println("You can't draw in the same place!");
                    break;
                }

                Ellipse bigCircle = new Ellipse(players[currentPlayerIndex].getPointer().getX() + 12.5, players[currentPlayerIndex].getPointer().getY() + 12.5, CELL_SIZE - 25, CELL_SIZE - 25);
                bigCircle.setColor(players[currentPlayerIndex].getColor());
                bigCircle.draw();

                hasMap.put((players[currentPlayerIndex].getPointer().getX() + 12.5 + players[currentPlayerIndex].getPointer().getY() + 12.5), "big");

                players[currentPlayerIndex].setCanMove(false);

                if (Game.getTurn() == 3){
                    currentPlayerIndex = 0;
                    Game.setTurn(0);
                } else {
                    Game.setTurn(currentPlayerIndex += 1);
                    System.out.println(currentPlayerIndex);
                }
                Game.nextTurn();
                break;*/

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    public boolean[][] getCheckSmallPosition() {
        return checkSmallPosition;
    }
}
