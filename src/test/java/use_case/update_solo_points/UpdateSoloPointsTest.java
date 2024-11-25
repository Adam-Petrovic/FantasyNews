package use_case.update_solo_points;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.Users.CommonUser;
import entity.Users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateSoloPointsTest {
    @Test
    void successUserLoggedInTest() {
        String[] word = {"Panda", "Panda", "Panda", "Panda", "Panda"};
        User user = new CommonUser("Adam", "madA", word);
        UpdatePointsInputData inputData = new UpdatePointsInputData(user);

        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("PUT API KEY HERE");


        UpdatePointsOutputBoundary successPresenter = new UpdatePointsOutputBoundary() {
            @Override
            public void execute(UpdatePointsOutputData updatePointsOutputData) {
                user.setPoints(updatePointsOutputData.getPoints());
                System.out.println(updatePointsOutputData.getPoints()[0]);
                assertEquals(guardianDataAccessObject.getPointsForCategory(word[0]),
                        user.getPointsForCategory(Constants.CATEGORIES[0]));
            }

        };

        UpdatePointsInputBoundary interactor = new UpdatePointsInteractor(guardianDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
