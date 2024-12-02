package interfaceadapter.leagueuserstory.to_league_actions;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.leagueuserstory.to_league.LeagueState;
import interfaceadapter.leagueuserstory.to_league.LeagueViewModel;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsOutputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsOutputData;

public class ToLeagueActionsPresenter implements ToLeagueActionsOutputBoundary {
    private LeagueActionsViewModel leagueActionsViewModel;
    private LeagueViewModel leagueViewModel;
    private ViewManagerModel viewManagerModel;

    public ToLeagueActionsPresenter(LeagueActionsViewModel leagueActionsViewModel,LeagueViewModel leagueViewModel,
                             ViewManagerModel viewManagerModel) {
        this.leagueActionsViewModel = leagueActionsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.leagueViewModel = leagueViewModel;
    }

    @Override
    public void prepareSuccessView(ToLeagueActionsOutputData toLeagueActionsOutputData) {
        LeagueActionsState leagueActionsState = this.leagueActionsViewModel.getState();
        leagueActionsState.setLeague(toLeagueActionsOutputData.getLeague());
        leagueActionsState.setUsername(toLeagueActionsOutputData.getUsername());
        leagueActionsViewModel.setState(leagueActionsState);
        leagueActionsViewModel.firePropertyChanged();

        viewManagerModel.setState(leagueActionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailureView(String errorMessage) {
        LeagueState leagueState = leagueViewModel.getState();
        leagueState.setErrorMessage("User is not in this league");
        leagueViewModel.setState(leagueState);
        leagueViewModel.firePropertyChanged();

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
