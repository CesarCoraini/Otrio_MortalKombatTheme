package org.academiadecodigo.bootcamp.otrio;

public class Game {

    private static final int PADDING = 10;
    private int turn = 0;
    private GameFrame gameFrame;
    private GameField gameField;

    private Player player1;

    public Game() {
        gameFrame = new GameFrame();
        gameField = new GameField();
        player1 = new Player();
    }


}
