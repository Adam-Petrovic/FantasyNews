package use_case.update_leagues;

import org.json.JSONObject;

public interface UpdateLeaguesLeagueDataAccessInterface {
    void saveNewLeague(String leagueID, String username);

    boolean LeagueExists(String leagueID);

    void addUserToLeague(String leagueID, String username);
}
