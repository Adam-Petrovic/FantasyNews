package interface_adapter.to_draft;

import entity.User;

public class DraftState {
    private String friend_username = "";
    private String friend_usernameError;
    private String username = "";
    private String usernameError;
    private User user;

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

