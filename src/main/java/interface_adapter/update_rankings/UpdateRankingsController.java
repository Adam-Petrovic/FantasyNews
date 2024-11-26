package interface_adapter.update_rankings;

import use_case.update_rankings.UpdateRankingsInputBoundary;
import use_case.update_rankings.UpdateRankingsInputData;

public class UpdateRankingsController {
    private UpdateRankingsInputBoundary updateRankingsInteractor;

    public UpdateRankingsController(UpdateRankingsInputBoundary updateRankingsInteractor) {
        this.updateRankingsInteractor = updateRankingsInteractor;
    }
    public void execute(String leagueID){
        UpdateRankingsInputData updateRankingsInputData = new UpdateRankingsInputData(leagueID);
        updateRankingsInteractor.execute(updateRankingsInputData);
    }
}
