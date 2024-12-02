package usecase.rankingsuserstory.update_rankings;

import java.util.ArrayList;
import java.util.Map;

import entity.League;

/**
 * The {@code UpdateRankingsLeagueDataAccessInterface} defines methods for accessing and
 * managing league data required for updating user rankings.
 */
public interface UpdateRankingsLeagueDataAccessInterface {

    /**
     * Retrieves a list of usernames associated with a specific league.
     *
     * @param leagueID the unique identifier of the league
     * @return an {@code ArrayList} of usernames belonging to the specified league
     */
    ArrayList<String> getLeagueUsers(String leagueID);

    /**
     * Retrieves league-related data for a specified league.
     *
     * @param leagueID the unique identifier of the league
     * @return a {@code Map} where each key is a username and the corresponding value is
     *         an array of strings representing league-related data
     */
    Map<String, String[]> getData(String leagueID);

    /**
     * Saves the specified {@code League} object to the data store.
     *
     * @param league the {@code League} object to be saved
     */
    void save(League league);

    /**
     * Retrieves a list of {@code League} objects for the specified league IDs.
     *
     * @param leagueID an {@code ArrayList} of league IDs to retrieve
     * @return an {@code ArrayList} of {@code League} objects corresponding to the provided IDs
     */
    ArrayList<League> getLeagues(ArrayList<String> leagueID);
}


