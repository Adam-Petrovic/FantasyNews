package entity.Users;

import java.util.ArrayList;

/**
 * Factory for creating users.
 */
public interface UserFactory {

    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String password);

    /**
     * Creates a new User with three parameters.
     * @param name the name of the new user.
     * @param password the password of the new user.
     * @param words the words of the new user.
     * @return the new user created.
     */
    User create(String name, String password, String[] words);

    /**
     * Creates a new User with four parameters.
     * @param name the name of the new user.
     * @param password the password of the new user.
     * @param words the words of the new user.
     * @param leagueIDs the league IDs of the new user.
     * @return the new user created.
     */
    User create(String name, String password, String[] words, ArrayList<String> leagueIDs);
}
