package use_case.update_leagues;

import entity.League;
import entity.User;
import org.json.JSONObject;

import java.util.ArrayList;

public interface UpdateLeaguesUserDataAccessInterface {
    ArrayList<String> addLeague(String username, String leagueID);
    boolean userInLeague(String username, String leagueID);
    ArrayList<User> getUsers(ArrayList<String> usernames);
}

