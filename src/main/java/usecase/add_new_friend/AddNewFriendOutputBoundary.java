package usecase.add_new_friend;

/**
 * Output boundary for add new friend  use case.
 */
public interface AddNewFriendOutputBoundary {
    /**
     * Prepares success view based on output data.
     * @param addNewFriendOutputData output data.
     */
    void prepareSuccessView(AddNewFriendOutputData addNewFriendOutputData);

    /**
     * Prepares fail view and shows the error message.
     * @param errorMessage error message.
     */
    void prepareFailView(String errorMessage);
}
