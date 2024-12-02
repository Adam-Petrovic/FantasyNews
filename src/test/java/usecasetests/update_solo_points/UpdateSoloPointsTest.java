package usecasetests.update_solo_points;

import dataaccess.Constants;
import dataaccess.GuardianDataAccessObject;
import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;
import usecase.pointsuserstory.update_solo_points.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateSoloPointsTest {
    @Test
    void successUserLoggedInTest() {
        String[] word = {"Panda", "Panda", "Panda", "Panda", "Panda"};
        User user = new CommonUser("Adam", "madA", word);
        UpdateSoloPlayPointsInputData inputData = new UpdateSoloPlayPointsInputData(user);

        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("c7dda92e-78d1-440d-a3b7-ee3d27ee35be");


        UpdatePointsOutputBoundary successPresenter = new UpdatePointsOutputBoundary() {
            @Override
            public void execute(UpdateSoloPlayPointsOutputData updatePointsOutputData) {
                user.setPoints(updatePointsOutputData.getPoints());
                System.out.println(updatePointsOutputData.getPoints()[0]);
                assertEquals(guardianDataAccessObject.getPointsForCategory(word[0]),
                        user.getPointsForCategory(Constants.CATEGORIES[0]));
            }

        };

        UpdateSoloPlayPointsInputBoundary interactor = new UpdatePointsInteractor(guardianDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
