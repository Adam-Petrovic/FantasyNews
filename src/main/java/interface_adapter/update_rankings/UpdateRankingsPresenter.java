package interface_adapter.update_rankings;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_rankings.RankingsState;
import interface_adapter.to_rankings.RankingsViewModel;
import usecase.update_rankings.UpdateRankingsOutputBoundary;
import usecase.update_rankings.UpdateRankingsOutputData;


public class UpdateRankingsPresenter implements UpdateRankingsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private RankingsViewModel rankingsViewModel;

    public UpdateRankingsPresenter(ViewManagerModel viewManagerModel, RankingsViewModel rankingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsViewModel = rankingsViewModel;
    }


    @Override
    public void execute(UpdateRankingsOutputData outputData) {
        System.out.println("In updateRankingsPresenter");
        //rankingsViewModel.getState().getLeague().setRankings(outputData.getRankings());
        RankingsState rankingsState = rankingsViewModel.getState();
        rankingsState.setLeague(outputData.getLeague());
        rankingsState.getLeague().setLiveRankings(outputData.getLiveRankings());
        rankingsState.getLeague().setHistoricalRankings(outputData.getHistoricalRankings());
        rankingsViewModel.setState(rankingsState);
        rankingsViewModel.firePropertyChanged();

        System.out.println("past fire PropertyChanged");
        viewManagerModel.setState(rankingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }



}
