package usecase.rankingsuserstory.update_rankings;

/**
 * The {@code UpdateRankingsInputBoundary} interface defines the contract for executing
 * the ranking update process in a league.
 */
public interface UpdateRankingsInputBoundary {
    /**
     * Executes the ranking update process.
     * This method processes the input data, updates user rankings, and interacts with
     * the output boundary to present the updated rankings.
     *
     * @param updateRankingsInputData an {@code UpdateRankingsInputData} object containing
     *                                the league identifier for which rankings need to be updated
     */
    void execute(UpdateRankingsInputData updateRankingsInputData);
}
