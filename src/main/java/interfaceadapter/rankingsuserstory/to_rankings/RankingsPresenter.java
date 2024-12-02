package interfaceadapter.rankingsuserstory.to_rankings;

import interfaceadapter.ViewManagerModel;
import usecase.rankingsuserstory.to_rankings.RankingsOutputBoundary;

public class RankingsPresenter implements RankingsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RankingsViewModel rankingsViewModel;

    public RankingsPresenter(ViewManagerModel viewManagerModel, RankingsViewModel rankingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsViewModel = rankingsViewModel;
    }

    //public void execute(UpdateRankingsOutputData outputData) {
    @Override
    public void execute() {
        final RankingsState rankingsState = rankingsViewModel.getState();
        rankingsViewModel.setState(rankingsState);
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setState(rankingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
