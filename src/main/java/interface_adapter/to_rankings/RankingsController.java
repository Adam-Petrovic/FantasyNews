package interface_adapter.to_rankings;

import use_case.to_rankings.RankingsOutputBoundary;

public class RankingsController {
    private final RankingsOutputBoundary rankingsPresenter;

    public RankingsController(RankingsOutputBoundary rankingsPresenter) {
        this.rankingsPresenter = rankingsPresenter;
    }

    public void execute(){
        rankingsPresenter.execute();
    }
}
