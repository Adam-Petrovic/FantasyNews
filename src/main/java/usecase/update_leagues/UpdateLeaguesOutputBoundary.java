package usecase.update_leagues;

public interface UpdateLeaguesOutputBoundary {
    void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData);
    void prepareFailView(String errorMessage);
}
