package interface_adapter.update_points;

import entity.User;
import usecase.update_solo_points.UpdatePointsInputBoundary;
import usecase.update_solo_points.UpdatePointsInputData;

public class UpdatePointsController {
    private final UpdatePointsInputBoundary updatePointsUseCaseInteractor;

    public UpdatePointsController(UpdatePointsInputBoundary updatePointsUseCaseInteractor) {
        this.updatePointsUseCaseInteractor = updatePointsUseCaseInteractor;
    }

    public void execute(User user){
        UpdatePointsInputData updatePointsInputData = new UpdatePointsInputData(user);
        updatePointsUseCaseInteractor.execute(updatePointsInputData);
    }
}
