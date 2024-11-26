package use_case.update_leagues;

import entity.League;

import java.util.ArrayList;

public class UpdateLeaguesOutputData {
    public String username;
    public ArrayList<League> userLeagueList;

    public UpdateLeaguesOutputData(String username, ArrayList<League> userLeagueList) {
        this.username = username;
        this.userLeagueList = userLeagueList;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<League> getUserLeagueList() {
        return userLeagueList;
    }
}
