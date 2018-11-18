package gamecentre;

import java.util.HashMap;
import java.util.Locale;

public abstract class Scoreboard {
    /**
     * Size of scoreboard (ie. how many top scores are stored and displayed).
     */
    private static final int LENGTH = 10;
    private static String user;
    private static int numMoves;
    private static int boardSize;
    /**
     * An ordered list with the highest score as first item.
     */
    private Score[] scoreList = new Score[LENGTH];
    /**
     * A map of username to high scores.
     */
    private HashMap<String, Score> userToBestScore = new HashMap<>();

    /**
     * Set the user.
     *
     * @param user the user
     */
    public static void setUser(String user) {
        Scoreboard.user = user;
    }

    /**
     * Set the number of moves to numMoves.
     *
     * @param numMoves the number of moves
     */
    public static void setNumMoves(int numMoves) {
        Scoreboard.numMoves = numMoves;
    }

    /**
     * Set the size of the board to boardSize.
     *
     * @param boardSize the size of the board
     */
    public static void setBoardSize(int boardSize) {
        Scoreboard.boardSize = boardSize;
    }

    /**
     * Gets the user's best score
     *
     * @return A string representation of the user's best score
     */
    public String getUserBestScore() {
        if (userToBestScore.get(user) == null) {
            return "Best Score: None";
        } else {
            return String.format(Locale.getDefault(), "Best Score: %d",
                    userToBestScore.get(user).getPoints());
        }

    }

    /**
     * Gets the user's current score
     *
     * @return A string representation of the user's current score
     */
    protected abstract String getUserCurrentScore();

    @Override
    public String toString() {
        String scores = "";
        for (int i = 0; i < LENGTH; i++) {
            // String looks like: "1. username: score \n 2. username: score ..."
            if (scoreList[i] == null) {
                // If there is no high score, string looks like: "1. \n 2. \n ..."
                scores = String.format(Locale.getDefault(), "%s%d.\n", scores, i + 1);
            } else {
                scores = String.format(Locale.getDefault(), "%s%d. %s\n", scores, i + 1,
                        scoreList[i].toString());
            }
        }
        return scores;
    }

    /**
     * If the new score is a high score, update the list of high scores.
     *
     * @param newScore the newest score
     */
    protected void updateGameHighScore(Score newScore) {

        for (int i = LENGTH - 1; i >= 0; i--) {
            if (scoreList[i] == null || newScore.compareTo(scoreList[i]) < 0) {
                Score current = scoreList[i];
                scoreList[i] = newScore;
                if (i < LENGTH - 1) {
                    scoreList[i + 1] = current;
                }
            }
        }
    }


    /**
     * Update a user's high score if their new score is better.
     *
     * @param newScore the newest score
     */
    protected void updateUserHighScore(Score newScore) {
        String username = newScore.getUsername();
        if ((userToBestScore.get(username) == null) ||
                (userToBestScore.get(username).compareTo(newScore) > 0)) {
            userToBestScore.put(username, newScore);
        }
    }

    /**
     * Update the user high score and game high score.
     */
    protected abstract void update();
}
