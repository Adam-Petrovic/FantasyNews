package interface_adapter.to_friends;

import java.util.HashMap;

import entity.User;

public class FriendsState {
    private String friendUsername = "";
    private String friendUsernameError;
    private String username = "";
    private User user;
    private HashMap<User, Integer> userPoints;

    public void setUserPoints(HashMap<User, Integer> userPoints) {
        this.userPoints = userPoints;
    }

    public HashMap<User, Integer> getUserPoints() {
        return userPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public String getFriendUsernameError() {
        return friendUsernameError;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public void setFriendUsernameError(String friendUsernameError) {
        this.friendUsernameError = friendUsernameError;
    }
}
