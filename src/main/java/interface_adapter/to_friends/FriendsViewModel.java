package interface_adapter.to_friends;

import data_access.Constants;
import interface_adapter.ViewModel;

public class FriendsViewModel extends ViewModel<FriendsState> {
    public FriendsViewModel(){
        super(Constants.ADD_FRIENDS_VIEW_NAME);
        setState(new FriendsState());
    }
}
