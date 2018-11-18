package slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;

import gamecentre.Score;

/**
 * Keep track of the user's score by their username.
 */
public class SlidingtilesScore extends Score implements Comparable<SlidingtilesScore>, Serializable {
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
        this.username = username;
        this.points = points;
    }

    /**
     * Return the username
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Return the points
     *
     * @return the points
     */
    int getPoints() {
        return points;
    }

    /**
     * Compares the two scores by the points
     *
     * @param other another score
     * @return This scores points minus the other scores points
     */
    @Override
    public int compareTo(@NonNull SlidingtilesScore other) {
        return this.points - other.points;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Player %s: %d points", username, points);
    }
}
