package main;

public class Match {

    private String player1;
    private String player2;
    private Set set;

    public Match(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.set = new Set(player1, player2);
    }

    public String score() {
        if (set.getWinner() == 1) {
            return set.getScore() + " => " + player1 + " wins!";
        } else if (set.getWinner() == 2) {
            return set.getScore() + " => " + player2 + " wins!";
        } else {
            return set.getScore();
        }
    }

    public void pointWonBy(String player) {
        if (player.equals(player1) && set.getWinner() == -1) {
            set.player1WinsPoint();
        } else if (player.equals(player2) && set.getWinner() == -1) {
            set.player2WinsPoint();
        }
    }
}
