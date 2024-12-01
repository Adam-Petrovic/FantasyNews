package usecase.update_leagues;

import entity.User;

import java.util.ArrayList;

public interface UpdateLeaguesUserDataAccessInterface {
    ArrayList<String> addLeague(String username, String leagueID);
    boolean userInLeague(String username, String leagueID);
    ArrayList<User> getUsers(ArrayList<String> usernames);
}

