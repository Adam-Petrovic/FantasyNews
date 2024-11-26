package interface_adapter.update_leagues;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;
import use_case.update_leagues.UpdateLeaguesOutputBoundary;
import use_case.update_leagues.UpdateLeaguesOutputData;

public class UpdateLeaguesPresenter implements UpdateLeaguesOutputBoundary {
    private LeagueViewModel leagueViewModel;
    private ViewManagerModel viewManagerModel;

    public UpdateLeaguesPresenter(LeagueViewModel leagueViewModel,
                                  ViewManagerModel viewManagerModel ) {
        this.leagueViewModel = leagueViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
        final LeagueState leagueState = leagueViewModel.getState();
        leagueState.setLeagues(updateLeaguesOutputData.getUserLeagueList());
        leagueState.setUsername(updateLeaguesOutputData.getUsername());

        //setting viewModel to state
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
