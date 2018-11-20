package gamecentre.battlegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import gamecentre.Score;
import gamecentre.Scoreboard;

import static org.junit.Assert.*;

public class BattleScoreboardTest {

    /**
     * The BattleScoreboard to test
     */
    BattleScoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = null;
        Score[] gameHighScores = new Score[Scoreboard.LENGTH];
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
            Score s = new Score(String.format("Player %d", i), i);
            gameHighScores[i] = s;
        }
        HashMap<String, Score> userHighScores = new HashMap<>();
        for (int i = 0; i < Scoreboard.LENGTH; i++) {
            userHighScores.put(String.format("Player %d", i), gameHighScores[i]);
        }
        scoreboard.setScoreList(gameHighScores);
        scoreboard.setUserToBestScore(userHighScores);
    }

    @Test
    public void getUserBestScore() {
    }

    @Test
    public void getUserCurrentScore() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getUserBestScore1() {
    }

    @Test
    public void getUserCurrentScore1() {
    }

    @Test
    public void updateGameHighScore() {
    }

    @Test
    public void updateUserHighScore() {
    }
}