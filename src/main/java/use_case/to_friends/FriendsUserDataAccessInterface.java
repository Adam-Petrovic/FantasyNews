package use_case.to_friends;

import entity.User;

public interface FriendsUserDataAccessInterface {
    User get(String username);
    void save(User user);
    boolean existsByName(String friend_username);
}
