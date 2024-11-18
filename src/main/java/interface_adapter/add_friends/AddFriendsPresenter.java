package interface_adapter.add_friends;

import interface_adapter.ViewManagerModel;
import use_case.add_friends.AddFriendsOutputBoundary;


public class AddFriendsPresenter implements AddFriendsOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final AddFriendsViewModel addFriendsViewModel;

    public AddFriendsPresenter(ViewManagerModel viewManagerModel, AddFriendsViewModel addFriendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addFriendsViewModel = addFriendsViewModel;
    }

    public void execute() {
        final AddFriendsState addFriendsState = addFriendsViewModel.getState();
        addFriendsViewModel.setState(addFriendsState);

        viewManagerModel.setState(addFriendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
