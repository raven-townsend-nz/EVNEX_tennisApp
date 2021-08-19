package main;

public class Game {

    /** If a player reaches this score and is ahead by WINNING_MARGIN then they win the game */
    private static final int WINNING_SCORE = 4;

    /** If a player reaches the WINNING_SCORE and is ahead by this margin then they win the game */
    private static final int WINNING_MARGIN = 2;

    /** True if this game is a tie breaker, in which case the score will be 1, 2, 3 rather than 15, 30, 40 */
    private final boolean isTieBreaker;

    /** The name entered for player 1 */
    private final String player1;

    /** The name entered for player 2 */
    private final String player2;

    /** The number of points player 1 has (0, 1, 2...) */
    private int player1Points;

    /** The number of points player 2 has (0, 1, 2...) */
    private int player2Points;


    /**
     * Create a new game with two player names and whether the game is a tie breaker.
     * Both player's points will start at zero
     */
    public Game(String player1, String player2, boolean isTieBreaker) {
        this.isTieBreaker = isTieBreaker;
        this.player1 = player1;
        this.player2 = player2;
        player1Points = 0;
        player2Points = 0;
    }


    /**
     * Returns a string of the score for this game. The function assumes that
     * @return returns a string of the game's score
     */
    public String getScore() {

        if (player1Points >= WINNING_SCORE || player2Points >= WINNING_SCORE) {
            if (player1Points == player2Points) {
                return "Deuce";
            } else if (player1Points > player2Points) {
                return "Advantage " + player1;
            } else {
                return "Advantage " + player2;
            }
        }
        else {
            return displayPoints(player1Points) + "-" + displayPoints(player2Points);
        }
    }


    /**
     * Returns a string of the way the given points value is described in tennis
     * @param points the points value to be displayed
     * @return a string describing the given points value according to tennis conventions
     */
    private String displayPoints(int points) {
        if (isTieBreaker) {
            return String.valueOf(points);
        } else {
            switch (points) {
                case 0:
                    return "0";
                case 1:
                    return "15";
                case 2:
                    return "30";
                case 3:
                    return "40";
                default:
                    return null;
            }
        }
    }


    /**
     * Method to find the winner of the game
     * @return an int representing which player won (1 or 2). -1 if the game has not been won yet.
     */
    public int getWinner() {
        if (player1Points >= WINNING_SCORE && player1Points >= player2Points + WINNING_MARGIN) {
            return 1;
        } else if (player2Points >= WINNING_SCORE && player2Points >= player1Points + WINNING_MARGIN) {
            return 2;
        } else {
            return -1;
        }
    }


    public void player1Scores() {
        player1Points += 1;
    }


    public void player2Scores() {
        player2Points += 1;
    }
}
