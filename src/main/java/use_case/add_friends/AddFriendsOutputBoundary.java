package use_case.add_friends;

public interface AddFriendsOutputBoundary {
    void prepareSuccessView(AddFriendsOutputData outputData);

    void prepareFailView(String errorMessage);
}
