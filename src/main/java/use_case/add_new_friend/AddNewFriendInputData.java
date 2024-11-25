package use_case.add_new_friend;

public class AddNewFriendInputData {
    private final String friend_username;
    private final String username;

    public AddNewFriendInputData(String friend_username, String username) {
        this.friend_username = friend_username;
        this.username = username;
    }

    public String getFriend_username() {
        return friend_username;
    }

    public String getUsername() {
        return username;
    }
}
