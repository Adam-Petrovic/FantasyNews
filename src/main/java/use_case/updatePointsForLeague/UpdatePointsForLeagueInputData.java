package use_case.updatePointsForLeague;

import entity.User;

import java.util.ArrayList;

public class UpdatePointsForLeagueInputData {
    private final ArrayList<User> users;
    private final String leagueID;
    public UpdatePointsForLeagueInputData(String leagueID, ArrayList<User> users){
        this.leagueID = leagueID;
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getLeagueID() {
        return leagueID;
    }
}
