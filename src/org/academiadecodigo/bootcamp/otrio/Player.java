package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.HashMap;
import java.util.Iterator;

public class Player implements KeyboardHandler{

    private HashMap<Double, String> hasMap = new HashMap();

    private Keyboard keyboard;
    private GameField gameField;
    private Rectangle rectangle;


    private int x = GameField.getX();
    private int y = GameField.getY();
    private int CELL_SIZE = GameField.getCellSize();

    private int smallCircleCount = 0;
    private int mediumCircleCount = 0;
    private int bigCircleCount = 0;

    public Player() {
        keyboard = new Keyboard(this);
        rectangle = new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
        rectangle.setColor(Color.RED);
        rectangle.draw();
        init();
    }

    public void init() {
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


        switch(keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                if (rectangle.getY() == y - 100) {
                    System.out.println("You can't go up!");
                    break;
                }
                rectangle.translate(0, -CELL_SIZE);
                break;
            case KeyboardEvent.KEY_A:
                if (rectangle.getX() == x - 100) {
                    System.out.println("You can't go left!");
                    break;
                }
                rectangle.translate(-CELL_SIZE, 0);
                break;
            case KeyboardEvent.KEY_S:
                if (rectangle.getY() == y + 100) {
                    System.out.println("You can't go down!");
                    break;
                }
                rectangle.translate(0, CELL_SIZE);
                break;
            case KeyboardEvent.KEY_D:
                if (rectangle.getX() == x + 100) {
                    System.out.println("You can't go right!");
                    break;
                }
                rectangle.translate(CELL_SIZE, 0);
                break;
            case KeyboardEvent.KEY_J:

                if(smallCircleCount == 3) {
                    System.out.println("You had already drawn 3 small circles!");
                    break;
                }

                if (hasMap.get(rectangle.getX() + 37.5 + rectangle.getY() + 37.5) == "small") {
                    System.out.println("You can't draw in the same place!");
                    break;
                }

                Ellipse smallCircle = new Ellipse(rectangle.getX() + 37.5, rectangle.getY() + 37.5, CELL_SIZE - 75, CELL_SIZE - 75);
                smallCircle.setColor(Color.BLUE);
                smallCircle.draw();
                smallCircleCount++;
                System.out.println("Small circles 3 | " + smallCircleCount);

                hasMap.put((rectangle.getX() + 37.5 + rectangle.getY() + 37.5), "small");

                break;
            case KeyboardEvent.KEY_K:

                if(mediumCircleCount == 3) {
                    System.out.println("You had already drawn 3 medium circles!");
                    break;
                }

                if (hasMap.get(rectangle.getX() + 25.0 + rectangle.getY() + 25.0) == "medium") {
                    System.out.println("You can't draw in the same place!");
                    break;
                }

                Ellipse mediumCircle = new Ellipse(rectangle.getX() + 25, rectangle.getY() + 25, CELL_SIZE - 50, CELL_SIZE - 50);
                mediumCircle.setColor(Color.BLUE);
                mediumCircle.draw();
                mediumCircleCount++;
                System.out.println("Medium circles 3 | " + mediumCircleCount);

                hasMap.put((rectangle.getX() + 25.0 + rectangle.getY() + 25.0), "medium");

                break;
            case KeyboardEvent.KEY_L:

                if(bigCircleCount == 3) {
                    System.out.println("You had already drawn 3 big circles!");
                    break;
                }

                if (hasMap.get(rectangle.getX() + 12.5 + rectangle.getY() + 12.5) == "big") {
                    System.out.println("You can't draw in the same place!");
                    break;
                }

                Ellipse bigCircle = new Ellipse(rectangle.getX() + 12.5, rectangle.getY() + 12.5, CELL_SIZE - 25, CELL_SIZE - 25);
                bigCircle.setColor(Color.BLUE);
                bigCircle.draw();
                bigCircleCount++;
                System.out.println("Big circles 3 | " + bigCircleCount);

                hasMap.put((rectangle.getX() + 12.5 + rectangle.getY() + 12.5), "big");

                break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
