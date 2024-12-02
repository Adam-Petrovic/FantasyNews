package usecase.award_league_points;

import java.util.ArrayList;

import data_access.Constants;
import entity.User;

public class AwardLeaguePointsInteractor implements AwardLeaguePointsInputBoundary {
    private AwardLeaguePointsDataAccessInterface awardLeaguePointsDataAccessInterface;

    public AwardLeaguePointsInteractor(AwardLeaguePointsDataAccessInterface dataAccessInterface) {
        this.awardLeaguePointsDataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(AwardLeaguePointsInputData awardLeaguePointsInputData) {
        ArrayList<User> users = awardLeaguePointsInputData.getUsers();

        for (String category : Constants.CATEGORIES) {
            String userWithMostPoints = getUserWithMostPoints(users, category);
            awardLeaguePointsDataAccessInterface
                    .updatePointsForUser(awardLeaguePointsInputData.getLeagueID(),
                                            userWithMostPoints,
                                            users.size());
        }

    }

    private String getUserWithMostPoints(ArrayList<User> users, String category) {
        String userWithMostPoints = users.get(0).getName();
        int maxPoints = users.get(0).getPointsForCategory(category);
        for (User user : users) {
            if (user.getPointsForCategory(category) >= maxPoints) {
                userWithMostPoints = user.getName();
                maxPoints = user.getPointsForCategory(category);
            }
        }
        return userWithMostPoints;
    }

}
