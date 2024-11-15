package interface_adapter.to_league;

import interface_adapter.ViewManagerModel;
import use_case.to_league.LeagueOutputBoundary;

public class LeaguePresenter implements LeagueOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LeagueViewModel leagueViewModel;

    public LeaguePresenter(ViewManagerModel viewManagerModel, LeagueViewModel leagueViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leagueViewModel = leagueViewModel;
    }

    @Override
    public void execute() {
        final LeagueState leagueState = leagueViewModel.getState();
        leagueViewModel.setState(leagueState);

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
