package interface_adapter.update_rankings;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_rankings.RankingsViewModel;
import use_case.update_rankings.UpdateRankingsOutputBoundary;
import use_case.update_rankings.UpdateRankingsOutputData;


public class UpdateRankingsPresenter implements UpdateRankingsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private RankingsViewModel rankingsViewModel;

    public UpdateRankingsPresenter(ViewManagerModel viewManagerModel, RankingsViewModel rankingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsViewModel = rankingsViewModel;
    }


    @Override
    public void execute(UpdateRankingsOutputData outputData) {
        rankingsViewModel.getState().getLeague().setRankings(outputData.getRankings());

        this.rankingsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(rankingsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }



}
