package gamecentre.battlegame;

import gamecentre.Score;
import gamecentre.Scoreboard;

/**
 * The scoreboard for the battle game.
 */
public class BattleScoreboard extends Scoreboard {
    /**
     * The number of moves and hp of both characters
     */
    private static int numMoves;
    /**
     * The hp lost by player 1
     */
    private static int player1HpLost;
    /**
     * The hp lost by player 2
     */
    private static int player2HpLost;

    /**
     * Set numMoves
     *
     * @param numMoves the number of moves
     */
    static void setNumMoves(int numMoves) {
        BattleScoreboard.numMoves = numMoves;
    }

    /**
     * Set player1HpLost
     *
     * @param player1HpLost the hp lost by player 1
     */
    static void setPlayer1HpLost(int player1HpLost) {
        BattleScoreboard.player1HpLost = player1HpLost;
    }

    /**
     * Set player2HpLost
     *
     * @param player2HpLost the hp lost by player 2
     */
    static void setPlayer2HpLost(int player2HpLost) {
        BattleScoreboard.player2HpLost = player2HpLost;
    }

    /**
     * Return the best score for the user
     * @return the best score
     */
    protected String getUserBestScore() {
        return super.getUserBestScore();
    }

    /**
     * Return the number of moves
     * @return numMoves
     */
    public static int getNumMoves() {
        return numMoves;
    }

    /**
     * Return the winner of the game
     *
     * @return a String saying player 1 won or player 2 won
     */
    String getWinner() {
        if (player1HpLost == Character.getInitialHp()) {
            return "Player 2 is the winner!";
        } else if (player2HpLost == Character.getInitialHp()) {
            return "Player 1 is the winner!";
        }
        return "";
    }

    /**
     * Return the user's current score
     * @return the user's current score
     */
    protected String getUserCurrentScore() {
        return super.getUserCurrentScore();
    }

    /**
     * Update the high score for the game
     * @param newScore the newest score
     */
    protected void updateGameHighScore(Score newScore) {
        super.updateGameHighScore(newScore);
    }

    /**
     * Update the user's high score
     * @param newScore the newest score
     */
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
            int points = ((100 + player1HpLost - player2HpLost) * numMoves) / 10;
            currentScore = new Score(Scoreboard.getUser(), points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        } else {
            currentScore = null;
            player1HpLost = 0;
            player2HpLost = 0;
        }
    }
}
