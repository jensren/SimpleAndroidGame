package slidingtiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import gamecentre.Scoreboard;

/**
 * Manage the scores for user and games.
 */
public class SlidingtilesScoreboard extends Scoreboard implements Serializable {

    /**
     * An ordered list with the highest score as first item.
     */
    private SlidingtilesScore[] scoreList = new SlidingtilesScore[LENGTH];

    /**
     * A map of username to high scores.
     */
    private HashMap<String, SlidingtilesScore> userToBestScore = new HashMap<>();

    private static String user;
    private static int numMoves;
    private static int boardSize;

    public static void setUser(String user) {
        SlidingtilesScoreboard.user = user;
    }

    static void setNumMoves(int numMoves) {
        SlidingtilesScoreboard.numMoves = numMoves;
    }

    static void setBoardSize(int boardSize) {
        SlidingtilesScoreboard.boardSize = boardSize;
    }

    public String toString() {
        return super.toString();
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    @Override
    public String getUserCurrentScore() {
        if (SlidingtilesScoreboardActivity.scores == null) {
            return "Your Score: None";
        } else {
            return String.format(Locale.getDefault(), "Your Score: %d", SlidingtilesScoreboardActivity.scores.getPoints());
        }
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
            SlidingtilesScoreboardActivity.scores = new SlidingtilesScore(user, points);
            updateGameHighScore(SlidingtilesScoreboardActivity.scores);
            updateUserHighScore(SlidingtilesScoreboardActivity.scores);
        }
    }
}
