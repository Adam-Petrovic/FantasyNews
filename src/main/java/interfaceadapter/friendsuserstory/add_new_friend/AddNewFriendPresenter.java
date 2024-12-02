package interfaceadapter.friendsuserstory.add_new_friend;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.friendsuserstory.to_friends.FriendsState;
import interfaceadapter.friendsuserstory.to_friends.FriendsViewModel;
import usecase.frienduserstory.add_new_friend.AddNewFriendOutputBoundary;
import usecase.frienduserstory.add_new_friend.AddNewFriendOutputData;

/**
 * Presenter for the add new friend button on friends view.
 */
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
        friendsState.setFriendUsernameError(errorMessage);
        this.friendsViewModel.firePropertyChanged();
    }
}
