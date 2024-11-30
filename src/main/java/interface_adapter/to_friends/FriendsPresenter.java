package interface_adapter.to_friends;

import interface_adapter.ViewManagerModel;
import use_case.to_friends.FriendsOutputBoundary;
import use_case.to_friends.FriendsOutputData;

public class FriendsPresenter implements FriendsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final FriendsViewModel friendsViewModel;

    public FriendsPresenter(ViewManagerModel viewManagerModel, FriendsViewModel friendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsViewModel = friendsViewModel;
    }

    public void execute(FriendsOutputData friendsOutputData) {
        final FriendsState friendsState = friendsViewModel.getState();
        friendsState.setUser(friendsOutputData.getUser());
        this.friendsViewModel.setState(friendsState);
        this.friendsViewModel.firePropertyChanged();

        viewManagerModel.setState(friendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
