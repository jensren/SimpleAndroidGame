package gamecentre.battlegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import gamecentre.Score;
import gamecentre.Scoreboard;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BattleScoreboardTest {

    /**
     * The BattleScoreboard to test
     */
    BattleScoreboard scoreboard;

    /**
     * Create a scoreboard and populate it with a list of players
     */
    @Before
    public void setUp() {
        scoreboard = null;
        Score[] gameHighScores = new Score[Scoreboard.LENGTH];
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
            Score s = new Score(String.format("Player %d", i + 1), i + 1);
            gameHighScores[i] = s;
        }
        HashMap<String, Score> userHighScores = new HashMap<>();
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
            userHighScores.put(String.format("Player %d", i + 1), gameHighScores[i]);
        }
        scoreboard.setScoreList(gameHighScores);
        scoreboard.setUserToBestScore(userHighScores);
    }

    /**
     * Test getUserCurrentScore when the user has a current score
     */
    @Test
    public void testGetUserCurrentScoreWithScore() {
        BattleScoreboard.setUser("Player 1");
        assertEquals("Your Score: 1", scoreboard.getUserCurrentScore());
    }

    /**
     * Test getUserCurrentScore when the user has no current score
     */
    @Test
    public void testGetUserCurrentScoreWithoutScore() {
        BattleScoreboard.setUser("Unknown");
        assertEquals("Your Score: None", scoreboard.getUserCurrentScore());
    }

    /**
     * Test getUserCurrentScore when the user has a best score
     */
    @Test
    public void getUserBestScoreWithScore() {
        BattleScoreboard.setUser("Player 1");
        assertEquals("Best Score: 1", scoreboard.getUserBestScore());
    }

    /**
     * Test getUserCurrentScore when the user has no best score
     */
    @Test
    public void getUserBestScoreWithoutScore() {
        BattleScoreboard.setUser("Unknown");
        assertEquals("Best Score: None", scoreboard.getUserBestScore());
    }

    /** Test update() when an old player does not get a high Score*/
    @Test
    public void testUpdateNotHighScore() {
        // TODO
    }
}