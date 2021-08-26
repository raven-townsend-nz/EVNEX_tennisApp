package main;

public class Set {

    /** The name entered by the user for player 1 */
    private final String player1;

    /** The name entered by the user for player 2 */
    private final String player2;

    /** The number of sets player 1 has won */
    private int player1Score;

    /** The number of sets player 2 has won */
    private int player2Score;

    /** If a player reaches this score and are ahead by winningMargin, then they win */
    private static final int WINNING_SCORE = 6;

    /** If a player is ahead by this margin and they reach the WINNING_SCORE then they win the set */
    private static final int WINNING_MARGIN = 2;

    /** If a player reaches this score they win no matter the margin */
    private static final int MAX_WINNING_SCORE = 7;

    /** The current game being played */
    private Game game;


    /**
     * Create a new set with two player names. Both player's scores will start at zero
     */
    public Set(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
        startNewGame();
    }


    /**
     * This method should be called when the current game has been won by a player so they need to start a new game.
     * If either player is about to reach the MAX_WINNING_SCORE then the game will be scored as a tie breaker.
     */
    private void startNewGame() {
        boolean isTiebreaker = player1Score == MAX_WINNING_SCORE - 1 && player2Score == MAX_WINNING_SCORE - 1;
        game = new Game(player1, player2, isTiebreaker);
    }


    /**
     * Adds a point to player 1 in the current game, and if they have won then increases the number of sets they have
     * won and starts a new game.
     */
    public void player1WinsPoint() {
        game.player1Scores();
        if (game.getWinner() == 1) {
            player1Score++;
            startNewGame();
        }
    }


    /**
     * Adds a point to player 2 in the current game, and if they have won then increases the number of sets they have
     * won and starts a new game.
     */
    public void player2WinsPoint() {
        game.player2Scores();
        if (game.getWinner() == 2) {
            player2Score++;
            startNewGame();
        }
    }


    /**
     * Get a display of the current set and game score
     * @return a string of the current set and game score
     */
    public String getScore() {
        if (getWinner() == -1) {
            return player1Score + "-" + player2Score + ", " + game.getScore();
        } else {
            return player1Score + "-" + player2Score;
        }
    }


    /**
     * Returns 1 if player 1 has won this set, or 2 if player 2 has won. Returns -1 if no one has won.
     * @return an integer representing which player has won the set
     */
    public int getWinner() {
        if (player1Score >= WINNING_SCORE && player1Score >= player2Score + WINNING_MARGIN
                || player1Score == MAX_WINNING_SCORE) {
            return 1;
        } else if (player2Score >= WINNING_SCORE && player2Score >= player1Score + WINNING_MARGIN
                || player2Score == MAX_WINNING_SCORE) {
            return 2;
        } else {
            return -1;
        }
    }
}
