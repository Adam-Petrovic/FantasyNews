package interface_adapter.update_rankings;

import use_case.update_rankings.UpdateRankingsInputBoundary;

public class UpdateRankingsController {
    private UpdateRankingsInputBoundary updateRankingsInputBoundary;

    public UpdateRankingsController(UpdateRankingsInputBoundary updateRankingsInputBoundary) {
        this.updateRankingsInputBoundary = updateRankingsInputBoundary;
    }
    public void execute(){
        updateRankingsInputBoundary.execute();
    }
}
