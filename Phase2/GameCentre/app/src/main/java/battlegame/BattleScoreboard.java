package battlegame;

import java.util.Locale;

import gamecentre.Scoreboard;

public class BattleScoreboard extends Scoreboard {
    @Override
    protected String getUserCurrentScore() {
        if (BattleScoreboardActivity.scores == null) {
            return "Your Score: None";
        } else {
            return String.format(Locale.getDefault(), "Your Score: %d", BattleScoreboardActivity.scores.getPoints());
        }
    }

    @Override
    protected void update() {

    }
}
