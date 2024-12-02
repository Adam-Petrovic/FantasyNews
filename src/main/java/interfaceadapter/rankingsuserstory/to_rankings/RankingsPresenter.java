package interfaceadapter.rankingsuserstory.to_rankings;

import interfaceadapter.ViewManagerModel;
import usecase.rankingsuserstory.to_rankings.RankingsOutputBoundary;

/**
 * The RankingsPresenter class is responsible for orchestrating updates to the
 * RankingsViewModel and notifying the ViewManagerModel of changes. It implements
 * the RankingsOutputBoundary interface to adhere to the use case layer's output boundary contract.
 */
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
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setState(rankingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
