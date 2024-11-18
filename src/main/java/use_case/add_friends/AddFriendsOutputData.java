package use_case.add_friends;

import entity.User;

public class AddFriendsOutputData {
    private final User user;

    public AddFriendsOutputData(User user) {
        this.user = user;
    }

    public User getUser() { return this.user; }
}
