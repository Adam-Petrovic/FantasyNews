package usecase.frienduserstory.add_new_friend;

import entity.User;

/**
 * DAO for add new friend use case.
 */
public interface AddNewFriendUserDataAccessInterface {
    /**
     * Gets the user from the database by searching the username.
     * @param username the user's name.
     * @return a User.
     */
    User get(String username);

    /**
     * Return a boolean to show whether the friend exists by name.
     * @param friend_username friend's username.
     * @return a boolean representing whether the friend is an existing user.
     */
    boolean existsByName(String friend_username);
}
