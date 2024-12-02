package usecase.add_new_friend;

/**
 * Input data for the add new friend use case.
 */
public class AddNewFriendInputData {
    private final String friendUsername;
    private final String username;

    public AddNewFriendInputData(String friendUsername, String username) {
        this.friendUsername = friendUsername;
        this.username = username;
    }

    /**
     * Return friend's username.
     * @return friendUsername.
     */
    public String getFriendUsername() {
        return friendUsername;
    }

    /**
     * Return user's name.
     * @return username.
     */
    public String getUsername() {
        return username;
    }
}
