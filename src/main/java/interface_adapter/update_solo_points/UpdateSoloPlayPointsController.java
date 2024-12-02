package interface_adapter.update_solo_points;

import entity.User;
import usecase.update_solo_points.UpdateSoloPlayPointsInputBoundary;
import usecase.update_solo_points.UpdateSoloPlayPointsInputData;

public class UpdateSoloPlayPointsController {
    private final UpdateSoloPlayPointsInputBoundary updatePointsUseCaseInteractor;

    public UpdateSoloPlayPointsController(UpdateSoloPlayPointsInputBoundary updatePointsUseCaseInteractor) {
        this.updatePointsUseCaseInteractor = updatePointsUseCaseInteractor;
    }

    /**
     * The execute method for the controller.
     * @param user the User who's points need updating
     */
    public void execute(User user) {
        UpdateSoloPlayPointsInputData updatePointsInputData = new UpdateSoloPlayPointsInputData(user);
        updatePointsUseCaseInteractor.execute(updatePointsInputData);
    }
}
