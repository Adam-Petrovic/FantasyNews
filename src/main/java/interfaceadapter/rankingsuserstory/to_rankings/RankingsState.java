package interfaceadapter.rankingsuserstory.to_rankings;

import entity.League;
import entity.User;

/**
 * The RankingsState class holds the state information relevant to the
 * rankings feature, including the league and user entities. It serves
 * as a data transfer object within the application.
 */
public class RankingsState {
    private League league;
    private User user;

    /**
     * Retrieves the current league associated with the rankings state.
     *
     * @return the league object representing the current league.
     */
    public League getLeague() {
        return league;
    }

    /**
     * Updates the league associated with the rankings state.
     *
     * @param league the league object to set as the current league.
     */
    public void setLeague(League league) {
        this.league = league;
    }

    /**
     * Retrieves the current user associated with the rankings state.
     *
     * @return the user object representing the current user.
     */
    public User getUser() {
        return user;
    }
}
