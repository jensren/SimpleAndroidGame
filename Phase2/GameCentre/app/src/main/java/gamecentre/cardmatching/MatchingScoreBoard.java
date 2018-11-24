package gamecentre.cardmatching;

import gamecentre.Scoreboard;

public class MatchingScoreBoard extends Scoreboard {
    /**
     * The current user, number of moves, and board size
     */
    private static String user;
    private static int numMoves;
    private static int boardSize;

    /**
     * Sets scoreboard's user to the current player's username.
     * @param user The username of the player
     */
    static void setUser(String user) {
        MatchingScoreBoard.user = user;
    }

    /**
     * Sets scoreboard's number of moves.
     * @param numMoves Number of moves player took in the game.
     */
    static void setNumMoves(int numMoves) {
        MatchingScoreBoard.numMoves = numMoves;
    }

    static void setBoardSize(int boardSize) {
        MatchingScoreBoard.boardSize = boardSize;
    }

    /**
     * Gets the user's highest high score.
     * @return the best score.
     */
    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    /**
     * Gets the user's score in his current game.
     * @return the user's current score.
     */
    String getUserCurrentScore() {
        return super.getUserCurrentScore(MatchingScoreBoardActivity.score);
    }

    /**
     * Updates scoreboard's high score list.
     * @param newScore the score to be added to the list.
     */
    private void updateGameHighScore(MatchingScore newScore) {
        super.updateGameHighScore(newScore);
    }

    /**
     * Updates scoreboard's per-user specific high score.
     * @param newScore the score to be added.
     */
    private void updateUserHighScore(MatchingScore newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    public void update() {
            int points = numMoves;
            MatchingScoreBoardActivity.score = new MatchingScore(user, points);
            updateGameHighScore(MatchingScoreBoardActivity.score);
            updateUserHighScore(MatchingScoreBoardActivity.score);
    }
}
