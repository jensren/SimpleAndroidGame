package slidingtiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import gamecentre.Scoreboard;

/**
 * Manage the scores the slidingtiles game.
 */
public class SlidingtilesScoreboard extends Scoreboard implements Serializable {

    /**
     * The current user, number of moves, and board size
     */
    private static String user;
    private static int numMoves;
    private static int boardSize;

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
        return super.getUserCurrentScore(SlidingtilesScoreboardActivity.score);
    }

    private void updateGameHighScore(SlidingtilesScore newScore) {
        super.updateGameHighScore(newScore);
    }

    private void updateUserHighScore(SlidingtilesScore newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    public void update() {
        if (boardSize != 0) {
            int points = numMoves / boardSize;
            SlidingtilesScoreboardActivity.score = new SlidingtilesScore(user, points);
            updateGameHighScore(SlidingtilesScoreboardActivity.score);
            updateUserHighScore(SlidingtilesScoreboardActivity.score);
        }
    }
}
