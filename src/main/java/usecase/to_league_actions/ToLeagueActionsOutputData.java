package usecase.to_league_actions;

import entity.League;

public class ToLeagueActionsOutputData {
    private League league;

    ToLeagueActionsOutputData(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }
}
