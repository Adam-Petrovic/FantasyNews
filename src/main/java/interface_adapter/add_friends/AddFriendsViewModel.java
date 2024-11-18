package interface_adapter.add_friends;

import data_access.Constants;
import interface_adapter.ViewModel;

public class AddFriendsViewModel extends ViewModel<AddFriendsState> {
    public AddFriendsViewModel() {
        super(Constants.ADD_FRIENDS_VIEW_NAME);
        setState(new AddFriendsState());
    }
}
