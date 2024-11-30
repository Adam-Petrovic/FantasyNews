package use_case.update_rankings;

import entity.User;

import java.util.ArrayList;

public interface UpdateRankingsUserDataAccessInterface {
    public User get(String username);
    ArrayList<User> getUsers(ArrayList<String> usernames);
}
