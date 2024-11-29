package use_case.updateLeaguePoints;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.User;

import java.util.ArrayList;

public class UpdateLeaguePointsInteractor implements UpdateLeaguePointsInputBoundary{
    private GuardianDataAccessObject guardianDataAccessObject;

    public UpdateLeaguePointsInteractor(GuardianDataAccessObject guardianDataAccessObject) {
        this.guardianDataAccessObject = guardianDataAccessObject;
    }

    @Override
    public void execute(UpdateLeaguePointsInputData updateLeaguePointsInputData) {
        ArrayList<User>  users = updateLeaguePointsInputData.getUsers();

        for(int i = 0; i < users.size(); i++){
            int[] pts = new int[Constants.NUM_CATEGORIES];
            User user = users.get(i);
            for (int j = 0; j < Constants.NUM_CATEGORIES; j++) {
                pts[j] = guardianDataAccessObject
                            .getPointsForCategory(
                                    user.getWordFromCategory(Constants.CATEGORIES[j]));
            }
            user.setPoints(pts);
        }
    }
}
