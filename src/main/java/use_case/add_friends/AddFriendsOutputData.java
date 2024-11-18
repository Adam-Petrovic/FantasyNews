package use_case.add_friends;

import java.util.ArrayList;
import java.util.List;
import entity.User;

public class AddFriendsOutputData {
    private List<User> friends;

    public AddFriendsOutputData(List<User> friends) {
        this.friends = new ArrayList<>(friends);
    }

    public List<User> getFriends() {
        return friends;
    }
}
