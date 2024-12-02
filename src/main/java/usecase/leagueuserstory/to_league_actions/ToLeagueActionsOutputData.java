package usecase.leagueuserstory.to_league_actions;

import entity.League;

/**
 * Output data.
 */
public class ToLeagueActionsOutputData {
    private League league;
    private String username;

    ToLeagueActionsOutputData(League league, String username) {
        this.league = league;
        this.username = username;
    }

    public League getLeague() {
        return league;
    }

    public String getUsername() {
        return username;
    }
}