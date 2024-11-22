package use_case.update_rankings;

import use_case.update_solo_points.UpdatePointsOutputData;

public interface UpdateRankingsOutputBoundary {
    void execute(UpdateRankingsOutputData updateRankingsOutputData);
}
