package usecase.leagueuserstory.to_league_actions;

import java.util.ArrayList;

import entity.League;

/**
 * League Data Access Interface.
 */
public interface ToLeagueActionsLeagueDataAccessInterface {
    /**
     * Get Leagues.
     * @param leagueID leagueID
     * @return array list of leagues.
     */
    ArrayList<League> getLeagues(ArrayList<String> leagueID);

    /**
     * Adds user to league.
     * @param leagueID leagueID.
     * @param username username.
     */
    void addUserToLeague(String leagueID, String username);

    /**
     * Save league.
     * @param league league.
     */
    void save(League league);
}
