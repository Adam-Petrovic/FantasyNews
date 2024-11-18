package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInputData;

public class AddFriendsController{
    private final AddFriendsInputBoundary addFriendsInteractor;

    public AddFriendsController(AddFriendsInputBoundary addFriendsInteractor) {
        this.addFriendsInteractor = addFriendsInteractor;
    }

    public void execute(String username){
        //addFriendsPresenter.execute();
        final AddFriendsInputData addFriendsInputData = new AddFriendsInputData(username);
        addFriendsInteractor.execute(addFriendsInputData);
    }
}
