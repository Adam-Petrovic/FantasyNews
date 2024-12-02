package usecase.to_league_actions;

/**
 * Output boundary.
 */
public interface ToLeagueActionsOutputBoundary {
    /**
     * Prepare success view.
     * @param toLeagueActionsOutputData output data.
     */
    void prepareSuccessView(ToLeagueActionsOutputData toLeagueActionsOutputData);

    /**
     * Prepare failure view.
     * @param errorMessage error message.
     */
    void prepareFailureView(String errorMessage);
}
