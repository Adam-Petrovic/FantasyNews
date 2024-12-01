package usecase.to_friends;

import entity.User;

public class FriendsOutputData {
    private final User user;

    public FriendsOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
