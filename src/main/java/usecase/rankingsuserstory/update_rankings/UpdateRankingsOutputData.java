package usecase.rankingsuserstory.update_rankings;

import java.util.ArrayList;

import entity.League;
import entity.User;

/**
 * The {@code UpdateRankingsOutputData} class encapsulates the output data of the ranking update process,
 * including live rankings, historical rankings, and league information.
 * This class is not designed for extension. To prevent subclassing, it is declared as {@code final}.
 */
public class UpdateRankingsOutputData {
    private ArrayList<User> rankings;
    private ArrayList<User> liveRankings;
    private ArrayList<User> historicalRankings;
    private League league;

    public UpdateRankingsOutputData(ArrayList<User> liveRankings, League league, ArrayList<User> historicalRankings) {
        this.liveRankings = liveRankings;
        this.league = league;
        this.historicalRankings = historicalRankings;
    }

    /**
     * Retrieves the league associated with the rankings.
     * This method is safe to use and does not allow modifications to the internal state of the class.
     *
     * @return the {@code League} object associated with the rankings
     */
    public League getLeague() {
        return league;
    }

    /**
     * Retrieves the live rankings.
     * This method is safe to use and does not allow modifications to the internal state of the class.
     *
     * @return an {@code ArrayList} of {@code User} objects representing the live rankings
     */
    public ArrayList<User> getLiveRankings() {
        return liveRankings;
    }

    /**
     * Retrieves the historical rankings.
     * This method is safe to use and does not allow modifications to the internal state of the class.
     *
     * @return an {@code ArrayList} of {@code User} objects representing the historical rankings
     */
    public ArrayList<User> getHistoricalRankings() {
        return historicalRankings;
    }
}
