package cardmatching;

import gamecentre.Scoreboard;

public class MatchingScoreBoard extends Scoreboard {
    /**
     * The current user, number of moves, and board size
     */
    private static String user;
    private static int numMoves;
    private static int boardSize;

    static void setUser(String user) {
        MatchingScoreBoard.user = user;
    }

    static void setNumMoves(int numMoves) {
        MatchingScoreBoard.numMoves = numMoves;
    }

    static void setBoardSize(int boardSize) {
        MatchingScoreBoard.boardSize = boardSize;
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    String getUserCurrentScore() {
        return super.getUserCurrentScore(MatchingScoreBoardActivity.score);
    }

    private void updateGameHighScore(MatchingScore newScore) {
        super.updateGameHighScore(newScore);
    }

    private void updateUserHighScore(MatchingScore newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    public void update() {
        if (boardSize != 0) {
            int points = numMoves / boardSize;
            MatchingScoreBoardActivity.score = new MatchingScore(user, points);
            updateGameHighScore(MatchingScoreBoardActivity.score);
            updateUserHighScore(MatchingScoreBoardActivity.score);
        }
    }
}
