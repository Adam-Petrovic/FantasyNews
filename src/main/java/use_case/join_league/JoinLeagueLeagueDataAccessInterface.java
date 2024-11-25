package use_case.join_league;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface JoinLeagueLeagueDataAccessInterface {
    boolean leagueExist(String leagueID);
    void updateLeagueUsers(User user, String leagueID);
    ArrayList<String> getLeagueUsers(String id);

    HashMap<String, String[]> getData(String id);
}
