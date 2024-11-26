package use_case.update_leagues;

import entity.League;
import org.json.JSONObject;

import java.util.ArrayList;

public interface UpdateLeaguesUserDataAccessInterface {
    JSONObject get(String type);
    void save(JSONObject jsonObject, String type);

}
