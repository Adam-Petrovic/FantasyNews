package use_case.award_league_points;

import entity.User;

import java.util.ArrayList;

public class AwardLeaguePointsInputData {
    private String leagueID;
    private ArrayList<User> users;


    public AwardLeaguePointsInputData(String leagueID, ArrayList<User> users) {
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
