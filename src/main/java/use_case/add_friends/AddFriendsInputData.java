package use_case.add_friends;

public class AddFriendsInputData {
    private final String username;

    public AddFriendsInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
