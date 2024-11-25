package use_case.update_rankings;

import java.util.ArrayList;
import java.util.HashMap;

public interface UpdateRankingsLeagueDataAccessInterface {
    public ArrayList<String> getLeagueUsers(String leagueID);
    public HashMap<String, String[]> getData(String leagueID);
}


