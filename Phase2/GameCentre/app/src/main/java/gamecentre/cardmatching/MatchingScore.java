package gamecentre.cardmatching;

import gamecentre.Score;

public class MatchingScore extends Score {
    private String username;

    /**
     * The points the user has earned.
     */
    private int points;

    /**
     * The score for a user stored by their username and points.
     *
     * @param username The user's username
     * @param points   The points of this user
     */
    MatchingScore(String username, int points) {
        super(username, points);
    }
}
