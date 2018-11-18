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
     * Size of scoreboard (ie. how many top scores are stored and displayed).
     */
    private static final int LENGTH = 10;

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

    @Override
    public String getUserCurrentScore() {
        if (SlidingtilesScoreboardActivity.scores == null) {
            return "Your Score: None";
        } else {
            return String.format(Locale.getDefault(), "Your Score: %d", SlidingtilesScoreboardActivity.scores.getPoints());
        }
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
