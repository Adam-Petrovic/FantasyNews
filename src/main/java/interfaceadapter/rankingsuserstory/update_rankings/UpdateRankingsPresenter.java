package interfaceadapter.rankingsuserstory.update_rankings;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.leagueuserstory.to_league.LeagueState;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsState;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsViewModel;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsOutputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsOutputData;

/**
 * The {@code UpdateRankingsPresenter} class implements the {@code UpdateRankingsOutputBoundary} interface
 * to update the view models and trigger property changes based on updated rankings data.
 * It acts as a bridge between the ranking update use case and the UI layer, ensuring the UI state is
 * synchronized with the latest rankings data.
 */
public class UpdateRankingsPresenter implements UpdateRankingsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private RankingsViewModel rankingsViewModel;

    public UpdateRankingsPresenter(ViewManagerModel viewManagerModel, RankingsViewModel rankingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsViewModel = rankingsViewModel;
    }

    @Override
    public void execute(UpdateRankingsOutputData outputData) {
        RankingsState rankingsState = rankingsViewModel.getState();

        rankingsState.setLeague(outputData.getLeague());
        rankingsState.getLeague().setLiveRankings(outputData.getLiveRankings());
        rankingsState.getLeague().setHistoricalRankings(outputData.getHistoricalRankings());

        rankingsViewModel.setState(rankingsState);
        rankingsViewModel.firePropertyChanged();

        viewManagerModel.setState(rankingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        RankingsState rankingsState = rankingsViewModel.getState();
        rankingsState.setErrorMessage(errorMessage);
        rankingsViewModel.setState(rankingsState);
        rankingsViewModel.firePropertyChanged();
    }

}
