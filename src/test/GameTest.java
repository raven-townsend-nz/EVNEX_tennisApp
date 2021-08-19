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
    void getScore_tieBreakerPlayer2Scores6_returnsCorrectScore() {
        Game game = new Game("player 1", "player 2", true);
        playerScores(6, game, 2);
        String expected = "0-6";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scoresEqual_returnsDeuce() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(3, game, 2);
        playerScores(4, game, 1);
        playerScores(1, game, 2);
        String expected = "Deuce";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scores4And3_returnsAdvantage() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(3, game, 2);
        playerScores(4, game, 1);
        String expected = "Advantage player 1";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_scores5And6_returnsAdvantage() {
        Game game = new Game("P1", "P2", false);
        playerScores(3, game, 1);
        playerScores(4, game, 2);
        playerScores(2, game, 1);
        playerScores(2, game, 2);
        String expected = "Advantage P2";
        String actual = game.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_tieBreaker5To7_correctWinner() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(5, game, 1);
        playerScores(7, game, 2);
        int expected = 2;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_tieBreaker6To7_noWinner() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(6, game, 1);
        playerScores(7, game, 2);
        int expected = -1;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_noWinner_returnsMinusOne() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(1, game, 1);
        playerScores(2, game, 2);
        int expected = -1;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_player1Wins_returns1() {
        Game game = new Game("player 1", "player 2", false);
        playerScores(4, game, 1);
        int expected = 1;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_player2WinsAfterDeuce_returns1() {
        Game game = new Game("player 1", "player 2", false);
        for (int i = 0; i < 4; i++) {
            playerScores(1, game, 1);
            playerScores(1, game, 2);
        }
        playerScores(2, game, 2);
        int expected = 2;
        int actual = game.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    private void playerScores(int points, Game game, int player) {
        for (int i = 0; i < points; i++) {
            if (player == 1) {
                game.player1Scores();
            } else {
                game.player2Scores();
            }
        }
    }
}
