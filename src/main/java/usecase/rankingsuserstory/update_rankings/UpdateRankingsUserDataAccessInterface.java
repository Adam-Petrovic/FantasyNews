package usecase.rankingsuserstory.update_rankings;

import java.util.ArrayList;

import entity.User;

/**
 * The {@code UpdateRankingsUserDataAccessInterface} defines the methods for accessing and
 * managing user data required for updating rankings in a league.
 */
public interface UpdateRankingsUserDataAccessInterface {

    /**
     * Retrieves the {@code User} object for the specified username.
     *
     * @param username the username of the user to retrieve
     * @return the {@code User} object associated with the specified username
     */
    User get(String username);

    /**
     * Saves the specified {@code User} object to the data store.
     *
     * @param user the {@code User} object to be saved
     */
    void save(User user);

    /**
     * Retrieves a list of {@code User} objects corresponding to the specified usernames.
     *
     * @param usernames an {@code ArrayList} of usernames to retrieve
     * @return an {@code ArrayList} of {@code User} objects corresponding to the provided usernames
     */
    ArrayList<User> getUsers(ArrayList<String> usernames);
}
