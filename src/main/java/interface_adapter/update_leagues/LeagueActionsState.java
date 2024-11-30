package interface_adapter.update_leagues;

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
