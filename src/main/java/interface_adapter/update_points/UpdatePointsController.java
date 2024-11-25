package interface_adapter.update_points;

import entity.Users.User;
import use_case.update_solo_points.UpdatePointsInputBoundary;
import use_case.update_solo_points.UpdatePointsInputData;

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
