package usecase.frienduserstory.to_friends;

import entity.User;

/**
 * Output data for switching to friends view.
 */
public class FriendsOutputData {
    private final User user;

    /**
     * Sets the current user.
     * @param user the current user.
     */
    public FriendsOutputData(User user) {
        this.user = user;
    }

    /**
     * Return the current user as a User object.
     * @return user.
     */
    public User getUser() {
        return this.user;
    }
}
