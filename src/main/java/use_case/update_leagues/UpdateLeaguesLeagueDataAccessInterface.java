package use_case.update_leagues;

import org.json.JSONObject;

public interface UpdateLeaguesLeagueDataAccessInterface {
    JSONObject get(String type);
    void save(JSONObject jsonObject, String type);
}
