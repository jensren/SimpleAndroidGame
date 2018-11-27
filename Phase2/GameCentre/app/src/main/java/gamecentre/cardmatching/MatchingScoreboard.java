package gamecentre.cardmatching;

import gamecentre.Score;
import gamecentre.Scoreboard;

public class MatchingScoreboard extends Scoreboard {
    /**
     * The current user, number of moves
     */
    private static String user;
    private static int numMoves;

    /**
     * Sets scoreboard's user to the current player's username.
     * @param user The username of the player
     */
    static void setUser(String user) {
        MatchingScoreboard.user = user;
    }

    /**
     * Sets scoreboard's number of moves.
     * @param numMoves Number of moves player took in the game.
     */
    static void setNumMoves(int numMoves) {
        MatchingScoreboard.numMoves = numMoves;
    }

    /**
     * Gets the user's highest high score.
     * @return the best score.
     */
    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    /**
     * Get the number of moves when the game ends
     *
     * @return the number of moves
     */
    public static int getNumMoves() {
        return numMoves;
    }

    /**
     * Gets the user's score in his current game.
     * @return the user's current score.
     */
    protected String getUserCurrentScore() {
        return super.getUserCurrentScore();
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
        if (numMoves > 0) {
            currentScore = new Score(user, numMoves);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        } else {
            currentScore = null;
        }
    }
}
