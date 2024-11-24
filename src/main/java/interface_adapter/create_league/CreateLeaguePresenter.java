package interface_adapter.create_league;

import entity.League;
import interface_adapter.ViewManagerModel;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;
import use_case.create_league.CreateLeagueOutputBoundary;
import use_case.create_league.CreateLeagueOutputData;
import view.LeagueView;
import view.ViewManager;

import java.util.ArrayList;

public class CreateLeaguePresenter implements CreateLeagueOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LeagueViewModel leagueViewModel;

    public CreateLeaguePresenter(ViewManagerModel viewManagerModel, LeagueViewModel leagueViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leagueViewModel = leagueViewModel;
    }

    public void prepareSuccessView(CreateLeagueOutputData createLeagueOutputData) {
        //updating state... maybe should add a "add league"?
        final LeagueState leagueState = leagueViewModel.getState();
        leagueState.addLeague(createLeagueOutputData.getLeague());
        leagueState.addLeagueID(createLeagueOutputData.getLeagueID());

        //setting viewModel to state
        leagueViewModel.setState(leagueState);
        leagueViewModel.firePropertyChanged();

        viewManagerModel.setState(leagueViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
