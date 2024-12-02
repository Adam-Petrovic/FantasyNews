package interfaceadapter.pointsuserstory.award_league_points;

import java.util.ArrayList;

import entity.User;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputData;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputBoundary;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputData;

public class AwardLeaguePointsController {

    private final RoundLeaguePointsInputBoundary roundLeagueInteractor;
    private AwardLeaguePointsInputBoundary awardLeaguePointsInteractor;

    public AwardLeaguePointsController(AwardLeaguePointsInputBoundary awardLeaguePointsInteractor,
                                       RoundLeaguePointsInputBoundary roundLeagueInteractor) {
        this.awardLeaguePointsInteractor = awardLeaguePointsInteractor;
        this.roundLeagueInteractor = roundLeagueInteractor;
    }

    /**
     * This function specifies which interactor to use; in this case, to award points to a user..
     * @param leagueID The name of the League
     * @param users  An arraylist of the users in the League
     */
    public void awardPoints(String leagueID, ArrayList<User> users) {
        AwardLeaguePointsInputData awardLeaguePointsInputData = new AwardLeaguePointsInputData(leagueID, users);
        awardLeaguePointsInteractor.execute(awardLeaguePointsInputData);
    }

    /**
     * This function specifies which interactor to use; in this case, to round up the points of each user.
     * @param leagueID The name of the League
     * @param users  An arraylist of the users in the League
     */
    public void roundUp(String leagueID, ArrayList<User> users) {
        RoundLeaguePointsInputData roundLeaguePointsInputData = new RoundLeaguePointsInputData(leagueID, users);
        roundLeagueInteractor.roundUp(roundLeaguePointsInputData);
    }

}
