package usecase.frienduserstory.to_friends;

import entity.User;

/**
 * DAO for the to_friends use case.
 */
public interface FriendsUserDataAccessInterface {
    /**
     * Gets the user from the database by searching the username.
     * @param username the user's name.
     * @return a User.
     */
    User get(String username);

    /**
     * Saves the user.
     * @param user user.
     */
    void save(User user);

    /**
     * Return a boolean to show whether the friend exists by name.
     * @param friend_username friend's username.
     * @return a boolean representing whether the friend is an existing user.
     */
    boolean existsByName(String friend_username);
}
