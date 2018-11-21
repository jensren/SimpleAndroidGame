package gamecentre.slidingtiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import gamecentre.Scoreboard;

/**
 * Manage the scores the gamecentre.slidingtiles game.
 */
public class SlidingtilesScoreboard extends Scoreboard implements Serializable {

    /**
     * The current user, number of moves, and board size
     */
    private static String user;
    private static int numMoves;
    private static int boardSize;

    /**
     * The user's current score
     */
    private SlidingtilesScore currentScore;

    static void setUser(String user) {
        SlidingtilesScoreboard.user = user;
    }

    static void setNumMoves(int numMoves) {
        SlidingtilesScoreboard.numMoves = numMoves;
    }

    static void setBoardSize(int boardSize) {
        SlidingtilesScoreboard.boardSize = boardSize;
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    String getUserCurrentScore() {
        return super.getUserCurrentScore(currentScore);
    }

    private void updateGameHighScore(SlidingtilesScore newScore) {
        super.updateGameHighScore(newScore);
    }

    private void updateUserHighScore(SlidingtilesScore newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    public void update() {
        if (numMoves != 0) {
            int points = numMoves / boardSize;
            currentScore = new SlidingtilesScore(user, points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        }
    }
}
