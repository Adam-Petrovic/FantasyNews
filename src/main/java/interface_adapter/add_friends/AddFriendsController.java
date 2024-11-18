package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInputData;

public class AddFriendsController {
    private final AddFriendsInputBoundary addFriendsUseCaseInteractor;


    public AddFriendsController(AddFriendsInputBoundary addFriendsUseCaseInteractor) {
        this.addFriendsUseCaseInteractor = addFriendsUseCaseInteractor;
    }

    public void execute(String friend_username) {
        final AddFriendsInputData addFriendsInputData = new AddFriendsInputData(friend_username);
        addFriendsUseCaseInteractor.execute(addFriendsInputData);
    }
}
