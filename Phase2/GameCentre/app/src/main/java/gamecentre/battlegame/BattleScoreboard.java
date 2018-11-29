package gamecentre.battlegame;

import gamecentre.Score;
import gamecentre.Scoreboard;

public class BattleScoreboard extends Scoreboard {
    /**
     * The current user, number of moves, and hp of both characters
     */
    private static String user;
    private static int numMoves;
    private static int playerHpLost;
    private static int opponentHpLost;

    public static void setUser(String user) {
        BattleScoreboard.user = user;
    }

    static void setNumMoves(int numMoves) {
        BattleScoreboard.numMoves = numMoves;
    }

    static void setPlayerHpLost(int playerHpLost) {
        BattleScoreboard.playerHpLost = playerHpLost;
    }

    static void setOpponentHpLost(int opponentHpLost) {
        BattleScoreboard.opponentHpLost = opponentHpLost;
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    public static int getNumMoves() {
        return numMoves;
    }

    /**
     * Get the winner of the game
     *
     * @return a String saying player 1 won or player 2 won
     */
    String getWinner() {
        if (playerHpLost == Character.getInitialHp()) {
            return "Player 2 is the winner!";
        } else if (opponentHpLost == Character.getInitialHp()) {
            return "Player 1 is the winner!";
        }
        return "";
    }

    protected String getUserCurrentScore() {
        return super.getUserCurrentScore();
    }

    protected void updateGameHighScore(Score newScore) {
        super.updateGameHighScore(newScore);
    }

    protected void updateUserHighScore(Score newScore) {
        super.updateUserHighScore(newScore);
    }

    /**
     * Reset the score
     */
    static void reset() {
        numMoves = 0;
    }

    @Override
    protected void update() {
        if (numMoves != 0) {
            int points = ((100 + playerHpLost - opponentHpLost) * numMoves) / 10;
            currentScore = new Score(user, points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        } else {
            currentScore = null;
            playerHpLost = 0;
            opponentHpLost = 0;
        }
    }
}
