package interface_adapter.update_rankings;

import interface_adapter.ViewManagerModel;
import view.RankingsView;

public class UpdateRankingsPresenter {
    private ViewManagerModel viewManagerModel;
    private RankingsView rankingsView;

    public UpdateRankingsPresenter(ViewManagerModel viewManagerModel, RankingsView rankingsView) {
        this.viewManagerModel = viewManagerModel;
        this.rankingsView = rankingsView;
    }



}
