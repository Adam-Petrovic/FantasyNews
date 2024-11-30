package use_case.add_new_friend;

public class AddNewFriendInputData {
    private final String friendUsername;
    private final String username;

    public AddNewFriendInputData(String friendUsername, String username) {
        this.friendUsername = friendUsername;
        this.username = username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public String getUsername() {
        return username;
    }
}
