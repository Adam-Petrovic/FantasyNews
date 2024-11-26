package use_case.update_rankings;

import entity.User;

import java.util.ArrayList;

public interface UpdateRankingsDataAccessInterface {
    ArrayList<User> getUsers(ArrayList<String> usernames);
}
