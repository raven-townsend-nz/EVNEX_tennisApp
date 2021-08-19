package test;

import main.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchTest {

    @Test
    void score_initialScore_returns0All() {
        Match match = new Match("player 1", "player 2");
        String expected = "0-0, 0-0";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_player1ScoresOnce_returnsCorrectScore() {
        Match match = new Match("player 1", "player 2");
        playerScores(1, match, "player 1");
        String expected = "0-0, 15-0";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_player1WinsSetPlayer2ScoresOnce_returnsCorrectScore() {
        Match match = new Match("player 1", "player 2");
        playerScores(4, match, "player 1");
        playerScores(1, match, "player 2");
        String expected = "1-0, 0-15";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_players1Win6Games_returnsCorrectScoreAndWinner() {
        Match match = new Match("player 1", "player 2");
        playerScores(24, match, "player 1");
        String expected = "6-0 => player 1 wins!";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_player1Wins6Player2Wins5_returnsCorrectScoreAndNoWinner() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 5; i++) {
            playerScores(4, match, "player 1");
            playerScores(4, match, "player 2");
        }
        playerScores(4, match, "player 1");
        String expected = "6-5, 0-0";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_playersWin6GamesEachAndPlayer1ScoresOnce_returnsCorrectScore() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            playerScores(4, match, "player 1");
            playerScores(4, match, "player 2");
        }
        playerScores(1, match, "player 2");
        String expected = "6-6, 0-1";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_playersWin6GamesEachAndDeuceOnLastGame_returnsCorrectScore() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            playerScores(4, match, "player 1");
            playerScores(4, match, "player 2");
        }
        for (int i = 0; i < 4; i++) {
            playerScores(1, match, "player 1");
            playerScores(1, match, "player 2");
        }
        String expected = "6-6, Deuce";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_playersWin6GamesEachAndAdvantageOnLastGAme_returnsCorrectScore() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            playerScores(4, match, "player 1");
            playerScores(4, match, "player 2");
        }
        for (int i = 0; i < 4; i++) {
            playerScores(1, match, "player 1");
            playerScores(1, match, "player 2");
        }
        playerScores(1, match, "player 1");
        String expected = "6-6, Advantage player 1";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void score_playersWin6GamesEachAndPlayer2WinsAnotherGame_returnsCorrectScoreAndWinner() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 6; i++) {
            playerScores(4, match, "player 1");
            playerScores(4, match, "player 2");
        }
        playerScores(4, match, "player 2");
        String expected = "6-7 => player 2 wins!";
        String actual = match.score();
        Assertions.assertEquals(expected, actual);
    }

    private void playerScores(int points, Match match, String player) {
        for (int i = 0; i < points; i++) {
            match.pointWonBy(player);
        }
    }
}
