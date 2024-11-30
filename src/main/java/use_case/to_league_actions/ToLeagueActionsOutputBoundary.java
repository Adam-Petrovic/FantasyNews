package use_case.to_league_actions;

public interface ToLeagueActionsOutputBoundary {
    void prepareSuccessView(ToLeagueActionsOutputData toLeagueActionsOutputData);
    void prepareFailureView(String errorMessage);
}
