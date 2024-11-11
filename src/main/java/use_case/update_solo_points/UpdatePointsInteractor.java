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
        User user = updatePointsInputData.getUser();
        for(String category : Constants.CATEGORIES) {
            int pointsInCategory = guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(category));
            user.setCategoryPoints(category, pointsInCategory);
        }

        updatePointsPresenter.execute();
    }




}
