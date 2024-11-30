package use_case.round_league_points;

import entity.User;

import java.util.ArrayList;

public class RoundLeaguePointsInputData {
    private String leagueID;
    private ArrayList<User> users;


    public RoundLeaguePointsInputData(String leagueID, ArrayList<User> users) {
        this.leagueID = leagueID;
        this.users = users;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
