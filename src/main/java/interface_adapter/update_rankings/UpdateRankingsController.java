package interface_adapter.update_rankings;

import entity.League;
import use_case.update_rankings.UpdateRankingsInputBoundary;
import use_case.update_rankings.UpdateRankingsInputData;

public class UpdateRankingsController {
    private UpdateRankingsInputBoundary updateRankingsInteractor;

    public UpdateRankingsController(UpdateRankingsInputBoundary updateRankingsInteractor) {
        this.updateRankingsInteractor = updateRankingsInteractor;
    }
    public void execute(League league){
        UpdateRankingsInputData updateRankingsInputData = new UpdateRankingsInputData(league);
        updateRankingsInteractor.execute(updateRankingsInputData);
    }
}
