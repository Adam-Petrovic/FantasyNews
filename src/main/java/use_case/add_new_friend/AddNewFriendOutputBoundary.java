package use_case.add_new_friend;

public interface AddNewFriendOutputBoundary {
    void prepareSuccessView(AddNewFriendOutputData addNewFriendOutputData);
    void prepareFailView(String errorMessage);

}
