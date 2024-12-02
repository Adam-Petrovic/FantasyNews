package interfaceadapter.leagueuserstory.to_league_actions;

import entity.League;

/**
 * The LeagueActionsState class holds the state information relevant to
 * league-related actions. It encapsulates the current league and username,
 * providing methods to access and modify this state.
 */
public class LeagueActionsState {
    private League league;
    private String username;

    /**
     * Sets the current league for the state.
     *
     * @param league the League object to be associated with this state.
     */
    public void setLeague(League league) {
        this.league = league;
    }

    /**
     * Retrieves the current league associated with the state.
     *
     * @return the League object representing the current league.
     */
    public League getLeague() {
        return league;
    }

    /**
     * Sets the username associated with the league actions state.
     *
     * @param username the username to associate with this state.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username associated with the state.
     *
     * @return the username as a String.
     */
    public String getUsername() {
        return username;
    }
}
