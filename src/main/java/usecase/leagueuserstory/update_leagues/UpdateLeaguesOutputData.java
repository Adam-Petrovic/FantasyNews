package usecase.leagueuserstory.update_leagues;

import java.util.ArrayList;

import entity.League;

/**
 * Output Data.
 */
public class UpdateLeaguesOutputData {
    private String username;
    private ArrayList<League> userLeagueList;

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
