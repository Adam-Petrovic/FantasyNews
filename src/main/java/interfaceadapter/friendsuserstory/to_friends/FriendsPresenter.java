package interfaceadapter.friendsuserstory.to_friends;

import interfaceadapter.ViewManagerModel;
import usecase.frienduserstory.to_friends.FriendsOutputBoundary;
import usecase.frienduserstory.to_friends.FriendsOutputData;

/**
 * Presenter for switching to the friends view.
 */
public class FriendsPresenter implements FriendsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final FriendsViewModel friendsViewModel;

    public FriendsPresenter(ViewManagerModel viewManagerModel, FriendsViewModel friendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsViewModel = friendsViewModel;
    }

    @Override
    public void execute(FriendsOutputData friendsOutputData) {
        final FriendsState friendsState = friendsViewModel.getState();
        friendsState.setUser(friendsOutputData.getUser());
        this.friendsViewModel.setState(friendsState);
        this.friendsViewModel.firePropertyChanged();

        viewManagerModel.setState(friendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
