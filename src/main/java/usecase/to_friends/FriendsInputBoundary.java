package usecase.to_friends;

/**
 * Input boundary for switching to friends view.
 */
public interface FriendsInputBoundary {
    /**
     * Executes the switching to friends view use case based on input data.
     * @param friendsInputData the input data.
     */
    void execute(FriendsInputData friendsInputData);
}
