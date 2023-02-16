package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    /*private static final int PADDING = 10;
    private int turn = 0;
    private GameFrame gameFrame;
    private GameField gameField;

    private Player player;

    public Game() {
        gameFrame = new GameFrame();
        gameField = new GameField();
        player = new Player();
    }*/

    private static Player[] players;

    private int turn = 0;
    private static int currentPlayerIndex;
    private GameFrame gameFrame;
    private GameField gameField;
    private Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.PINK};

    public Game(int num) {
        gameFrame = new GameFrame();
        gameField = new GameField();
        //displayPlayers(generatePlayers(num));
        generatePlayers(num);

    }

    public  Player[] generatePlayers(int numPlayers) {
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            int name = i + 1;
            Player player = new Player(name, 0, colors[i]);
            players[i] = player;
        }

        return players;
    }

    public void displayPlayers(Player[] players) {
        int numPlayers = players.length;
        int rectWidth = 200 + (numPlayers - 2) * 100;
        int rectHeight = 80 + (numPlayers - 2) * 40;
        int rectX = 50;
        int rectY = 50;

        // Create rectangle
        Rectangle rect = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        rect.setColor(Color.BLACK);
        rect.draw();

        // Add player names and scores
        for (int i = 0; i < numPlayers; i++) {
            int x = rectX + 20 + i * 100;
            int y = rectY + 20 + i * 20;
            Player player = players[i];
            Text nameText = new Text(x, y, "Player " + player.getName());
            nameText.draw();
            Text scoreText = new Text(x, y + 20, "Score: " + player.getScore());
            scoreText.draw();
        }
    }

    /*public void turn() {

        while (true) {
            if(turn % 2 == 0){
                players[0].init();
                turn++;
            } else {
                players[1].init();
                turn++;
            }
        }*/



}
