package use_case.update_rankings;

import entity.League;
import entity.User;

import java.util.ArrayList;

public interface UpdateRankingsUserDataAccessInterface {
    public User get(String username);
    public void save(User user);
    ArrayList<User> getUsers(ArrayList<String> usernames);
}
