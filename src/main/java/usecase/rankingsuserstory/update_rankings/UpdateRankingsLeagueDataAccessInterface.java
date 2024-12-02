package usecase.rankingsuserstory.update_rankings;

import java.util.ArrayList;

import entity.League;

/**
 * The {@code UpdateRankingsLeagueDataAccessInterface} defines methods for accessing and
 * managing league data required for updating user rankings.
 */
public interface UpdateRankingsLeagueDataAccessInterface {

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

