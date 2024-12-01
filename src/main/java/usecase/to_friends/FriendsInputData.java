package usecase.to_friends;

public class FriendsInputData {
    private final String username;

    public FriendsInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
