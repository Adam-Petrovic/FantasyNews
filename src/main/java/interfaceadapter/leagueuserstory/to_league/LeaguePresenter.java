package interfaceadapter.leagueuserstory.to_league;

import interfaceadapter.ViewManagerModel;
import usecase.leagueuserstory.to_league.LeagueOutputBoundary;

/**
 * The LeaguePresenter class implements the {@link LeagueOutputBoundary} interface
 * and is responsible for managing the transition of league-related states and
 * updating the view manager model.
 */
public class LeaguePresenter implements LeagueOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LeagueViewModel leagueViewModel;

    public LeaguePresenter(ViewManagerModel viewManagerModel, LeagueViewModel leagueViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leagueViewModel = leagueViewModel;
    }

    /**
     * Executes a league-related action with a specific username.
     * Updates the league state with the username and triggers a view state change.
     *
     * @param username the username to be set in the league state.
     */
    public void execute(String username) {
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
