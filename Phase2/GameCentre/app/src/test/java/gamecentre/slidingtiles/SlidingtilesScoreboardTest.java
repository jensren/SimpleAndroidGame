package gamecentre.slidingtiles;

import org.junit.Test;

import java.util.HashMap;

import gamecentre.Score;
import gamecentre.Scoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SlidingtilesScoreboardTest {

    /**
     * The SlidingtilesScoreboard to test
     */
    private SlidingtilesScoreboard scoreboard = new SlidingtilesScoreboard();

    /**
     * Create a generic score list, where Player i is in ith place, with a score of i
     *
     * @return the score list
     */
    private Score[] createScoreList() {
        Score[] gameHighScores = new Score[Scoreboard.getLENGTH()];
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            Score s = new Score(String.format("%d", i + 1), i + 1);
            gameHighScores[i] = s;
        }
        return gameHighScores;
    }

    /**
     * Generate a list of high user high scores for a given list of game high scores
     *
     * @param gameHighScores the list of game high scores
     * @return the list of user high scores
     */
    private HashMap<String, Score> createUserToBestScores(Score[] gameHighScores) {
        HashMap<String, Score> userHighScores = new HashMap<>();
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            userHighScores.put(gameHighScores[i].getUsername(), gameHighScores[i]);
        }
        return userHighScores;
    }

    /**
     * Create a scoreboard and populate it with a list of players
     */
    private void populateScoreboard() {
        Score[] gameHighScores = createScoreList();
        HashMap<String, Score> userHighScores = createUserToBestScores(gameHighScores);
        scoreboard.setScoreList(gameHighScores);
        scoreboard.setUserToBestScore(userHighScores);
    }

    /**
     * Test getUserCurrentScore when the user has a current score
     */
    @Test
    public void testGetUserCurrentScoreWithScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("1");
        SlidingtilesScoreboard.setNumMoves(100);
        SlidingtilesScoreboard.setBoardSize(1);
        // The player's score will be 100
        scoreboard.update();
        assertEquals("Your Score: 100", scoreboard.getUserCurrentScore());
    }

    /**
     * Test getUserCurrentScore when the user has no current score
     */
    @Test
    public void testGetUserCurrentScoreWithoutScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("Unknown");
        assertEquals("Your Score: None", scoreboard.getUserCurrentScore());
    }

    /**
     * Test getUserCurrentScore when the user has a best score
     */
    @Test
    public void getUserBestScoreWithScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("1");
        assertEquals("Best Score: 1", scoreboard.getUserBestScore());
    }

    /**
     * Test getUserCurrentScore when the user has no best score
     */
    @Test
    public void getUserBestScoreWithoutScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("Unknown");
        assertEquals("Best Score: None", scoreboard.getUserBestScore());
    }

    /**
     * Test update() when an old player does not get a high score
     */
    @Test
    public void testUpdateNotHighScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("1");
        SlidingtilesScoreboard.setNumMoves(100);
        SlidingtilesScoreboard.setBoardSize(1);
        // The player's score will be 100
        scoreboard.update();
        Score[] expectedGameHighScores = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            assertEquals(expectedGameHighScores[i].compareTo(actualGameHighScores[i]), 0);
        }
        assertEquals(expectedUserToBestScore, scoreboard.getUserToBestScore());
    }

    /**
     * Test update() with a new player
     */
    @Test
    public void testUpdateNewPlayer() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("New Player");
        SlidingtilesScoreboard.setNumMoves(100);
        SlidingtilesScoreboard.setBoardSize(1);
        // The player's score will be 100
        scoreboard.update();

        Score newScore = new Score("New Player", 100);
        Score[] expectedGameHighScores = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        expectedUserToBestScore.put("New Player", newScore);

        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            assertEquals(expectedGameHighScores[i].compareTo(actualGameHighScores[i]), 0);
        }
        assertEquals(expectedUserToBestScore, scoreboard.getUserToBestScore());
    }

    /**
     * Test update() when the player beats their old score and enters top 10
     */
    @Test
    public void testUpdateNewHighScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("Underdog");
        SlidingtilesScoreboard.setNumMoves(100);
        SlidingtilesScoreboard.setBoardSize(1);
        // The player's score will be 100
        scoreboard.update();
        Score newScore = new Score("Underdog", 0);
        SlidingtilesScoreboard.setNumMoves(1);
        SlidingtilesScoreboard.setBoardSize(100);
        // The player's score will be 0
        scoreboard.update();

        Score[] s = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(s);
        Score[] expectedGameHighScores = new Score[Scoreboard.getLENGTH()];
        expectedGameHighScores[0] = newScore;
        System.arraycopy(s, 0, expectedGameHighScores, 1, Scoreboard.getLENGTH() - 1);
        expectedUserToBestScore.put("Underdog", newScore);

        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            assertEquals(expectedGameHighScores[i], actualGameHighScores[i]);
        }
        assertEquals(expectedUserToBestScore, scoreboard.getUserToBestScore());
    }

    /**
     * Test update() when there is no score
     */
    @Test
    public void testUpdateWithNoScore() {
        populateScoreboard();
        SlidingtilesScoreboard.setUser("New Player");
        SlidingtilesScoreboard.setNumMoves(0);
        scoreboard.update();
        Score[] expectedGameHighScores = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            assertEquals(expectedGameHighScores[i].compareTo(actualGameHighScores[i]), 0);
        }
        assertEquals(expectedUserToBestScore, scoreboard.getUserToBestScore());
    }

    /**
     * Test whether the scoreboard resets
     */
    @Test
    public void testReset() {
        SlidingtilesScoreboard.setNumMoves(1);
        SlidingtilesScoreboard.reset();
        assertEquals(0, SlidingtilesScoreboard.getNumMoves());
    }

    /**
     * Test toString with an empty scoreboard
     */
    @Test
    public void testEmptyScoreboardToString() {
        StringBuilder expected = new StringBuilder("SCORE BOARD\n\n");
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            expected.append(String.format("%d.\n", i + 1));
        }
        assertEquals(expected.toString(), scoreboard.toString());
    }

    /**
     * Test toString with a populated scoreboard
     */
    @Test
    public void testPopulatedScoreboardToString() {
        populateScoreboard();
        StringBuilder expected = new StringBuilder("SCORE BOARD\n\n");
        for (int i = 0; i < Scoreboard.getLENGTH(); i++) {
            expected.append(String.format("%d. Player %d: %d points\n", i + 1, i + 1, i + 1));
        }
        assertEquals(expected.toString(), scoreboard.toString());
    }
}