package test;

import main.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetTest {

    @Test
    void getScore_initialScore_returns0All() {
        Set set = new Set("player 1", "player 2");
        String expected = "0-0, 0-0";
        String actual = set.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getScore_player1WinsGame_returnsCorrectScore() {
        Set set = new Set("player 1", "player 2");
        for (int i = 0; i < 4; i++) {
            set.player1WinsPoint();
        }
        String expected = "1-0, 0-0";
        String actual = set.getScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_player1WinsSet_returnsCorrectWinner() {
        Set set = new Set("player 1", "player 2");
        for (int i = 0; i < 24; i++) {
            set.player1WinsPoint();
        }
        int expected = 1;
        int actual = set.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_playersWin6SetsEach_returnsNoWinner() {
        Set set = new Set("player 1", "player 2");
        winsGamesEach(6, set);
        int expected = -1;
        int actual = set.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getWinner_playersWin6SetsEachThenPlayer2Wins_returnsNoWinner() {
        Set set = new Set("player 1", "player 2");
        winsGamesEach(6, set);
        for (int j = 0; j < 4; j++) {
            set.player2WinsPoint();
        }
        int expected = 2;
        int actual = set.getWinner();
        Assertions.assertEquals(expected, actual);
    }

    private void winsGamesEach(int numGames, Set set) {
        for (int i = 0; i < numGames; i++) {
            for (int j = 0; j < 4; j++) {
                set.player1WinsPoint();
            }
            for (int j = 0; j < 4; j++) {
                set.player2WinsPoint();
            }
        }
    }
}
