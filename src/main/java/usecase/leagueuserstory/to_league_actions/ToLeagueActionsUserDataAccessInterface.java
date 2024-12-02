package usecase.leagueuserstory.to_league_actions;

import java.util.ArrayList;

import entity.User;

/**
 * User Data Access Interface.
 */
public interface ToLeagueActionsUserDataAccessInterface {
    /**
     * User in league.
     * @param username username.
     * @param leagueID leagueID.
     * @return if user is in league.
     */
    boolean userInLeague(String username, String leagueID);

    /**
     * Save user.
     * @param user user.
     */
    void save(User user);

    /**
     * Add league to user.
     * @param username username.
     * @param leagueID leagueID.
     * @return updated list of user's leagues.
     */
    ArrayList<String> addLeague(String username, String leagueID);
}
