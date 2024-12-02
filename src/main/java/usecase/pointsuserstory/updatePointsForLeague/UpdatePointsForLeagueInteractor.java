package usecase.pointsuserstory.updatePointsForLeague;

import java.util.ArrayList;

import data_access.Constants;
import entity.User;

public class UpdatePointsForLeagueInteractor implements UpdatePointsForLeagueInputBoundary {
    private UpdatePointsForLeagueDataAccessObject updatePointsForLeagueDataAccessObject;

    public UpdatePointsForLeagueInteractor(
            UpdatePointsForLeagueDataAccessObject updatePointsForLeagueDataAccessObject) {
        this.updatePointsForLeagueDataAccessObject = updatePointsForLeagueDataAccessObject;
    }

    @Override
    public void execute(UpdatePointsForLeagueInputData updateLeaguePointsInputData) {
        ArrayList<User> users = updateLeaguePointsInputData.getUsers();

        for (int i = 0; i < users.size(); i++) {
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
