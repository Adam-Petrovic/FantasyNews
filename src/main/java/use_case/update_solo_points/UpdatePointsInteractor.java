package use_case.update_solo_points;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.User;

public class UpdatePointsInteractor implements UpdatePointsInputBoundary{
    private final GuardianDataAccessObject guardianDataAccessObject;
    private final UpdatePointsOutputBoundary updatePointsPresenter;

    public UpdatePointsInteractor(GuardianDataAccessObject guardianDataAccessObject,
                                  UpdatePointsOutputBoundary updatePointsPresenter) {
        this.guardianDataAccessObject = guardianDataAccessObject;
        this.updatePointsPresenter = updatePointsPresenter;
    }

    @Override
    public void execute(UpdatePointsInputData updatePointsInputData) {
        int[] points = new int[Constants.NUM_CATEGORIES];
        User user = updatePointsInputData.getUser();
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            points[index] =
                    guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[index]));
        }
        UpdatePointsOutputData outputData = new UpdatePointsOutputData(points);
        updatePointsPresenter.execute(outputData);
    }




}
