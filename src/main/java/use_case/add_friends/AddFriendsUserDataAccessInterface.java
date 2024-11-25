package use_case.add_friends;

import entity.User;

public interface AddFriendsUserDataAccessInterface {
    User get(String username);

    boolean existsByName(String friend_username);
}
