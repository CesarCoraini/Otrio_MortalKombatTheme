package org.academiadecodigo.osrosas.mortalkombat.field;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Field implements KeyboardHandler {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final int CELL_SIZE = 100;
    private static final int PADDING = 10;

    private int turn = 0;
    private char[][] board = new char[ROWS][COLS];
    private Rectangle[][] cells = new Rectangle[ROWS][COLS];
    private Text[][] marks = new Text[ROWS][COLS];

    public Field() {
        Keyboard keyboard = new Keyboard(this);

        Rectangle frame = new Rectangle(PADDING, PADDING, 1280,720);
        frame.setColor(Color.RED);
        frame.fill();

        Ellipse circle = new Ellipse(PADDING + 50, PADDING + 50, 75, 75);
        Ellipse circle1 = new Ellipse(PADDING + 50, PADDING + 50, 75, 75);
        Ellipse circle2 = new Ellipse(PADDING + 50, PADDING + 50, 75, 75);

        circle1.grow(20, 20);
        circle2.grow(40, 40);
        circle.draw();
        circle2.draw();
        circle1.draw();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = ' ';
                Rectangle cell = new Rectangle(col * CELL_SIZE + PADDING + 100, row * CELL_SIZE + PADDING + 100, CELL_SIZE, CELL_SIZE);
                cell.setColor(Color.BLUE);
                cell.draw();
                cells[row][col] = cell;

                KeyboardEvent event = new KeyboardEvent();
                event.setKey(KeyboardEvent.KEY_1 + (row * ROWS + col));
                event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
                keyboard.addEventListener(event);
            }
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
