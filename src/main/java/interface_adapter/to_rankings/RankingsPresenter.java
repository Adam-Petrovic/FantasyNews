package interface_adapter.to_rankings;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_rankings.RankingsViewModel;
import use_case.to_rankings.RankingsOutputBoundary;

public class RankingsPresenter implements RankingsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RankingsViewModel rankingsViewModel;

    public RankingsPresenter(ViewManagerModel viewManagerModel, RankingsViewModel rankingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsViewModel = rankingsViewModel;
    }

    @Override
    public void execute() {
        final RankingsState rankingsState = rankingsViewModel.getState();
        rankingsViewModel.setState(rankingsState);

        viewManagerModel.setState(rankingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}