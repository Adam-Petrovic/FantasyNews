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

    /**
     * Returns user's name.
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns an arraylist of leagues for the user..
     * @return userLeagueList.
     */
    public ArrayList<League> getUserLeagueList() {
        return userLeagueList;
    }
}
