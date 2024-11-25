package use_case.create_league;

import entity.League;
import entity.User;

import java.util.ArrayList;

public interface CreateLeagueUserDataAccessInterface {
    //updates user's array list of id's and returns it
    public ArrayList<String> updateUserLeagues(String username, String leagueID);
    
    public League getLeague(String leagueID);
    public User get(String username);
    boolean leagueExist(String leagueID);
}

