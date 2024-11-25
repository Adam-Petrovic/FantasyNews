package interface_adapter.add_new_friend;

import use_case.add_new_friend.AddNewFriendInputBoundary;
import use_case.add_new_friend.AddNewFriendInputData;

public class AddNewFriendController {
    private final AddNewFriendInputBoundary addNewFriendInteractor;

    public AddNewFriendController(AddNewFriendInputBoundary addNewFriendInteractor) {
        this.addNewFriendInteractor = addNewFriendInteractor;
    }

    public void execute(String friend_username, String username) {
        final AddNewFriendInputData addNewFriendInputData = new AddNewFriendInputData(friend_username, username);
        addNewFriendInteractor.execute(addNewFriendInputData);
    }
}
