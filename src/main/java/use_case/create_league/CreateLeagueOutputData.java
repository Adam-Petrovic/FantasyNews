package use_case.create_league;

import entity.League;

import java.util.ArrayList;

public class CreateLeagueOutputData {
    private final String username;
    private final ArrayList<League> leagues;

    public CreateLeagueOutputData(String username, ArrayList<League> leagues) {
        this.username = username;
        this.leagues = leagues;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }
}
