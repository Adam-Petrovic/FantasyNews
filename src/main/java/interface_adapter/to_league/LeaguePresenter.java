package interface_adapter.to_league;

import interface_adapter.ViewManagerModel;
import usecase.to_league.LeagueOutputBoundary;

public class LeaguePresenter implements LeagueOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LeagueViewModel leagueViewModel;

    public LeaguePresenter(ViewManagerModel viewManagerModel, LeagueViewModel leagueViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leagueViewModel = leagueViewModel;
    }

    public void execute(String username){
        final LeagueState leagueState = leagueViewModel.getState();
        leagueState.setUsername(username);
        leagueViewModel.setState(leagueState);

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void execute() {
        final LeagueState leagueState = leagueViewModel.getState();
        leagueViewModel.setState(leagueState);

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
