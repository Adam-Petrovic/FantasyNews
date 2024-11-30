package interface_adapter.add_new_friend;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_friends.FriendsState;
import interface_adapter.to_friends.FriendsViewModel;
import use_case.add_new_friend.AddNewFriendOutputBoundary;
import use_case.add_new_friend.AddNewFriendOutputData;

public class AddNewFriendPresenter implements AddNewFriendOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final FriendsViewModel friendsViewModel;

    public AddNewFriendPresenter(ViewManagerModel viewManagerModel, FriendsViewModel friendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsViewModel = friendsViewModel;
    }

    @Override
    public void prepareSuccessView(AddNewFriendOutputData outputData) {
        final FriendsState friendsState = friendsViewModel.getState();
        friendsViewModel.getState().getUser().addFriend(outputData.getFriend());
        friendsViewModel.getState().setUserPoints(outputData.getUserPoints());

        this.friendsViewModel.setState(friendsState);
        this.friendsViewModel.firePropertyChanged();
        this.viewManagerModel.setState(friendsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final FriendsState friendsState = friendsViewModel.getState();
        friendsState.setFriend_usernameError(errorMessage);
        this.friendsViewModel.firePropertyChanged();
    }
}
