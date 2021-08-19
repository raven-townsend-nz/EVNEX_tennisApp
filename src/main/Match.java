package main;

public class Match {

    /** The name entered for player 1 */
    private final String player1;

    /** The name entered for player 2 */
    private final String player2;

    /** The single set for this match */
    private final Set set;


    /**
     * Create a new match with two player names
     */
    public Match(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.set = new Set(player1, player2);
    }


    /**
     * Return a string representation of the score. Additionally adds which player won if the game has ended
     */
    public String score() {
        if (set.getWinner() == 1) {
            return set.getScore() + " => " + player1 + " wins!";
        } else if (set.getWinner() == 2) {
            return set.getScore() + " => " + player2 + " wins!";
        } else {
            return set.getScore();
        }
    }

    /**
     * This method should be called if a player has won a point
     * @param player the name of the player
     */
    public void pointWonBy(String player) {
        if (player.equals(player1) && set.getWinner() == -1) {
            set.player1WinsPoint();
        } else if (player.equals(player2) && set.getWinner() == -1) {
            set.player2WinsPoint();
        }
    }
}
