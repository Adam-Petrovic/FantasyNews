package usecase.update_leagues;

import java.util.ArrayList;

import entity.League;

/**
 * League Data Access Interface.
 */
public interface UpdateLeaguesLeagueDataAccessInterface {
    /**
     * Save new league.
     * @param leagueID leagueID
     * @param username username
     */
    void saveNewLeague(String leagueID, String username);

    /**
     * Sees if league exists.
     * @param leagueID leagueID.
     * @return if league exists.
     */
    boolean leagueExists(String leagueID);

    /**
     * Adds user to league.
     * @param leagueID leagueID.
     * @param username username.
     */
    void addUserToLeague(String leagueID, String username);

    /**
     * Gets leagues.
     * @param userLeagueIDList leagueIDlist.
     * @return new list of leagues.
     */
    ArrayList<League> getLeagues(ArrayList<String> userLeagueIDList);

    /**
     * Saves league.
     * @param league league.
     */
    void save(League league);
}
