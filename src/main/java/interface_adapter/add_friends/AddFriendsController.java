package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.to_league.LeagueOutputBoundary;

public class AddFriendsController{
    private final AddFriendsOutputBoundary addFriendsPresenter;

    public AddFriendsController(AddFriendsOutputBoundary addFriendsPresenter) {
        this.addFriendsPresenter = addFriendsPresenter;
    }

    public void execute(){
        addFriendsPresenter.execute();
    }
}
