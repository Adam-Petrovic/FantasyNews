package use_case.update_rankings;

import entity.League;

import java.util.ArrayList;
import java.util.HashMap;

public interface UpdateRankingsLeagueDataAccessInterface {
    public ArrayList<String> getLeagueUsers(String leagueID);
    public HashMap<String, String[]> getData(String leagueID);

    ArrayList<League> getLeagues(ArrayList<String> leagueID);
}


