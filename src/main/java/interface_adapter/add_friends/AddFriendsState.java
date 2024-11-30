package interface_adapter.add_friends;

import entity.User;

import java.util.HashMap;

public class AddFriendsState {
    private String friend_username = "";
    private String friend_usernameError;
    private String username = "";
    private String usernameError;
    private User user;
    private HashMap<User, Integer> userPoints;

    public void setUserPoints(HashMap<User, Integer> userPoints) {
        this.userPoints = userPoints;
    }

    public HashMap<User, Integer> getUserPoints() {
        return userPoints;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.friend_usernameError = usernameError;
    }

    public String getFriend_username() {
        return friend_username;
    }

    public String getFriend_usernameError() {
        return friend_usernameError;
    }

    public void setFriend_username(String username) {
        this.friend_username = username;
    }

    public void setFriend_usernameError(String usernameError) {
        this.friend_usernameError = usernameError;
    }
}
