package use_case.create_league;

import entity.League;

public class CreateLeagueOutputData {
    private final String username;
    private final String leagueID;
    private final League league;

    public CreateLeagueOutputData(String username, String leagueID, League league) {
        this.username = username;
        this.leagueID = leagueID;
        this.league = league;
    }

    public String getUsername() {
        return username;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public League getLeague() {
        return league;
    }
}
