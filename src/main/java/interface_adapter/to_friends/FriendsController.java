package interface_adapter.to_friends;

import usecase.to_friends.FriendsInputBoundary;
import usecase.to_friends.FriendsInputData;

public class FriendsController {
    private final FriendsInputBoundary addFriendsInteractor;

    public FriendsController(FriendsInputBoundary addFriendsInteractor) {
        this.addFriendsInteractor = addFriendsInteractor;
    }

    public void execute(String username) {
        final FriendsInputData friendsInputData = new FriendsInputData(username);
        addFriendsInteractor.execute(friendsInputData);
    }
}
