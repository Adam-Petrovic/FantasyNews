package usecase.to_friends;

/**
 * Represents the input data for friend-related operations.
 */
public class FriendsInputData {
    private final String username;

    /**
     * Constructs a new FriendsInputData object.
     *
     * @param username The username of the user for whom friend-related operations are to be performed.
     */
    public FriendsInputData(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username associated with this input data.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
}
