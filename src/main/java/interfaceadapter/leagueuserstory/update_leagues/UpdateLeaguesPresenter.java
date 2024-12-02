package interfaceadapter.leagueuserstory.update_leagues;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.leagueuserstory.to_league.LeagueState;
import interfaceadapter.leagueuserstory.to_league.LeagueViewModel;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesOutputBoundary;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesOutputData;

/**
 * Presenter.
 */
public class UpdateLeaguesPresenter implements UpdateLeaguesOutputBoundary {
    private LeagueViewModel leagueViewModel;
    private ViewManagerModel viewManagerModel;

    public UpdateLeaguesPresenter(ViewManagerModel viewManagerModel, LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
        final LeagueState leagueState = leagueViewModel.getState();
        leagueState.setLeagues(updateLeaguesOutputData.getUserLeagueList());
        leagueState.setUsername(updateLeaguesOutputData.getUsername());

        // Setting viewModel to state
        leagueViewModel.setState(leagueState);
        leagueViewModel.firePropertyChanged();

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LeagueState leagueState = leagueViewModel.getState();
        leagueState.setErrorMessage(errorMessage);
        leagueViewModel.setState(leagueState);
        leagueViewModel.firePropertyChanged();
    }
}
