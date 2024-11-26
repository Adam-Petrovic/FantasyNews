package use_case.update_leagues;

import entity.League;
import org.json.JSONObject;

import java.util.ArrayList;

public interface UpdateLeaguesUserDataAccessInterface {
    ArrayList<String> addLeague(String username, String leagueID);
    boolean userInLeague(String username, String leagueID);
}
