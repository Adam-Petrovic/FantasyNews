package interface_adapter.to_league_actions;

import interface_adapter.ViewManagerModel;
import interface_adapter.update_leagues.LeagueActionsState;
import interface_adapter.update_leagues.LeagueActionsViewModel;
import use_case.to_league_actions.ToLeagueActionsOutputBoundary;
import use_case.to_league_actions.ToLeagueActionsOutputData;

public class ToLeagueActionsPresenter implements ToLeagueActionsOutputBoundary {
    private LeagueActionsViewModel leagueActionsViewModel;
    private ViewManagerModel viewManagerModel;

    ToLeagueActionsPresenter(LeagueActionsViewModel leagueActionsViewModel,ViewManagerModel viewManagerModel) {
        this.leagueActionsViewModel = leagueActionsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ToLeagueActionsOutputData toLeagueActionsOutputData) {

    }

    @Override
    public void prepareFailureView(String errorMessage) {

    }
}
