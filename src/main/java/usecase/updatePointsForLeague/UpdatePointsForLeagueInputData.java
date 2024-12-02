package usecase.updatePointsForLeague;

import java.util.ArrayList;

import entity.User;

public class UpdatePointsForLeagueInputData {
    private final ArrayList<User> users;
    private final String leagueID;

    /**
     * Stores the league name and the users.
     * @param leagueID league name
     * @param users users of the league
     */
    public UpdatePointsForLeagueInputData(String leagueID, ArrayList<User> users) {
        this.leagueID = leagueID;
        this.users = users;
    }

    /**
     * Gets the users.
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Gets the league name.
     * @return league name
     */
    public String getLeagueID() {
        return leagueID;
    }
}
