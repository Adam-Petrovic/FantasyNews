package use_case.update_solo_points;

import entity.User;

public interface UpdatePointsInputBoundary {
    void execute(UpdatePointsInputData updatePointsInputData);
}
