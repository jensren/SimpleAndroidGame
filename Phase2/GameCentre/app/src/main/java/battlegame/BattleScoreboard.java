package battlegame;

import java.util.Locale;

import gamecentre.Score;
import gamecentre.Scoreboard;

public class BattleScoreboard extends Scoreboard {
    /**
     * The current user, number of moves, and hp of both characters
     */
    private static String user;
    private static String numMoves;
    private static int playerHp;
    private static int opponentHp;

    public static void setUser(String user) {
        BattleScoreboard.user = user;
    }

    public static void setNumMoves(String numMoves) {
        BattleScoreboard.numMoves = numMoves;
    }

    public static void setPlayerHp(int playerHp) {
        BattleScoreboard.playerHp = playerHp;
    }

    public static void setOpponentHp(int opponentHp) {
        BattleScoreboard.opponentHp = opponentHp;
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    protected String getUserCurrentScore() {
        return super.getUserCurrentScore(BattleScoreboardActivity.score);
    }

    protected void updateGameHighScore(Score newScore) {
        super.updateGameHighScore(newScore);
    }

    protected void updateUserHighScore(Score newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    protected void update() {
        // TODO
    }
}
