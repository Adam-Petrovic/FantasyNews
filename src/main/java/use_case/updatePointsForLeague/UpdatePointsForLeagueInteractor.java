package use_case.updatePointsForLeague;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.User;

import java.util.ArrayList;

public class UpdatePointsForLeagueInteractor implements UpdatePointsForLeagueInputBoundary {
    private GuardianDataAccessObject updatePointsForLeagueDataAccessObject;

    public UpdatePointsForLeagueInteractor(UpdatePointsForLeagueDataAccessObject updatePointsForLeagueDataAccessObject) {
        this.updatePointsForLeagueDataAccessObject = updatePointsForLeagueDataAccessObject;
    }

    @Override
    public void execute(UpdatePointsForLeagueInputData updateLeaguePointsInputData) {
        ArrayList<User>  users = updateLeaguePointsInputData.getUsers();

        for(int i = 0; i < users.size(); i++){
            int[] pts = new int[Constants.NUM_CATEGORIES];
            User user = users.get(i);
            for (int j = 0; j < Constants.NUM_CATEGORIES; j++) {
                pts[j] = updatePointsForLeagueDataAccessObject
                            .getPointsForCategory(
                                    user.getWordFromCategory(Constants.CATEGORIES[j]));
            }
            user.setPoints(pts);
        }
    }
}
