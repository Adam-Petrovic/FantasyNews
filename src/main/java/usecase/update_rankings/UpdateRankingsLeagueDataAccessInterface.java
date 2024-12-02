package usecase.update_rankings;

import entity.League;

import java.util.ArrayList;
import java.util.HashMap;

public interface UpdateRankingsLeagueDataAccessInterface {
    public ArrayList<String> getLeagueUsers(String leagueID);
    public HashMap<String, String[]> getData(String leagueID);
    public void save(League league);
    ArrayList<League> getLeagues(ArrayList<String> leagueID);
}

