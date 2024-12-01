package interface_adapter.add_new_friend;

import usecase.add_new_friend.AddNewFriendInputBoundary;
import usecase.add_new_friend.AddNewFriendInputData;

/**
 * Controller for the add friend button on friends view.
 */
public class AddNewFriendController {
    private final AddNewFriendInputBoundary addNewFriendInteractor;

    public AddNewFriendController(AddNewFriendInputBoundary addNewFriendInteractor) {
        this.addNewFriendInteractor = addNewFriendInteractor;
    }

    /**
     * Executes the interactor for adding a new friend.
     * @param friend_username a String representing the friend's username
     * @param username a String representing user's name
     */
    public void execute(String friend_username, String username) {
        final AddNewFriendInputData addNewFriendInputData = new AddNewFriendInputData(friend_username, username);
        addNewFriendInteractor.execute(addNewFriendInputData);
    }
}
