package interface_adapter.to_league;

import entity.League;

public class LeagueState {
    private String leagueID;
    private League league;
    private String username;

    public String getLeagueID() {
        return leagueID;
    }
    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }
    public League getLeague(){
        return league;
    }
    public void setLeague(League league) {
        this.league = league;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
