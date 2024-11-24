package interface_adapter.add_new_friend;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_friends.AddFriendsState;
import interface_adapter.add_friends.AddFriendsViewModel;
import use_case.add_new_friend.AddNewFriendOutputBoundary;
import use_case.add_new_friend.AddNewFriendOutputData;

public class AddNewFriendPresenter implements AddNewFriendOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AddFriendsViewModel addFriendsViewModel;

    public AddNewFriendPresenter(ViewManagerModel viewManagerModel, AddFriendsViewModel addFriendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addFriendsViewModel = addFriendsViewModel;
    }

    @Override
    public void prepareSuccessView(AddNewFriendOutputData outputData) {
        final AddFriendsState addFriendsState = addFriendsViewModel.getState();
        addFriendsViewModel.getState().getUser().addFriend(outputData.getFriend());

        this.addFriendsViewModel.setState(addFriendsState);
        this.addFriendsViewModel.firePropertyChanged();
        this.viewManagerModel.setState(addFriendsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final AddFriendsState addFriendsState = addFriendsViewModel.getState();
        addFriendsState.setFriend_usernameError(errorMessage);
        this.addFriendsViewModel.firePropertyChanged();
    }
}
