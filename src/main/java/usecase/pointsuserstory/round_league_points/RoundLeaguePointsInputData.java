package usecase.pointsuserstory.round_league_points;

import java.util.ArrayList;

import entity.User;

/**
 * Input data for rounding points for league.
 */
public class RoundLeaguePointsInputData {
    private String leagueID;
    private ArrayList<User> users;

    public RoundLeaguePointsInputData(String leagueID, ArrayList<User> users) {
        this.leagueID = leagueID;
        this.users = users;
    }

    /**
     * Gets the league name.
     * @return the league name
     */
    public String getLeagueID() {
        return leagueID;
    }

    /**
     * Gets the users.
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

}
