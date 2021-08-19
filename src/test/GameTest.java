package test;


import main.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameTest {

    @Test
    void getScore_initialScore_returns0_0() {
        Game game = new Game("player 1", "player 2", false);
        String expected = "0-0";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_nonTieBreaker_returns30_15() {
        Game game = new Game("player 1", "player 2", false);
        game.player1Scores();
        game.player2Scores();
        game.player1Scores();
        String expected = "30-15";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_nonTieBreaker_returns0_40() {
        Game game = new Game("player 1", "player 2", false);
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        String expected = "0-40";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_tieBreaker_returnsCorrectScore() {
        Game game = new Game("player 1", "player 2", true);
        game.player2Scores();
        game.player2Scores();
        game.player2Scores();
        String expected = "0-3";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scoresEqual_returnsDeuce() {
        Game game = new Game("player 1", "player 2", false);
        for (int i = 0; i < 4; i++) {
            game.player1Scores();
            game.player2Scores();
        }
        String expected = "Deuce";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scores4And3_returnsAdvantage() {
        Game game = new Game("player 1", "player 2", false);
        game.player1Scores();
        for (int i = 0; i < 3; i++) {
            game.player1Scores();
            game.player2Scores();
        }
        String expected = "Advantage player 1";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scores6And7_returnsAdvantage() {
        Game game = new Game("P1", "P2", false);
        game.player2Scores();
        for (int i = 0; i < 3; i++) {
            game.player1Scores();
            game.player2Scores();
        }
        String expected = "Advantage P2";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_noWinner_returnsMinusOne() {
        Game game = new Game("player 1", "player 2", false);
        game.player1Scores();
        game.player2Scores();
        game.player2Scores();
        int expected = -1;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_player1Wins_returns1() {
        Game game = new Game("player 1", "player 2", false);
        for (int i = 0; i < 4; i++) {
            game.player1Scores();
        }
        int expected = 1;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_player2WinsAfterDeuce_returns1() {
        Game game = new Game("player 1", "player 2", false);
        for (int i = 0; i < 4; i++) {
            game.player1Scores();
            game.player2Scores();
        }
        game.player2Scores();
        game.player2Scores();
        int expected = 2;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }
}
