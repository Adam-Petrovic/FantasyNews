package use_case.add_new_friend;

import entity.User;

public class AddNewFriendOutputData {
    private User friend;

    public AddNewFriendOutputData(User friend) {
        this.friend = friend;
    }

    public User getFriend() {
        return friend;
    }
}
