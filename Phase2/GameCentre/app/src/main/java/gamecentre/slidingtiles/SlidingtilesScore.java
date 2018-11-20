package gamecentre.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;

import gamecentre.Score;

/**
 * Keep track of the user's score by their username.
 */
public class SlidingtilesScore extends Score {
    /**
     * The user.
     */
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
    SlidingtilesScore(String username, int points) {
        super(username, points);
    }
}
