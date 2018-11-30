package gamecentre.slidingtiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import gamecentre.Score;
import gamecentre.Scoreboard;

/**
 * Manage the scores the gamecentre.slidingtiles game.
 */
public class SlidingtilesScoreboard extends Scoreboard implements Serializable {

    /**
     * The number of moves and board size
     */
    private static int numMoves;
    private static int boardSize;

    /**
     * Sets scoreboard's number of moves.
     * @param numMoves Number of moves player took in the game.
     */
    static void setNumMoves(int numMoves) {
        SlidingtilesScoreboard.numMoves = numMoves;
    }

    /**
     * Setter for board size.
     * @param boardSize the integer for size to be set.
     */
    static void setBoardSize(int boardSize) {
        SlidingtilesScoreboard.boardSize = boardSize;
    }
    /**
     * Gets the user's highest high score.
     * @return the best score.
     */
    protected String getUserBestScore() {
        return super.getUserBestScore();
    }
    /**
     * Get the number of moves when the game ends
     *
     * @return the number of moves
     */
    protected String getUserCurrentScore() {
        return super.getUserCurrentScore();
    }

    /**
     * Gets the current number of moves
     * @return the number of moves
     */
    public static int getNumMoves() {
        return numMoves;
    }
    /**
     * Updates scoreboard's high score list.
     * @param newScore the score to be added to the list.
     */
    protected void updateGameHighScore(Score newScore) {
        super.updateGameHighScore(newScore);
    }
    /**
     * Updates scoreboard's per-user specific high score.
     * @param newScore the score to be added.
     */
    protected void updateUserHighScore(Score newScore) {
        super.updateUserHighScore(newScore);
    }

    /**
     * Reset the score
     */
    static void reset() {
        numMoves = 0;
    }

    @Override
    public void update() {
        if (numMoves != 0) {
            int points = numMoves / boardSize;
            currentScore = new Score(Scoreboard.getUser(), points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        } else {
            currentScore = null;
        }
    }
}
