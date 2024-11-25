package use_case.join_league;

import entity.User;

import java.util.ArrayList;

public interface JoinLeagueUserDataAccessInterface {
    User get(String username);
    ArrayList<String> updateUserLeagues(User user, String leagueID);
}
