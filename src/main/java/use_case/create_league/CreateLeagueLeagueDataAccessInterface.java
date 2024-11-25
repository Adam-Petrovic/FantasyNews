package use_case.create_league;

import entity.League;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface CreateLeagueLeagueDataAccessInterface {
    public ArrayList<String> getLeagueUsers(String leagueID);
    boolean leagueExist(String leagueID);
    public void saveNewLeague(User user, String leagueID);
    public HashMap<String, String[]> getData(String leagueID);

}
