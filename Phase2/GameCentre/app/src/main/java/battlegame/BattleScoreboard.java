package battlegame;

import java.util.Locale;

import gamecentre.Scoreboard;

public class BattleScoreboard extends Scoreboard {
    protected String getUserCurrentScore() {
        return super.getUserCurrentScore(BattleScoreboardActivity.score);
    }

    @Override
    protected void update() {

    }
}
