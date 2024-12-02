package usecase.pointsuserstory.award_league_points;

import java.util.ArrayList;

import entity.User;

public class AwardLeaguePointsInputData {
    private String leagueID;
    private ArrayList<User> users;

    public AwardLeaguePointsInputData(String leagueID, ArrayList<User> users) {
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
     * Gets the users of the current league.
     * @return the users of the current league
     */
    public ArrayList<User> getUsers() {
        return users;
    }

}
