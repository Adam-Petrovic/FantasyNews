package usecase.add_new_friend;

/**
 * Input boundary for the add new friend use case.
 */
public interface AddNewFriendInputBoundary {
    /**
     * Adds the new friend given the input data.
     * @param addNewFriendInputData the input data.
     */
    void execute(AddNewFriendInputData addNewFriendInputData);
}
