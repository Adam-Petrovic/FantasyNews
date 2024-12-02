package interface_adapter.to_friends;

import usecase.to_friends.FriendsInputBoundary;
import usecase.to_friends.FriendsInputData;

/**
 * Controller to switch to friends view.
 */
public class FriendsController {
    private final FriendsInputBoundary addFriendsInteractor;

    public FriendsController(FriendsInputBoundary addFriendsInteractor) {
        this.addFriendsInteractor = addFriendsInteractor;
    }

    /**
     * Executes the interactor for add friends, essentially passing the username information.
     * @param username a String representing the user's name
     */
    public void execute(String username) {
        final FriendsInputData friendsInputData = new FriendsInputData(username);
        addFriendsInteractor.execute(friendsInputData);
    }
}
