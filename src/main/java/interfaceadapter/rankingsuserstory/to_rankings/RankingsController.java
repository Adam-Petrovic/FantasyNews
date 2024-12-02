package interfaceadapter.rankingsuserstory.to_rankings;

import usecase.rankingsuserstory.to_rankings.RankingsOutputBoundary;

/**
 * The RankingsController class handles input related to the "to rankings" use case
 * and delegates execution to the associated RankingsOutputBoundary. It serves as
 * a part of the controller layer in the application's architecture.
 */
public class RankingsController {
    private final RankingsOutputBoundary rankingsPresenter;

    public RankingsController(RankingsOutputBoundary rankingsPresenter) {
        this.rankingsPresenter = rankingsPresenter;
    }

    /**
     * Executes the "to rankings" use case by delegating to the RankingsOutputBoundary.
     */
    public void execute() {
        rankingsPresenter.execute();
    }
}

