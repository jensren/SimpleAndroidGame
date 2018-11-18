package slidingtiles;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Manage user.
 */
class UserManager implements Serializable {
    /**
     * HashMap of user, with key username and value password
     */
    private HashMap<String, String> users = new HashMap<>();

    /**
     * Adds a user with username and password to user
     *
     * @param username the user's username
     * @param password the user's password
     */
    void addUser(String username, String password) {
        users.put(username, password);
    }

    /**
     * Return the password of username
     *
     * @param username the user's username
     * @return username's password
     */
    String getPassword(String username) {
        return users.get(username);
    }
}


