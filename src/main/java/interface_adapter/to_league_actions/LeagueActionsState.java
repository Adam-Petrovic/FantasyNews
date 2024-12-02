package interface_adapter.to_league_actions;

import entity.League;

public class LeagueActionsState {
    private League league;
    private String username;

    public void setLeague(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
