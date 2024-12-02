package interfaceadapter.friendsuserstory.to_friends;

import data_access.Constants;
import interfaceadapter.ViewModel;

/**
 * The view model for the friends view.
 */
public class FriendsViewModel extends ViewModel<FriendsState> {
    public FriendsViewModel() {
        super(Constants.ADD_FRIENDS_VIEW_NAME);
        setState(new FriendsState());
    }
}
