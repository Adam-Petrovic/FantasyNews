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

    /**
     * Returns the league.
     * @return league.
     */
    public League getLeague() {
        return league;
    }

    /**
     * Returns the username.
     * @return username.
     */
    public String getUsername() {
        return username;
    }
}
