package use_case.add_new_friend;

import java.util.HashMap;

import entity.User;

public class AddNewFriendOutputData {
    private User friend;
    private HashMap<User, Integer> userPoints;

    public AddNewFriendOutputData(User friend, HashMap<User, Integer> userPoints) {
        this.friend = friend;
        this.userPoints = userPoints;
    }

    public HashMap<User, Integer> getUserPoints() {
        return userPoints;
    }

    public User getFriend() {
        return friend;
    }
}
