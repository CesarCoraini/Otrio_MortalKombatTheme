package org.academiadecodigo.bootcamp.otrio;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private GameField gameField;
    private GameFrame gameFrame;
    private static Player[] players;
    private Color[] colors = new Color[]{Color.ORANGE, Color.MAGENTA, Color.BLUE, Color.GRAY};
    private static EventHandler handler;

    private static int turn = 0;
    private static int counter = 3 * 3 * 3;

    private Rectangle scoreFrame;
    private Text[] nameTexts = new Text[4];
    private static Text[] scoreTexts = new Text[4];

    private static Picture[] winnigPicture;



    public Game(int num) {

        this.gameFrame = new GameFrame();
        this.gameField = new GameField();
        generatePlayers(num);
        displayPlayers(players);
        this.handler = new EventHandler(players);
        handler.init();

        winnigPicture = new Picture[]{new Picture(10, 10, "resources/1.png"),
                                        new Picture(10, 10, "resources/2.png"),
                                        new Picture(10, 10, "resources/3.png"),
                                        new Picture(10, 10, "resources/4.png")};

    }


    public static void nextTurn() {
        int score = 0;

        if(counter == 0) {
            System.out.println("Don't have more options to play!");

            return;
        }

        if(players[0].hasWon()) {
            System.out.println("player 0 won");

            score = players[0].getScore() + 1;
            players[0].setScore(score);
            System.out.println(score);

            int x = 20 + 20 + 0 * 100;
            int y = 20 + 15;
            scoreTexts[0].delete();
            scoreTexts[0] = new Text(x, y + 20, "Score: " + players[0].getScore());
            scoreTexts[0].setColor(Color.WHITE);
            scoreTexts[0].draw();
            counter = 27;
            winnigPicture[0].draw();
            return;
        }

        if(players[1].hasWon()) {
            System.out.println("player 1 won");

            score = players[1].getScore() + 1;
            players[1].setScore(score);
            System.out.println(score);

            int x = 20 + 20 + 1 * 100;
            int y = 20 + 15;
            scoreTexts[1].delete();
            scoreTexts[1] = new Text(x, y + 20, "Score: " + players[1].getScore());
            scoreTexts[1].setColor(Color.WHITE);
            scoreTexts[1].draw();
            counter = 27;
            winnigPicture[1].draw();
            return;
        }

        if(players[2].hasWon()) {
            System.out.println("player 2 won");

            score = players[2].getScore() + 1;
            players[2].setScore(score);
            System.out.println(score);

            int x = 20 + 20 + 2 * 100;
            int y = 20 + 15;
            scoreTexts[2].delete();
            scoreTexts[2] = new Text(x, y + 20, "Score: " + players[2].getScore());
            scoreTexts[2].setColor(Color.WHITE);
            scoreTexts[2].draw();
            counter = 27;
            winnigPicture[2].draw();
            return;
        }

        if(players[3].hasWon()) {
            System.out.println("player 3 won");

            score = players[3].getScore() + 1;
            players[3].setScore(score);
            System.out.println(score);

            int x = 20 + 20 + 3 * 100;
            int y = 20 + 15;
            scoreTexts[3].delete();
            scoreTexts[3] = new Text(x, y + 20, "Score: " + players[3].getScore());
            scoreTexts[3].setColor(Color.WHITE);
            scoreTexts[3].draw();
            counter = 27;
            winnigPicture[3].draw();
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
        scoreFrame = new Rectangle(rectX, rectY, rectWidth, rectHeight);
        scoreFrame.setColor(Color.BLACK);
        //scoreFrame.draw();

        // Add player names and scores
        for (int i = 0; i < numPlayers; i++) {
            int x = rectX + 20 + i * 100;
            int y = rectY + 15;
            Player player = players[i];
            Text nameText = new Text(x, y, "Player " + player.getName());
            nameTexts[i] = nameText;
            nameText.setColor(Color.WHITE);
            nameText.draw();
            Text scoreText = new Text(x, y + 20, "Score: " + player.getScore());
            scoreTexts[i] = scoreText;
            scoreText.setColor(Color.WHITE);
            scoreText.draw();
        }
    }

    public static void setTurn(int turn) {
        Game.turn = turn;
    }

    public static int getTurn() {
        return turn;
    }

    public void setScoreTexts(Text scoreTexts, int i) {
        this.scoreTexts[i] = scoreTexts;
    }

    public static Picture[] getWinnigPicture() {
        return winnigPicture;
    }
}
