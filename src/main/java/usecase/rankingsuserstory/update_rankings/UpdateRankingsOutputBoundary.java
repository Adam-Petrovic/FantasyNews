package usecase.rankingsuserstory.update_rankings;

/**
 * The {@code UpdateRankingsOutputBoundary} interface defines the method for presenting
 * the updated rankings data after the ranking update process is executed.
 */
public interface UpdateRankingsOutputBoundary {
    /**
     * Outputs the updated rankings data.
     *
     * @param updateRankingsOutputData an {@code UpdateRankingsOutputData} object containing
     *                                 the live rankings, historical rankings, and league information
     */
    void execute(UpdateRankingsOutputData updateRankingsOutputData);

    /**
     * Prepares fail view.
     * @param errorMessage error message.
     */
    void prepareFailView(String errorMessage);
}
