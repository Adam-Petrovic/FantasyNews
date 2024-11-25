package use_case.create_league;

public interface CreateLeagueOutputBoundary {
    void prepareSuccessView(CreateLeagueOutputData createLeagueOutputData);
    void prepareFailureView(String errorMessage);
}
