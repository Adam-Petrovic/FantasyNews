package usecase.frienduserstory.to_friends;

/**
 * Output boundary for switching to friends view.
 */
public interface FriendsOutputBoundary {
    /**
     * Switches to friends view.
     * @param friendsOutputData output data.
     */
    void execute(FriendsOutputData friendsOutputData);
}
