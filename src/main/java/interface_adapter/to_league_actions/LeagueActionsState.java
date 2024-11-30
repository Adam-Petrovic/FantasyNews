package interface_adapter.to_league_actions;

import entity.League;

public class LeagueActionsState {
    private League league;

    public void setLeague(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }
}
