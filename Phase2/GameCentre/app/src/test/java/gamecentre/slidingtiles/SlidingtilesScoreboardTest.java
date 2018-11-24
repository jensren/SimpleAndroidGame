package gamecentre.slidingtiles;

import org.junit.Test;

import java.util.HashMap;

import gamecentre.Score;
import gamecentre.Scoreboard;

import static org.junit.Assert.*;

public class SlidingtilesScoreboardTest {

    /**
     * The SlidingtilesScoreboard to test
     */
    SlidingtilesScoreboard scoreboard = new SlidingtilesScoreboard();

    /**
     * Create a generic score list, where Player i is in ith place, with a score of i
     *
     * @return the score list
     */
    private Score[] createScoreList() {
        Score[] gameHighScores = new Score[Scoreboard.LENGTH];
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
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
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
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
        SlidingtilesScoreboard.setUser("Player 1");
        assertEquals("Your Score: 1", scoreboard.getUserCurrentScore());
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
        SlidingtilesScoreboard.setUser("Player 1");
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
        SlidingtilesScoreboard.setUser("Player 1");
        SlidingtilesScoreboard.setNumMoves(1);
        SlidingtilesScoreboard.setBoardSize(1);
        // Score will be 1
        scoreboard.update();
        Score[] expectedGameHighScores = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
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
        Score newScore = new Score("New Player", 100);
        scoreboard.setCurrentScore(newScore);
        scoreboard.update();

        Score[] expectedGameHighScores = createScoreList();
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        expectedUserToBestScore.put("New Player", newScore);

        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
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
        Score oldScore = new Score("Underdog", 100);
        scoreboard.setCurrentScore(oldScore);
        scoreboard.update();
        Score newScore = new Score("Underdog", 0);
        scoreboard.setCurrentScore(newScore);
        scoreboard.update();

        Score[] s = createScoreList();
        Score[] expectedGameHighScores = new Score[Scoreboard.LENGTH];
        expectedGameHighScores[0] = newScore;
        for (int i = 0; i < Scoreboard.LENGTH - 1; i++) {
            expectedGameHighScores[i + 1] = s[i];
        }
        HashMap<String, Score> expectedUserToBestScore = createUserToBestScores(expectedGameHighScores);
        expectedUserToBestScore.put("Underdog", newScore);

        Score[] actualGameHighScores = scoreboard.getScoreList();
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
            assertEquals(expectedGameHighScores[i], actualGameHighScores[i]);
        }
        assertEquals(expectedUserToBestScore, scoreboard.getUserToBestScore());
    }
}