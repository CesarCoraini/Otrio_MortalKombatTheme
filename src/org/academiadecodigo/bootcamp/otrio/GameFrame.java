package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GameFrame {

    private static final int PADDING = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Rectangle gameFrame;


    public GameFrame() {
        gameFrame = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        gameFrame.draw();
    }
}
