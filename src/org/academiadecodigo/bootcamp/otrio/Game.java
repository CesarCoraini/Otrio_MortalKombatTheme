package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Game {

    private GameField gameField;
    private GameFrame gameFrame;
    private static Player[] players;
    private Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.PINK};
    private static EventHandler handler;

    private static int turn = 0;
    private static int counter = 3 * 3 * 3;


    public Game(int num) {

        this.gameFrame = new GameFrame();
        this.gameField = new GameField();
        generatePlayers(num);
        displayPlayers(players);
        this.handler = new EventHandler(players);
        handler.init();
    }


    public static void nextTurn() {

        if(counter == 0) {
            System.out.println("Don't have more options to play!");
            return;
        }

        if(players[0].hasWon()) {
            System.out.println("player 0 won");
            return;
        }

        if(players[1].hasWon()) {
            System.out.println("player 1 won");
            return;
        }

        if(players[2].hasWon()) {
            System.out.println("player 2 won");
            return;
        }

        if(players[3].hasWon()) {
            System.out.println("player 3 won");
            return;
        }

        if(getTurn() == 0) {
            players[0].setCanMove(true);
            players[0].getPointer().draw();
        } else if (getTurn() == 1){
            players[1].setCanMove(true);
            players[1].getPointer().draw();
        } else if (getTurn() == 2){
            players[2].setCanMove(true);
            players[2].getPointer().draw();
        } else {
            players[3].setCanMove(true);
            players[3].getPointer().draw();
        }

        counter--;
    }

    public void generatePlayers(int numPlayers) {
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player(i, 0, colors[i]);
            players[i] = player;
        }

    }

    public void displayPlayers(Player[] players) {
        int numPlayers = players.length;
        int rectWidth = 200 + (numPlayers - 2) * 100;
        int rectHeight = (numPlayers - 2) * 40;
        int rectX = 20;
        int rectY = 20;

        // Create rectangle
        Rectangle rect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        rect.setColor(Color.BLACK);
        rect.draw();

        // Add player names and scores
        for (int i = 0; i < numPlayers; i++) {
            int x = rectX + 20 + i * 100;
            int y = rectY + 15;
            Player player = players[i];
            Text nameText = new Text(x, y, "Player " + player.getName());
            nameText.draw();
            Text scoreText = new Text(x, y + 20, "Score: " + player.getScore());
            scoreText.draw();
        }
    }

    public static void setTurn(int turn) {
        Game.turn = turn;
    }

    public static int getTurn() {
        return turn;
    }


}
