package interfaceadapter.friendsuserstory.to_friends;

import java.util.Map;

import entity.User;

/**
 * The state information regarding the user's friends.
 */
public class FriendsState {
    private String friendUsername = "";
    private String friendUsernameError;
    private String username = "";
    private User user;
    private Map<User, Integer> userPoints;

    /**
     * Sets the user points map.
     *
     * @param userPoints A HashMap containing User objects as keys and their corresponding points as values.
     */
    public void setUserPoints(Map<User, Integer> userPoints) {
        this.userPoints = userPoints;
    }

    /**
     * Retrieves the user points map.
     *
     * @return A HashMap containing User objects as keys and their corresponding points as values.
     */
    public Map<User, Integer> getUserPoints() {
        return userPoints;
    }

    /**
     * Retrieves the current user.
     *
     * @return The User object representing the current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the current user.
     *
     * @param user The User object to set as the current user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Retrieves the username of the current user.
     *
     * @return A String representing the username of the current user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the current user.
     *
     * @param username A String representing the username to set for the current user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the friend.
     *
     * @return A String representing the username of the friend.
     */
    public String getFriendUsername() {
        return friendUsername;
    }

    /**
     * Retrieves the error message related to the friend's username.
     *
     * @return A String containing the error message for the friend's username, if any.
     */
    public String getFriendUsernameError() {
        return friendUsernameError;
    }

    /**
     * Sets the username of the friend.
     *
     * @param friendUsername A String representing the username to set for the friend.
     */
    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    /**
     * Sets the error message related to the friend's username.
     *
     * @param friendUsernameError A String containing the error message to set for the friend's username.
     */
    public void setFriendUsernameError(String friendUsernameError) {
        this.friendUsernameError = friendUsernameError;
    }
}
