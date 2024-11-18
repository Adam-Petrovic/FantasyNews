package use_case.add_new_friend;

import entity.User;

public interface AddNewFriendUserDataAccessInterface {
    boolean existsByName(String friend_username);

    User get(String friend_username);
}
