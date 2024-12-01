package usecase.add_new_friend;

public interface AddNewFriendOutputBoundary {
    void prepareSuccessView(AddNewFriendOutputData addNewFriendOutputData);

    void prepareFailView(String errorMessage);
}
