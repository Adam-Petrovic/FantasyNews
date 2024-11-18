package use_case.add_new_friend;

public class AddNewFriendInputData {
    private final String friend_username;

    public AddNewFriendInputData(String friend_username) {
        this.friend_username = friend_username;
    }

    public String getFriend_username() {
        return friend_username;
    }
}
