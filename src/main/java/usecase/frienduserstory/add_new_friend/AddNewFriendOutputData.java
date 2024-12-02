package usecase.frienduserstory.add_new_friend;

import java.util.HashMap;
import java.util.Map;

import entity.User;

/**
 * Output data for add new friend.
 */
public class AddNewFriendOutputData {
    private User friend;
    private Map<User, Integer> userPoints;

    public AddNewFriendOutputData(User friend, HashMap<User, Integer> userPoints) {
        this.friend = friend;
        this.userPoints = userPoints;
    }

    /**
     * Return a map mapping from user to their points.
     * @return userPoints.
     */
    public Map<User, Integer> getUserPoints() {
        return userPoints;
    }

    /**
     * Return the user's new friend added.
     * @return friend.
     */
    public User getFriend() {
        return friend;
    }
}
