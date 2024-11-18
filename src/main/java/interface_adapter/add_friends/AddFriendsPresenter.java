package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.add_friends.AddFriendsOutputData;

public class AddFriendsPresenter implements AddFriendsOutputBoundary {
    @Override
    public void prepareSuccessView(AddFriendsOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
