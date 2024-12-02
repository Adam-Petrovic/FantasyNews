package usecase.leagueuserstory.update_leagues;

/**
 * Output Boundary.
 */

public interface UpdateLeaguesOutputBoundary {
    /**
     * Prepare success view.
     * @param updateLeaguesOutputData updateLeaguesOutputData.
     */
    void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData);

    /**
     * Prepares fail view.
     * @param errorMessage error message.
     */
    void prepareFailView(String errorMessage);
}
