package use_case.update_rankings;

import use_case.update_solo_points.UpdatePointsInputData;

public interface UpdateRankingsInputBoundary {
    void execute(UpdateRankingsInputData updateRankingsInputData);
}