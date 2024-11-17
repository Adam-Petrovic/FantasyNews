package use_case.add_friends;

public class AddFriendsInputData {
    private final String friend_username;

    public AddFriendsInputData(String friend_username) {
        this.friend_username = friend_username;
    }

    public String getFriend_username() {
        return friend_username;
    }
}
