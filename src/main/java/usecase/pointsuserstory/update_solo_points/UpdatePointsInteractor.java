package usecase.pointsuserstory.update_solo_points;

import data_access.Constants;
import entity.User;

public class UpdatePointsInteractor implements UpdateSoloPlayPointsInputBoundary {
    private final UpdateSoloPlayPointsDataAccessInterface updatePointsDataAccessInterface;
    private final UpdatePointsOutputBoundary updatePointsPresenter;

    public UpdatePointsInteractor(UpdateSoloPlayPointsDataAccessInterface updatePointsDataAccessInterface,
                                  UpdatePointsOutputBoundary updatePointsPresenter) {
        this.updatePointsDataAccessInterface = updatePointsDataAccessInterface;
        this.updatePointsPresenter = updatePointsPresenter;
    }

    @Override
    public void execute(UpdateSoloPlayPointsInputData updatePointsInputData) {
        int[] points = new int[Constants.NUM_CATEGORIES];
        User user = updatePointsInputData.getUser();
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            points[index] =
                    updatePointsDataAccessInterface
                            .getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[index]));
        }
        UpdateSoloPlayPointsOutputData outputData = new UpdateSoloPlayPointsOutputData(points);
        updatePointsPresenter.execute(outputData);
    }
}
