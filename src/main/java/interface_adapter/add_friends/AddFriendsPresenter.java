package interface_adapter.add_friends;

import interface_adapter.ViewManagerModel;
import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.add_friends.AddFriendsOutputData;


public class AddFriendsPresenter implements AddFriendsOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final AddFriendsViewModel addFriendsViewModel;

    public AddFriendsPresenter(ViewManagerModel viewManagerModel, AddFriendsViewModel addFriendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addFriendsViewModel = addFriendsViewModel;
    }

    public void execute(AddFriendsOutputData addFriendsOutputData) {
        final AddFriendsState addFriendsState = addFriendsViewModel.getState();
        addFriendsState.setUser(addFriendsOutputData.getUser());
        this.addFriendsViewModel.setState(addFriendsState);
        this.addFriendsViewModel.firePropertyChanged();

        viewManagerModel.setState(addFriendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
